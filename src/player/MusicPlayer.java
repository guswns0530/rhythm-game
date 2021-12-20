package player;

import application.MainGame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
	private MediaPlayer player;
	private Media file;
	
	public MusicPlayer() {
	}
	
	public void setMusic(String filename) {
		file = new Media(
				getClass().getResource("/resources/" + filename).toString());
		player = new MediaPlayer(file);
		player.setVolume(0.5);
	}
	
	public double getVolume() {
		return player.getVolume();
	}
	
	public void setVolume(double value) {
		player.setVolume(value);
	}
	
	public void play() {
		player.play();
	}
	
	public void setTime(Double nowMusicTime) {
		player.currentTimeProperty().addListener((a, b, c) -> {
			MainGame.game.totalTime = c.toSeconds();
		});
	}
	
	public void stop() {
		player.stop();
	}
	
	public void reset() {
//		player.set
	}
}
