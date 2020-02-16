package flappyBird;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MListeners extends MouseAdapter{
	public void mouseClicked(MouseEvent e) {
		if(Main.menu != null) {
			Main.menu.click(e.getPoint());
		}
	}
}
