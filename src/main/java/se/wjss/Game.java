package se.wjss;

import javax.swing.*;

import se.wjss.gx.Screen;
import se.wjss.input.Keyboard;
import se.wjss.components.level.Layer;
import se.wjss.components.level.Level;
import se.wjss.factories.LevelFactory;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
// import java.awt.Toolkit;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static String title = "Game Engine";
    public static int width = 300; // (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(); //Bredd på
                                   // själva applikationsfönstret
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private boolean running = false;
    private Thread gamethread;
    private JFrame frame;
    private Keyboard key;
    private Level level;

    private Screen screen;

    private BufferedImage image;
    private int[] pixels;

    private List<Layer> layerStack = new ArrayList<Layer>();

    public Game() {
        setSize();
        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();

        level = LevelFactory.createSpawnLevel();
        addLayer(level);
        addKeyListener(key);
    }

    public void setSize() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void addLayer(Layer layer) {
		layerStack.add(layer);
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
        } catch (InterruptedException e) {
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
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("    |    " + updates + " ups, " + frames + " fps");
                frame.setTitle(title + "\t|\t" + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    int x = 0;
    int y = 0;
    public void update() {
        key.update();
        if(key.up) y--;
        if(key.down) y++;
        if(key.left) x--;
        if(key.right) x++;

        for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).update();
		}
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();

        level.setScroll(x, y);

        for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).render(screen);
		}

        addPixelsToBuffer();
        draw(bs);
    }

    private void addPixelsToBuffer() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
    }

    private void draw(BufferStrategy bufferStrategy) {
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, 0, 0, width * scale, height * scale, null);
        // g.setColor(new Color(0xff00ff));
        // g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
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
