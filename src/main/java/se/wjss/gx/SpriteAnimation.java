package se.wjss.gx;

public class SpriteAnimation {
    public final int SIZE;
    public final int SPRITE_WIDTH, SPRITE_HEIGHT;
    private Sprite[] sprites;
	public int[] pixels;

    public SpriteAnimation(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize){
        int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
        int frame = 0;
		if(width == height) SIZE = width;
		else SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for(int y0 = 0; y0 < h ; y0++){
			int yp = yy + y0;
			for(int x0 = 0 ;x0 < w; x0++){
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height ; ya++){
			for (int xa = 0; xa < width ; xa++){
				int[] spritePixels = new int[spriteSize * spriteSize];
				for( int y0 =0; y0 < spriteSize; y0++){
					for( int x0=0; x0 < spriteSize; x0++){
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
    }
}
