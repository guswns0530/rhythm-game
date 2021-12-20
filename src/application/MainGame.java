package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import gameObject.BackGround;
import gameObject.ClickShadow;
import gameObject.Combo;
import gameObject.GameObject;
import gameObject.HpBar;
import gameObject.Judgment;
import gameObject.KeyEffect;
import gameObject.NodeList;
import gameObject.ScoreText;
import gameObject.textScore;
import info.PlayerInfo;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import player.MusicPlayer;
import util.JDBCUtil;
import views.EndGameController;
import views.MainController;

public class MainGame {
	public static MainGame game;
	
	//input시스템
	public InputSystem inputSystem = new InputSystem();
	
	//node 관리자
	public NodeList nodeList;
	public double speed;
	
	//hp bar
	public HpBar hp;
	
	public Combo combo;
	public ScoreText sc;
	public textScore ts; 
	
	private Canvas GameCanvas;
	
	private GraphicsContext gc;
	//hpbar canvas
	private GraphicsContext Hpgc;
	//score canvas
	private double cw;
	private double ch;
	
	private long before;
	
	//gameObject 저장소
	private ArrayList<GameObject> list;
	
	private AnimationTimer mainLoop;
	
	public MusicPlayer MainMusic;
	
	public double totalTime = 0;
	public Boolean GameOver = true;
	public Boolean GameEnd = false;
	
	//초기화 애니메이션
	private double volume;
	private double endSpeed;
	
	private PlayerInfo info;
	
	public double getWidth() {
		return cw;
	}
	public double getHeight() {
		return ch;
	}
	
	
	public void startGame() {
		GameOver = false;
		MainMusic.play();
		MainMusic.setTime(totalTime);
		volume = MainMusic.getVolume();
		endSpeed = speed;
	}
	
	public void endGame(double d) {
//		GameOver = true;
		if(!GameEnd) return;
		
		//종료 애니메이션
		if(MainMusic.getVolume() <= 0 && speed <= 0) {
			GameOver = true;
			MainMusic.stop();
			
			
			MainController mc = (MainController)Main.app.getController("MainGame");
			Main.app.removePane(mc.getRoot());
			mc.reset();
			mainLoop.stop();
			Main.app.Maingame = false;

			
			//db 기록
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO `score`(`score`, `userName`, `musicName`) VALUES (?,?,?)";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setDouble(1, sc.getScore());
				stmt.setString(2, Main.app.userId);
				stmt.setString(3, info.name);
				
				if(stmt.executeUpdate() != 1) {
					System.out.println("오류");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Main.app.addPane("EndGame");
			EndGameController ec = (EndGameController)Main.app.getController("EndGame");
			info.score = (int) sc.getScore();
			ec.setInfo(info);
			ec.start();
		} else {
			MainMusic.setVolume(MainMusic.getVolume() - ((volume * d)/2));
			speed -= (endSpeed * d) / 2;
		}
		
		//다른 pane 불러오기
		
	}
	
		
	public MainGame(Canvas canvas, Canvas canvas2, Canvas canvas3, PlayerInfo info) {
		game = this;
		
		GameCanvas = canvas;
		gc = GameCanvas.getGraphicsContext2D();
		Hpgc= canvas2.getGraphicsContext2D();
		this.info = info;
		
		cw = canvas.getWidth();
		ch = canvas.getHeight();
		
		//생명
		hp = new HpBar(canvas2);
		//점수
		//음악재생기
		MainMusic = new MusicPlayer();
		combo = new Combo();
		sc = new ScoreText(canvas3);
		ts = new textScore();
		list = new ArrayList<>();
		
		append();
		
		before = System.nanoTime();
		mainLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				long deltaTime = now - before;
				before = now;
				
				double d = deltaTime / 1000000000d;
				if(!GameOver) totalTime += d; 
				update(d);
				render();
			}
		};
		mainLoop.start();
		
		startGame();
	}
	
//	화면에 표시될 내용 추가
	public void append() {
		//뮤직 이름 정해주기
		String musicName = info.name;
		speed = info.speed;
		
		nodeList = new NodeList();
 		nodeList.loadMusic(musicName+"/note.txt");
 		MainMusic.setMusic(musicName+ "/audio.mp3");
 		
		list.add(new BackGround());
		list.add(new Judgment());
		list.add(new ClickShadow());
		list.add(nodeList);
		list.add(new KeyEffect());
		list.add(combo); 
		list.add(ts);
	}
	public void update(double d) {
		//게임종료 애니메이션
		endGame(d);
		if(GameOver) return;
		
		for(GameObject go : list) {
			go.update(d);
		}
		hp.update(d);
		sc.update(d);
	}
	public void render() {
		if(GameOver) return;
		
		gc.clearRect(0, 0, cw, ch);
		//게임 실행시 render
		for(GameObject go : list) {
			go.render(gc);
		}
		hp.render(Hpgc);
		sc.render();
	}
}

//https://frontdev.tistory.com/entry/Canvas-%EB%B2%84%ED%8A%BC-Particle-%EC%9D%B4%ED%8E%99%ED%8A%B8
//이펙트 배끼기
