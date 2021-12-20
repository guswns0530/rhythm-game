package gameObject;

import java.io.File;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScoreText extends GameObject{
	private double cw;
	private double ch;
	private GraphicsContext gc;
	private double score;
	private double beforeScore;
	private String opScore;
	public int time;
	public double plus;
	private Combo cb;
	public int totalNotes;
	
	
	private Font font;
	
	public double getScore() {
		return score;
	}
	
	public ScoreText(Canvas canvas) {
		File fontFile = new File(getClass().getResource("/font/Aller_Bd.ttf").getFile());
		font = Font.loadFont(fontFile.toURI().toString(), 55);
		
		cw = canvas.getWidth();
		ch = canvas.getHeight();
		
		gc = canvas.getGraphicsContext2D();
		
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BASELINE.CENTER);
		
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		
		score = 0;
		beforeScore = 0;
		opScore = "0000000";
		time = 0;
		plus = 0;
	}

	@Override
	public void update(double d) {
		if(score <= beforeScore) {
			beforeScore = score;
			return;
		}
				
		time++;
		if(time == 2) {
			beforeScore+= plus/40.0;
			time =0;
		}
		String str = (int)Math.floor(beforeScore) + "";
		opScore = "0000000";
		opScore = opScore.substring(str.length()) + str;
	}

	public void render() {
		gc.clearRect(0, 0, cw, ch);
		gc.fillText(opScore, cw/2 + 30, ch /2);
	}
	
	
	public void score(double HitValue) {
		double BaseScore = (1000000 / totalNotes) * (HitValue / 250);
		double score = BaseScore;
		
		this.score += score;
		plus = this.score - beforeScore;
	}

	
	
	//안쓸거임
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

}
