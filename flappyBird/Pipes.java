package flappyBird;

import java.awt.Rectangle;

public class Pipes {
	
	private static final int START_X = 660;
	private static final int OFFSET = 360;
	private static final int MAX_GAP = 130;
	private static final int MIN_GAP = 105;
	private static final int MIN_TOP_DIST = 40;
	private static final int VARIANCE = 120;
	
	private boolean passed;
	
	private Pipe top, bottom;
	
	public Pipes(int multiplyer) {
		passed = false;
		
		int x = START_X + multiplyer * OFFSET;
		int y = (int)(Math.random() * VARIANCE) + MIN_TOP_DIST;
		
		top = new Pipe(x, y - 450);
		bottom = new Pipe(x, y + MAX_GAP - (int)(Math.random() * (MAX_GAP - MIN_GAP)));
	}
	
	public Pipes() {
		int y = (int)(Math.random() * VARIANCE) + MIN_TOP_DIST;
		
		top = new Pipe(START_X, y - 450);
		bottom = new Pipe(START_X, y + MAX_GAP - (int)(Math.random() * (MAX_GAP - MIN_GAP)));
	}
	
	public void move() {
		top.move();
		bottom.move();
	}
	
	public Pipe getTop() {
		return top;
	}
	
	public Pipe getBottom() {
		return bottom;
	}
	
	public Rectangle[] getBounds() {
		return new Rectangle[] {top.getBounds(), bottom.getBounds()};
	}
	
	public void pass() {
		passed = true;
	}
	
	public boolean getPassed() {
		return passed;
	}
}
