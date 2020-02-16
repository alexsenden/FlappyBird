package flappyBird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Menu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	BufferedImage background;
	
	public Menu() {
		InputStream inputstream = ClassLoader.class.getResourceAsStream("/images/background.png");
		try {
			background = ImageIO.read(inputstream);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		frame
	}
	
	public BufferedImage bufferFrame() {
		Graphics g = this.getGraphics();
	}
	
	public void click() {
		
	}
}
