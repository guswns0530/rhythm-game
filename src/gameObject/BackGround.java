package gameObject;

import application.MainGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class BackGround extends GameObject{
	public BackGround() {
		w = MainGame.game.getWidth();
		h = MainGame.game.getHeight();
		x = 0;
		y = 0;
	}

	@Override
	public void update(double d) {
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, w, h);
		
		gc.beginPath();
		gc.setStroke(Color.rgb(255, 255, 255));
		for(int i = 0; i < 4; i++) {
			gc.setLineWidth(2);
			gc.moveTo(w/4* i, 0);
			gc.lineTo(w/4 * i, h);
			gc.stroke();
			gc.closePath();
		}
	}

}
