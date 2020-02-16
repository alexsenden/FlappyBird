package flappyBird;

public class Ground {
	
	private static final int SPEED = 3;
	
	private int x;
	
	public Ground(int x) {
		this.x = x;
	}
	
	public void move() {
		x -= SPEED;
	}
	
	public int getX() {
		return x;
	}
}
