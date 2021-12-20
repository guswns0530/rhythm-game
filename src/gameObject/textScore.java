package gameObject;

import java.io.File;

import application.MainGame;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class textScore extends GameObject{
	
	private double time;
	private int idx = 4;
	private String[] list = {"perfect", "great", "nice", "miss", ""};
	private String fontName;
	private double fontSize;
	LinearGradient[] gradient = new LinearGradient[5];
	
	public textScore() {
		fontName = "Aller_Bd.ttf";
		fontSize = 75.0;
		
		x = MainGame.game.getWidth() / 2;
		y = MainGame.game.getHeight() / 2 + 200;
		
		//#b574fa, #2f4bff, #19e6f9, #f3c75a
		setGradient();
	}
	
	public void setGradient() {
		//perfect
		Stop[] stop = new Stop[] { new Stop(0, Color.web("#b574fa")), new Stop(0.3, Color.web("#b574fa")), new Stop(0.6, Color.web("#19e6f9")), new Stop(1, Color.web("f3c75a")) };
		gradient[0] = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop);
		//great
		Stop[] stop1 = new Stop[] { new Stop(0, Color.web("#fdd734")), new Stop(1, Color.web("#ffc900"))};;
		gradient[1] = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop1);
		//nice
		Stop[] stop2 = new Stop[] { new Stop(0, Color.web("#6d7989")), new Stop(1, Color.web("#586474"))};;
		gradient[2] = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop2);
		//miss
		Stop[] stop3 = new Stop[] { new Stop(0, Color.web("#cb3328")), new Stop(1, Color.web("#c61714"))};;
		gradient[3] = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stop3);
	}

	@Override
	public void update(double d) {
		if(time > 0.3) {
			idx = 4;
			return;
		}
		time += d;
		if(time > 0.2) return;
		if(time > 0.1) {
			fontSize -= 2 * 100 * 0.01;
			return;
		}
		fontSize += 2 * 100 * 0.01;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFont(setFont(fontName, fontSize));
		gc.setFill(gradient[idx]);
		gc.fillText(list[idx], x, y);
	}

	public void setText(String str) {
		fontSize = 75.0;
		for(int i = 0; i < list.length; i++) {
			if(list[i].equals(str)) {
				idx = i;
			}
		}
		time = 0;
	}
	
	public Font setFont(String fontname, double fontSize) {
		File fontFile = new File(getClass().getResource("/font/" + fontname).getFile());
		Font font = Font.loadFont(fontFile.toURI().toString(), fontSize);
		
		return font;
	}
}
