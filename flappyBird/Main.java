package flappyBird;

import javax.swing.JFrame;

public class Main {
	
	public static final boolean DEBUG = false;
	
	public static final int HEIGHT = 450;
	public static final int WIDTH = 600;
	
	public static String version = "flappyBird";
	
	public static final int TPS = 30;
	public static final int FPS = 120;
	
	public static boolean space = false;
	
	public static Menu menu;
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Flappy Bird");
		
		window.setBounds(0, 0, WIDTH, HEIGHT);
		window.setLocation(100, 100);
		window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        FlappyGame game = new FlappyGame();
        
        window.add(game);
        window.setVisible(true);
        
        KBListeners kblistener = new KBListeners();
        MListeners mlistener = new MListeners();
        
        window.setFocusable(true);
        window.requestFocus();
        window.addKeyListener(kblistener);
        window.addMouseListener(mlistener);
        
        boolean gameActive = true;
        
        long lastFrame, lastTick;
        lastFrame = lastTick = System.nanoTime();
        long ctime;
        
        int ticks = 0;
        int frames = 0;
        
        long lastTimeMillis = System.currentTimeMillis();
        long ctimeMillis;
        
        while(true) {
	        while(gameActive) {
	        	ctime = System.nanoTime();
	            if(ctime - lastTick > 1000000000.0 / TPS) { //check if enough time has passed to tick the game again
	                lastTick = ctime;
	                
		        	game.move();
		        	game.updateScore();
		        	
		        	if(game.checkIntersect()) {
		        		break;
		        	}
		        	
		        	ticks++;
	            }
	            
	            if(ctime - lastFrame > 1000000000.0 / FPS) {
	            	lastFrame = ctime;
	            	
	            	game.paintComponent(game.getGraphics());
	            	
	            	frames++;
	            }
	            
	            ctimeMillis = System.currentTimeMillis();
	            //every second
	            if(ctimeMillis - lastTimeMillis > 1000) {
	                lastTimeMillis = ctimeMillis;
	                
	                //if debug info enables, print out how many times the game 
	                //ticked in the last second
	            	System.out.println("TPS: " + ticks + " FPS: " + frames);
	                
	                //reset the ticks counter for the next second
	                ticks = frames = 0;
	            }
	        }
	        window.remove(game);
	        game = new FlappyGame();
	        window.add(game);
	        window.setVisible(true);;
        }
	}
}
