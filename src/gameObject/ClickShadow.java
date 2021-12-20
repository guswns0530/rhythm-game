package gameObject;


import application.MainGame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ClickShadow extends GameObject {
	
	private int[] list;
	private LinearGradient gradient;
	
	//애니메이션 할내역들
	private int[] before = new int[7];
	private int[] beforeY = new int[7];
	
	public ClickShadow() {
		x = MainGame.game.getWidth() / 4;
		//캔버스 높이 - 키건반 - 쉐도우이펙트 높이
		y = MainGame.game.getHeight() - 200 - 300;
		w = MainGame.game.getWidth() / 4 - 4;
		h = 300;
		Stop[] stop = new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.BLACK) };
		gradient = new LinearGradient(0,1, 0,0, true, CycleMethod.NO_CYCLE, stop );
	}

	@Override
	public void update(double d) {
		list = MainGame.game.inputSystem.getKey();
		update2(d);
	}
	@Override
	public void render(GraphicsContext gc) {
		render2(gc);
		// TODO Auto-generated method stub
		for(int i = 0; i < 4; i++) {
			//클릭됨
			double rx = x * i + 2;
			if(list[i] == 1) {
				before[i] = 1;
				gc.beginPath();
				gc.setFill(gradient);
				gc.moveTo(rx, y);
				gc.lineTo(rx, y + h);
				gc.lineTo(rx + w, y + h);
				gc.lineTo(rx + w, y);
				gc.fill();
				gc.closePath();
			}
		}
	}
	
	
	//그림자 사라지는 애미네이션
	public void update2(double d) {
		int speed = 2000;
		for(int i = 0; i < 4; i++) {
			if(before[i] == 1 && list[i] == 1 || beforeY[i] <= 0) {
				beforeY[i] = 300;
				before[i] = 0;
			} else if(before[i] == 1 && list[i] ==0) {
				beforeY[i] -= speed * d;
			}
		}
	}
	public void render2(GraphicsContext gc) {
		for(int i = 0; i < 4; i++) {
			double rx = x * i + 2;
			double ay = y;
			if(before[i] == 1 && list[i] == 0) {
				ay += 300 - beforeY[i];
				gc.beginPath();
				gc.setFill(gradient);
				gc.moveTo(rx, ay);
				gc.lineTo(rx, ay + beforeY[i]);
				gc.lineTo(rx + w, ay + beforeY[i]);
				gc.lineTo(rx + w, ay);
				gc.fill();
				gc.closePath();
			}
		}
	}
}


//판정 라인 색 63738c
