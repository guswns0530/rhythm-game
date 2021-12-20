package views;

import application.MainGame;
import info.PlayerInfo;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class MainController extends MasterController{
	
	public MainController mc;
	@FXML
	private Canvas canvas;
	@FXML
	private Canvas HpCanvas;
	@FXML
	private Canvas scoreCanvas;
	
	private MainGame game;
	
	public void startGame() {
		mc = this;
		game = new MainGame(canvas, HpCanvas, scoreCanvas, info);
	}

	@Override
	public void reset() {
		game = null;
	}
}
