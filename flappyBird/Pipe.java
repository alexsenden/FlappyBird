package flappyBird;

import java.awt.Rectangle;

public class Pipe {
	
	private static final int SPEED = 3;
	
	private int x;
	private int y;
	
	public Pipe(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void move() {
		x -= SPEED;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 60, 450);
	}
}
