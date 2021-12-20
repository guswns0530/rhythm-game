package gameObject;

import application.MainGame;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;

public class HpBar extends GameObject {
	private Canvas canvas;
	
	//라이프 최대값
	private double maxLife = 100;
	//현재 라이프
	public double life;
	//이전 라이프
	private double beforeLife;
	
	private double gw;
	private double gh;
	private double padding;
	private double time = 0;
	
	public HpBar(Canvas canvas) {
		this.canvas = canvas;
		
		
		beforeLife = 0;
		life = 100;
		
		gw = canvas.getWidth();
		gh = canvas.getHeight();
		padding = 13;
		w = gw - (padding*2);
		h = gh - (padding*2);
		
	}
	
	public void minusLife() {
		life -= 5;
		if(life <= 0) {
			life = 0;
			MainGame.game.GameEnd = true;
		}
	}
	
	public void plusLife(double plus) {
		plus = Math.round(plus);
		life += plus;
		
		if(life > 100) {
			life = 100;
		}
	}

	@Override
	public void update(double d) {
		if(life == Math.round(beforeLife*100) / 100.0) {
			return;
		}
		time += d;
		
		if(time >= 0.00001) {
			if(beforeLife > life ) {
				beforeLife -= 0.4;
				time = 0;
			} else {
				beforeLife += 0.4;
				time = 0;
			}
		}
	}

	//render
	@Override
	public void render(GraphicsContext gc) {
		gc.clearRect(0, 0, gw, gh);
		//배경 render
		gc.setFill(Color.rgb(70, 82, 106, 0.8));
		gc.beginPath();
		gc.moveTo(0, 0);
		gc.quadraticCurveTo(gw, 0, gw, gw);
		gc.lineTo(gw, gw);
		gc.lineTo(gw, gh);
		gc.lineTo(0, gh);
		gc.fill();
		
		gc.setFill(Color.BLACK);
		gc.fillRect(padding, padding, w, h);
		
		//채우기
		gc.setFill(Color.WHITE);
		gc.beginPath();
		gc.moveTo(padding, gh - padding);
		gc.lineTo(gw - padding, gh-padding);
		gc.lineTo(gw - padding, (gh-padding - (h * (beforeLife / maxLife))));
		gc.lineTo(padding,  (gh-padding - (h * (beforeLife / maxLife))));
		gc.fill();
		gc.closePath();
		
		//마감
		//up left
		gc.setFill(Color.rgb(70, 82, 106, 0.8));
		gc.beginPath();
		gc.moveTo(padding, padding);
		gc.lineTo((w/2) + padding, padding);
		gc.quadraticCurveTo(padding, padding, padding, padding + (w/2));
		gc.fill();
		gc.closePath();
		
		
		//up right
		gc.beginPath();
		gc.moveTo(gw - padding, padding);
		gc.lineTo((w /2 ) + padding, padding);
		gc.quadraticCurveTo(gw - padding, padding, gw-padding, padding + (w/2));
		gc.fill();
		gc.closePath();
		
		//Down left
		gc.beginPath();
		gc.moveTo(padding, gh - padding);
		gc.lineTo((w/2) + padding, gh - padding);
		gc.quadraticCurveTo(padding, gh - padding, padding, gh - (padding + (w/2)));
		gc.fill();
		gc.closePath();
//		Down Right
		gc.beginPath();
		gc.moveTo(gw - padding, gh-padding);
		gc.lineTo((w /2 ) + padding, gh-padding);
		gc.quadraticCurveTo(gw - padding, gh-padding, gw-padding, gh - (padding + (w/2)));
		gc.fill();
		gc.closePath();
	}
}
