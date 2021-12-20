package application;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputSystem {
	private Map<KeyCode, Boolean> map = new HashMap<>();
	private KeyCode KeyList[] = {KeyCode.D, KeyCode.F, KeyCode.J, KeyCode.K};
	
	
	//나중에 삭제
	public InputSystem() {
		for(KeyCode k : KeyList) {
			map.put(k, false);
		}
	}
	
	public void keyPressHandle(KeyEvent e) {
		for(int i = 0; i < 4; i++) {
			if(KeyList[i] == e.getCode() && !map.get(e.getCode())) {
//				System.out.println((i + 1) +":" + MainGame.game.getTime());
				MainGame.game.nodeList.removeNode(i + 1);
				break;
			}
		}
		map.put(e.getCode(), true);
	}
	public void keyReleaseHandle(KeyEvent e) {
		map.put(e.getCode(), false);
	}
	
	public int[] getKey() {
		int[] clickList = new int[7];
		
		
		for(int i = 0; i < 4; i++) {
			if(map.get(KeyList[i]) == true) {
				clickList[i] = 1;
			}
		}
		 
		return clickList;
	}
	public boolean getBoolean(int idx) {
		KeyCode key = KeyList[idx-1];
		return map.get(key);
	}
	
	public void setBoolean(int idx, boolean t) {
		KeyCode key = KeyList[idx-1];
		map.put(key, t);
	}
}
