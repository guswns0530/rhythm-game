package gameObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import application.MainGame;
import javafx.scene.canvas.GraphicsContext;

public class NodeList extends GameObject {
	public List<Node> list;
	
	private Score longNode;
	
	private double judgment;
	private double time = 0;
	private int idx = 0;
	private Combo combo;
	private HpBar hp;
	private ScoreText sc;
	private textScore ts;
	
	public double getTime() {
		return time;
	}
	
	public NodeList() {
		sc = MainGame.game.sc;
		combo = MainGame.game.combo;
		longNode = new Score();
		judgment = MainGame.game.getHeight() - 200 - 25;
		list = new ArrayList<>();
		hp = MainGame.game.hp;
		ts = MainGame.game.ts;
	}
	
	@Override
	public void update(double d) {
		if(idx > list.size() -1 && list.get(list.size() - 1).active == false) {
			//게임 엔딩씬 로드
			MainGame.game.GameEnd = true;
		}
		time += d;
		
		while(list.size() > idx) {
			Node n = list.get(idx);
			if(n.time <= MainGame.game.totalTime) {
				n.active = true;
				idx++;
			}else {
				break;
			}
		}
		
		//롱노드 판정 업데이터
		longNode.update(d);
		
		for(Node node : list) {
			if(node.y > MainGame.game.getHeight() - 225 + 100) {
				if(node.active && !node.isFirst) {
					if(node.len == 0) {
						node.active = false;
						//짧은노드종료
						ts.setText("miss");
						combo.reset();
						hp.minusLife();
					} else {
						//한번만 실행
						node.nodeActive = true;
						node.isFirst = true;
					}
				}
			} 
			//노트중에 활성화 되있는것중에 판정선을 지나칠경우 node.acitve =false;
			//miss 출력
			
			//긴노드 종료 if문
			if(node.maxHeight > MainGame.game.getHeight() - 225 + 100 && node.active) {
				if( !node.isSecond ) {
					ts.setText("miss");
					combo.reset();
					hp.minusLife();
				} 
				node.active = false;
			}
		}
		
		for(Node node : list) {
			node.update(d);
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		for(Node n : list) {
			n.render(gc);
		}
	}
	
	
	public void loadMusic(String filename)	{
		try {
 		File file = new File(getClass().getResource("/resources/" + filename).getFile());
 	 		FileInputStream fis = new FileInputStream(file);
 	 		InputStreamReader isr = new InputStreamReader(fis);
 	 		BufferedReader br = new BufferedReader(isr);
 	 		
 	 		double nodeDropTime = (MainGame.game.getHeight() - 200) / ((MainGame.game.getHeight() - 200) * MainGame.game.speed);
 	 		while(true) {
 	 			String node = br.readLine();
 	 			
 	 			if(node == null) break;
 	 			String[] info = node.split(":");
 	 			
 				list.add(new Node(Integer.parseInt(info[0]), (Double.parseDouble(info[1]) - nodeDropTime), Double.parseDouble(info[2]) - nodeDropTime));
 	 		}
 	 		MainGame.game.sc.totalNotes = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeNode(int idx) {
		MainGame.game.inputSystem.setBoolean(idx, true);
		
		for(Node node : list) {
			if(node.idx == idx && node.active) { //node.idx == idx && node.active
				double between = judgment - node.y;
				//long node 판정 추가
				if(between <= 150) {
					if(node.len > 0) {
						node.isClick = true;
						node.isFirst = true;
						node.isSecond = true;
						longNode.set(node, between);
					} else {
						scorePrint(between);
						hp.plusLife(2);
						combo.plus();
						node.active = false;
					}
				} 
				if(node.len == 0 ) break;
			}
		}
	}
	
	private void scorePrint(double between) {
		int[] scoreInt = {150, 90, 30, -30, -50};
		String[] scoreText = {"nice", "great", "perfect", "great", "nice"};
		int[] score = {50, 150, 250, 150, 50};
		
		for(int i = 4; i >= 0; i--) {
			if(scoreInt[i] >= between) {
				//send to score msg : scoreText[i];
				ts.setText(scoreText[i]);
				sc.score(score[i]);
				break;
			}
		}
	}
	
	
//	//수정
//	private void longNode(Node n, int idx, double firstClick) {
//		//재귀함수 종료 구문 
//		double between = judgment - n.maxHeight;
//		System.out.println("maxHeight : " + between);
//		
//		
//		if(!MainGame.game.inputSystem.getBoolean(idx) || between < -30) {
//			System.out.println("첫번째 클릭 " + firstClick + "두번째 클릭 " + between	);
//			
//			//여기서 처리하자 !DSFSDF
//			n.isClick = false;
//			
//			return;
//		}
//		
//		combo.plus();
//		
//		Timer timer = new Timer();
//		TimerTask timerTask = new TimerTask() {
//			
//			@Override
//			public void run() {
//				hp.plusLife(0.2);
//				longNode(n, idx, firstClick);
//			}
//		};
//		
//		timer.schedule(timerTask, 200);
//	}
}	

