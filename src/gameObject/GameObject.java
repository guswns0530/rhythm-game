package gameObject;

import application.MainGame;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
	public double x;
	public double y;
	public double w;
	public double h;
	
	public abstract void update(double d);
	public abstract void render(GraphicsContext gc);
}
