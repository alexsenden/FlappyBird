package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FlappyGame extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Bird flappy;
	
	public static BufferedImage background, bird, top, bottom, ground;
	
	private int score;
	
	public static Font scoreFont;
	public static FontMetrics fmets;
	
	public static boolean canFly = true;
	
	private ArrayList<Pipes> pipelist = new ArrayList<Pipes>();
	private ArrayList<Ground> grounds = new ArrayList<Ground>();

	public FlappyGame() {
		InputStream inputStream = ClassLoader.class.getResourceAsStream("/images/background.png");
		try {
			background = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputStream = ClassLoader.class.getResourceAsStream("/images/bird.png");
		try {
			bird = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputStream = ClassLoader.class.getResourceAsStream("/images/top.png");
		try {
			top = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputStream = ClassLoader.class.getResourceAsStream("/images/bottom.png");
		try {
			bottom = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inputStream = ClassLoader.class.getResourceAsStream("/images/ground.png");
		try {
			ground = ImageIO.read(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//"/font/Thirteen-Pixel-Fonts.ttf"
		inputStream = ClassLoader.class.getResourceAsStream("/fonts/Thirteen-Pixel-Fonts.ttf");
		try {
			File tempFile = File.createTempFile("tpfont", ".ttf");
			Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			scoreFont = Font.createFont(Font.TRUETYPE_FONT, tempFile).deriveFont(48f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fmets = this.getFontMetrics(scoreFont);

        score = 0;
        
		flappy = new Bird();
		
		pipelist.add(new Pipes());
		pipelist.add(0, new Pipes(1));
		
		grounds.add(new Ground(0));
		grounds.add(0, new Ground(608));
	}
	
	public void paintComponent(Graphics g) {
		BufferedImage frame = bufferFrame();
		g.drawImage(frame, 0, 0, this);
	}
	
	public BufferedImage bufferFrame() {
		BufferedImage buffImg = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.createGraphics();
        
        g.drawImage(background, 0, 0, this);
        
        for(Ground gr : grounds) {
        	g.drawImage(ground, gr.getX(), 338, this);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(scoreFont);
        g.drawString(Integer.valueOf(score).toString(), (600 - (fmets.stringWidth(Integer.toString(score))))/2, 150);
        
        double rotationRequired = Math.toRadians(flappy.getYvel() * -4);
        double locationX = bird.getWidth() / 2;
        double locationY = bird.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        
        g.drawImage(op.filter(bird, null), 250, 450 - flappy.getY(), this);
        
        for(Pipes p : pipelist) {
        	g.drawImage(top, p.getTop().getX(), p.getTop().getY(), this);
        	g.drawImage(bottom, p.getBottom().getX(), p.getBottom().getY(), this);
        }
        
        //hitboxes
        if(Main.DEBUG) {
        	g.setColor(Color.MAGENTA);
	        for(Pipes p : pipelist) {
				for(Rectangle r : p.getBounds()) {
					g.drawRect(r.x, r.y, r.width, r.height);
				}
			}
	        
	        g.drawRect(250, 450 - flappy.getY(), 40, 30);
	        
        }
        
        return buffImg;
	}
	
	public void move() {
		flappy.move();
		
		for(Pipes p : pipelist) {
			p.move();
		}
		
		if(pipelist.get(1).getTop().getX() < -60) {
			pipelist.add(0, new Pipes());
			pipelist.remove(2);
		}
		
		for(Ground gr : grounds) {
			gr.move();
		}
		
		if(grounds.get(1).getX() < -607) {
			grounds.add(0, new Ground(grounds.get(0).getX() + 608));
			grounds.remove(2);
		}
	}
	
	public boolean checkIntersect() {
		if(flappy.getGrounded()) {
			return true;
		}
		
		for(Pipes p : pipelist) {
			for(Rectangle r : p.getBounds()) {
				if(r.intersects(new Rectangle(250, 450 - flappy.getY(), 40, 30))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void updateScore() {
		for(Pipes p : pipelist) {
			if(!p.getPassed() && p.getTop().getX() < 250){
				p.pass();
				score++;
			}
		}
	}
}
