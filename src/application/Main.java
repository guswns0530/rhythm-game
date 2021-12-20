package application;
	
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import views.MasterController;


public class Main extends Application {
	
	public static Main app;
	
	private StackPane mainPage;
	
	public String userId = "guest";
	
	public boolean Maingame = false;
	
	private Map<String, MasterController> controllerMap = new HashMap<>();
	
	//컨트롤러 가져오기
	public MasterController getController(String name) {
		return controllerMap.get(name);
	}
	
	@Override
	public void start(Stage primaryStage) {
		//바인딩
		app = this;
//		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
//		
//		double h = visualBounds.getHeight();
		try {
			
			//게임화면
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/MainLayout.fxml"));
			AnchorPane pane = loader.load();
			MasterController mc = loader.getController();
			mc.setRoot(pane);
			controllerMap.put("MainGame", mc);
			
			FXMLLoader menuLoader = new FXMLLoader();
			menuLoader.setLocation(getClass().getResource("/views/Menu.fxml"));
			mainPage = menuLoader.load();
			MasterController menuc = menuLoader.getController();
			controllerMap.put("menu", menuc);
			
			FXMLLoader endLoader = new FXMLLoader();
			endLoader.setLocation(getClass().getResource("/views/EndGame.fxml"));
			AnchorPane endGame = endLoader.load();
			MasterController ec = endLoader.getController();
			ec.setRoot(endGame);
			controllerMap.put("EndGame", ec);
			
			Scene scene = new Scene(mainPage);
			
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, e-> {
				if(Maingame == true) {
					MainGame.game.inputSystem.keyPressHandle(e);
				}
			});
			scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
				if(Maingame == true) {
					MainGame.game.inputSystem.keyReleaseHandle(e);
				}
			});
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("rhythm_game");
			primaryStage.setScene(scene);	
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removePane(Pane pane) {
		mainPage.getChildren().remove(pane);
	}
	
	public void addPane(String name) {
		MasterController mc = controllerMap.get(name);
		Pane pane = mc.getRoot();
		mainPage.getChildren().add(pane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
