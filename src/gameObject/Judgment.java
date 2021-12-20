package gameObject;

import application.MainGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Judgment extends GameObject {
	
	private LinearGradient gradient;
	
	public Judgment() {
		w = MainGame.game.getWidth();
		h = 25;
		x = 0;
		//판정부분 h
		y = MainGame.game.getHeight() - 200 - 25;
		
		//그라디언트
		Stop[] stop = new Stop[] { new Stop(0, Color.web("#a9bfd4")), new Stop(1, Color.web("#6d8997")) };
		gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop);
	}

	@Override
	public void update(double d) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GraphicsContext gc) {
		//그라디언트 추가
		gc.setFill(gradient);
		gc.fillRect(x, y, w, h);
		
		gc.setStroke(Color.WHITE);
		gc.strokeRect(x, y, w, h);
	}

}
