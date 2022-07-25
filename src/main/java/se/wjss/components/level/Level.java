package se.wjss.components.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import se.wjss.components.Tile;
import se.wjss.factories.TileFactory;
import se.wjss.gx.Screen;

public class Level extends Layer {
    private int width, height;
    private int[] tiles;
    // private final int TILE_SIZE = 16;
    private int xScroll, yScroll;

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    public void setScroll(int xScroll, int yScroll) {
		this.xScroll = xScroll;
		this.yScroll = yScroll;
	}

    public void render(Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
        for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
    }

    private void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(Level.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }
    }

    private void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y);
            }
        }
    }

    private Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return TileFactory.void_tile;
        switch (tiles[x + y * width]) {
            case LevelUtil.col_spawn_floor:
                return TileFactory.spawn_floor;
            case LevelUtil.col_spawn_grass:
                return TileFactory.spawn_grass;
            case LevelUtil.col_spawn_wall1:
                return TileFactory.spawn_wall1;
            case LevelUtil.col_spawn_wall2:
                return TileFactory.spawn_wall2;
            default:
                return TileFactory.void_tile;
        }
    }

}
