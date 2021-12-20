package views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RankingController {
	int rank;
	String id;
	int score;
	
	@FXML
	private Label Lrank;
	@FXML
	private Label Lid;
	@FXML
	private Label Lscore;
	
	public void setItem(int rank, String id, int score) {
		this.rank = rank;
		this.id = id;
		this.score = score;
		
		append();
	}
	public void append() {
		Lrank.setText(rank + ".");
		Lid.setText(id + "");
		Lscore.setText(score + "");
	}
}
