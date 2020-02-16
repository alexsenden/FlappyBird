package flappyBird;

public class Bird {
	
	private int y;
	private int yvel;
	
	private boolean grounded;
	
	public Bird() {
		grounded = false;
		y = 300;
		yvel = 0;
	}
	
	public void move() {
		if(Main.space) {
			yvel = 12;;
			FlappyGame.canFly = false;
			Main.space = false;
		}
		yvel -= 1;
		y += yvel;
		
		if(y < 140) {
			y = 140;
			yvel = 0;
			grounded = true;
		}
		else {
			grounded = false;
		}
		
		if(y > 450) {
			y = 450;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public int getYvel() {
		return yvel;
	}
	
	public boolean getGrounded() {
		return grounded;
	}
}
