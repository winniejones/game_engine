package se.wjss.gx;

import java.util.Random;

public class Screen {
    public final int MAP_SIZE = 16;
    public int width, height;
    public int[] pixels;
    public int xOffset, yOffset;
    
    public int[] tiles = new int [MAP_SIZE * MAP_SIZE];
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i =0; i<MAP_SIZE*MAP_SIZE;i++){
			tiles [i] = random.nextInt(0xffffff);
			tiles [0] = 0;
		}
    }

    public void clear(){
		for (int i = 0; i< pixels.length; i++){
			pixels[i]=0;
		}
	}

    public void renderTile(int xp, int yp, int tileX, int tileY, int[] tilePixels){
        xp -= xOffset;
        yp -= yOffset;
        for(int y =0; y< tileY; y++){
            int ya = y + yp;
            for(int x =0; x < tileX; x++){
                int xa = x + xp;
                if(xa < - tileY || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya*width]= tilePixels[x + y * tileX];
            }
        }
    }

    public void renderTest(){
        for(int y =0; y < height; y++){
            for(int x =0; x < width; x++){
                int tileIndex = (x * 32) + (y * 32) * 64;
                pixels[x + y*width]= tiles[tileIndex];
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
