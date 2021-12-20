package gameObject;

import application.MainGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class KeyEffect extends GameObject{
	public int[] list;
	
	public KeyEffect() {
		x = MainGame.game.getWidth() / 4;
		y = MainGame.game.getHeight() - 200;
		w = MainGame.game.getWidth() / 4 - 6;
		h = 200-2;
	}

	@Override
	public void update(double d) {
		list = MainGame.game.inputSystem.getKey();
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, y, x * 7, y);
		for(int i = 0; i < 7; i++) {
			double rx = x * i + 3;
			if(list[i] == 1) {
				gc.beginPath();
				gc.setFill(Color.web("#fef7fe"));
				gc.moveTo(rx, y);
				gc.lineTo(rx, y + h);
				gc.lineTo(rx + w, y + h);
				gc.lineTo(rx + w, y);
				gc.fill();
				gc.closePath();
				gc.setStroke(Color.WHITE);
				gc.strokeRect(rx, y, w, h);
			} else {
				gc.beginPath();
				//분홍
				gc.setFill(Color.web("#dcbfdb"));
				//하늘색
//				gc.setFill(Color.web("#50bcdf"));
				gc.moveTo(rx, y);
				gc.lineTo(rx, y + h);
				gc.lineTo(rx + w, y + h);
				gc.lineTo(rx + w, y);
				gc.fill();
				gc.closePath();
				gc.setStroke(Color.WHITE);
				gc.strokeRect(rx, y, w, h);
			}
		}
	}
}

//s, d, f, j, k, l, space
