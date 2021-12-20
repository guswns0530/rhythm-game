package gameObject;

import application.MainGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Node extends GameObject{
	
	private double speed;
	public double y;
	public double time;
	public int idx;
	
	private LinearGradient gradient1;
	private LinearGradient gradient2;
	private LinearGradient gradient3;
	private Color Black;
	
	
	//long node부분
	private double lenW = 0;
	private double lenH = 0;
	private double lenP = 0;
	public double len = 0;
	public double maxHeight;
	
	//active = true일 경우 삭제
	public Boolean active = false;
	//nodeAcitve = true일 경우 노트비활성화 
	public Boolean nodeActive = false;
	//isScore = true일 경우 점수체크하지 않음 
	public Boolean isFirst = false;
	public Boolean isSecond = false;
	//isClick = true일 경우 클릭되어있는것
	public Boolean isClick = false;
	
	
	//KeyCode key
	public Node(int idx, double time, double len) {
		this.len = len;
		this.time = time;
		this.idx = idx;
		
		idx--;
		
		w = MainGame.game.getWidth() / 4;
		h = 25;
		x = MainGame.game.getWidth() / 4 * idx;
		y = -25;
		lenW = w - 25;
		lenP = 25 / 2 + 2;
		lenH = 0;
		speed = (MainGame.game.getHeight() - 200) * MainGame.game.speed;
		Black = new Color(0, 0, 0, 0.5);
		//basic gradient
		Stop[] stop = new Stop[] { new Stop(0, Color.web("#b86b8d")), new Stop(1, Color.web("#f5a4cd")) };
		Stop[] stop1 = new Stop[] { new Stop(0, Color.web("#e196bd")), new Stop(1, Color.web("#b76f94")) };
		Stop[] stop2 = new Stop[] { new Stop(0, Color.web("#b46f8e")), new Stop(0.5, Color.web("#e896be")), new Stop(1, Color.web("#b46f8e")) };
		gradient1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stop);
		gradient2 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stop1);
		gradient3 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stop2);
		
		//after gradient 나중에 만들거
	}

	@Override
	public void update(double d) {
		if(!active) return;
		//노드 길이
		speed = (MainGame.game.getHeight() - 200) * MainGame.game.speed;
		if(len <= MainGame.game.totalTime && len != 0) {
			lenH += speed * d;
		}
		maxHeight = lenH - h;
		y += speed * d;
	}

	@Override
	public void render(GraphicsContext gc) {
		if(!active) return;
		drawNote(gc, x, y);
		if(nodeActive) {
			drawNoteBlack(gc, x, y);
		}
		
		if(len > 0) {
			gc.setFill(gradient3);
			gc.fillRect(x + lenP, lenH, lenW, y - lenH);
			drawNote(gc, x, lenH - h);
			if(nodeActive) {
				gc.setFill(Black);
				gc.fillRect(x + lenP, lenH, lenW, y - lenH);
				drawNoteBlack(gc, x, lenH-h);
			}
		}
	}
	
	public void drawNote(GraphicsContext gc, double x, double y) {
		gc.beginPath();
		gc.setFill(gradient1);
		//border x + 1 ~ w - 2
		gc.fillRect(x + 2, y, (w/2 + 1), h);
		gc.setFill(gradient2);
		gc.fillRect(x + 2 + (w/2), y, w / 2-4, h);
		gc.setStroke(Color.PINK);
		gc.strokeRect(x+2, y, w - 4, h);
		gc.fill();
		gc.closePath();
	}
	public void drawNoteBlack(GraphicsContext gc, double x, double y) {
		gc.beginPath();
		gc.setFill(Black);
		//border x + 1 ~ w - 2
		gc.fillRect(x + 2, y, (w/2 + 1), h);
		gc.setFill(Black);
		gc.fillRect(x + 2 + (w/2), y, w / 2-4, h);
		gc.setStroke(Black);
		gc.strokeRect(x+2, y, w - 4, h);
		gc.fill();
		gc.closePath();
	}
}
