package views;

import java.util.ArrayList;

import application.Main;
import info.PlayerInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuController extends MasterController {
	
	public VBox itemPane;
	public Pane removePane;
	public TextField text;
	public Button userId;
	
	private ArrayList<PlayerInfo> list;

	@FXML
	public void initialize() {
		list = new ArrayList<>();
		appendList();
		
		addItem();
	}
	
	public void input() {
		if(text.getText().trim() == "") return;
		
		Main.app.userId = text.getText();
		userId.setText(Main.app.userId);
//		AnchorPane p = (AnchorPane)itemPane.getParent();
//		p.getChildren().remove(removePane);
	}
	
	public void appendList() {
		//곡 리스트
		list.add(new PlayerInfo("flower_dance", 1.3));
		list.add(new PlayerInfo("neru_lost_one_no_goukoku", 1.5));
		list.add(new PlayerInfo("Amphisbaena", 1.4));
	}
	
	public void addItem() {
		for(PlayerInfo info : list) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/MenuItem.fxml"));
			
			try {
				AnchorPane pane = loader.load();
				MenuItemController mc = loader.getController();
				mc.setData(info);
				
				
				itemPane.getChildren().add(pane);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void reset() {
		list.clear();
		addItem();
	}
}
