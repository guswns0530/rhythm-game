package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.JDBCUtil;

public class EndGameController extends MasterController{
	
	@FXML
	private VBox pane;
	@FXML
	private Label score;
	
	@FXML
	public void start() {
		reset();
		score.setText(info.score + "");
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM `score` WHERE musicName = ? ORDER BY score DESC LIMIT 0, 5";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info.name);
			ResultSet rs = pstmt.executeQuery();
			
			int count = 1;
			
			while(rs.next()) {
				if(rs.getInt("score") == 0) return;
				makeFXML(count++, rs.getString("userName"), rs.getInt("score"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void makeFXML(int count, String id, int score) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Ranking.fxml"));
			AnchorPane item = loader.load();
			RankingController rc = loader.getController();
			System.out.println(count + " " +  id + " " + score);
			rc.setItem(count, id, score);
			pane.getChildren().add(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reGame() {
		EndGameController ec = (EndGameController)Main.app.getController("EndGame");
		Main.app.removePane(ec.getRoot());
		MainController mc = (MainController)Main.app.getController("MainGame");
		mc.reset();
		mc.startGame();
		Main.app.addPane("MainGame");
		Main.app.Maingame = true;
	}
	
	public void goMenu() {
		EndGameController ec = (EndGameController)Main.app.getController("EndGame");
		Main.app.removePane(ec.getRoot());
		MenuController mc = (MenuController)Main.app.getController("menu");
		mc.reset();
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		pane.getChildren().clear();
		
	}

	
}
