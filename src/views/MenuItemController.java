package views;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import info.PlayerInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.JDBCUtil;

public class MenuItemController {
	@FXML
	private Label title;
	@FXML
	private Label score;
	@FXML
	private ImageView image;
	
	private PlayerInfo info;
	
	public void setData(PlayerInfo info) {
		this.info = info;
		title.setText(info.name);
		
		File file = new File(getClass().getResource("/resources/" + info.name + "/image.jpg").getFile());
		Image img = new Image(file.toURI().toString());
		
		image.setImage(img);
		
		//db
		
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM `score` WHERE musicName = ? ORDER BY score DESC LIMIT 0, 1";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info.name);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				this.score.setText("Best Score : " + rs.getInt("score") + "");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onAction() {
		//메인 게임으로 정보를 전달해주고 이동
		//info.id info.name info.speed 옮기기
		Main.app.addPane("MainGame");
		MainController mc = (MainController)Main.app.getController("MainGame");
		mc.setInfo(info);
		mc.startGame();
		Main.app.Maingame = true;
	}
}
