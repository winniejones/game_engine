package se.wjss;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Hello world!
 *
 */
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static String title="Game Engine";
    public static int width = 900;	//Bredd på själva applikationsfönstret
	public static int height = 500;
    public static int scale = 2;

    private boolean running = false;
    private Thread gamethread;
    private JFrame frame;

    public Game () {
        setSize();
        frame = new JFrame();
    }

    public void setSize() {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);
    }

    public synchronized void start() {
        running = true;
        gamethread = new Thread(this, "Display");
        gamethread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            gamethread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    } 

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
		double delta = 0;
        int frames = 0;
        int updates = 0;
        while(running) {
            long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
            while(delta >= 1){
                update();
				updates++;
				delta--;
			}
			render();
            frames++;
            if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				System.out.println("    |    "+updates + " ups, "+ frames + " fps" );
				updates =0;
				frames=0;
			}
        }
        stop();
    }

    public void update(){
	}

    public void render(){
        BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}

        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(0xff00ff));
        g.drawRect(0, 0, getWidth(), getHeight());
        g.dispose();
		bs.show();
    }

    public static void main( String[] args ) {
        Game game = new Game();
        game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
        game.start();
    }

}
