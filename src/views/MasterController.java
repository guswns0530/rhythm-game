package views;

import info.PlayerInfo;
import javafx.scene.layout.Pane;

public abstract class MasterController {
	private Pane root;
	public PlayerInfo info;
	
	public Pane getRoot() {
		return root;
	}
	
	public void setRoot(Pane root) {
		this.root = root;
	}
	
	public void setInfo(PlayerInfo info) {
		this.info = info;
	}
	
	public abstract void reset();
}