package gameObject;

import java.util.ArrayList;

import application.InputSystem;
import application.MainGame;
import javafx.scene.canvas.GraphicsContext;

public class Score extends GameObject{
	
	private ArrayList<Node> nodeList;
	private ArrayList<Double> before;
	private double time;
	private double judgment;
	private InputSystem is;
	private Combo combo;
	
	
	public Score() {
		combo = MainGame.game.combo;
		time = 0;
		nodeList = new ArrayList<>();
		before = new ArrayList<>();
		is = MainGame.game.inputSystem;
		 
		judgment = MainGame.game.getHeight() - 200 - 25;
	}
	
	public void set(Node node, double before) {
		nodeList.add(node);
		this.before.add(before);
	}
	
	public void remove(int i) {
		nodeList.remove(i);
		before.remove(i);
	}

	@Override
	public void update(double d) {
		// TODO Auto-generated method stub
		if(!MainGame.game.update ) {
			return; 
		}
		
		time += d;
		
		//판정부터
		for(int i = 0; i < nodeList.size(); i++ ) {
			Node node =  nodeList.get(i);
			double before = this.before.get(i);
			double after = judgment - node.maxHeight;
			if(time >= 0.1) {
				combo.plus();
				time = 0;
			}
			if(!is.getBoolean(node.idx) || !node.active) {
				if(after  >= 250) {
					node.isClick = false;
					node.isFirst = false;
					node.isSecond = false;
					
					remove(i);
					return;
				} else {
					double hap = Math.abs(before) + Math.abs(after);
					
					System.out.println("before, after : "  + before + " " + after);
					System.out.println("합 : " + hap);
				}
				//검사
				remove(i);
				//삭제 끝마침
			}
			
			//노트 점수 구문s
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	} 

}