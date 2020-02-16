package flappyBird;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KBListeners extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
       		case KeyEvent.VK_SPACE:
	       		if(FlappyGame.canFly) {
	       			Main.space = true;
	       		}
	       		break;
			}
	}
	    
    @Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
	    		FlappyGame.canFly = true;
	    		break;
		}
    }
}
