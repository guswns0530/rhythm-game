package gameObject;

import javafx.scene.canvas.GraphicsContext;

public class Combo extends GameObject{
	
	public Boolean isPrint = false;
	private int combo;
	private int before;
	
	public Combo() {
		combo = 0;
	}
	
	public void plus() {
		isPrint = true;
		combo++;
		System.out.println(combo);
	}
	public void reset() {
		combo = 0;
		before = combo;
		System.out.println(combo);
	}
	
	public int get() {
		return combo; 
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if(!isPrint) return; 
	}

	@Override
	public void update(double d) {
		
	}
}
