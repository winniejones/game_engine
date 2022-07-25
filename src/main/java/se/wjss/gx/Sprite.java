package se.wjss.gx;

public class Sprite {
    public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE=size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet = sheet;
		loadPixels();
	}

    public Sprite(int[] pixels, int width, int height){
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		
		for(int i = 0; i < pixels.length; i++){
			this.pixels[i] = pixels[i];	
		}	
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

    private void loadPixels(){
		for(int y =0; y< height; y++){
			for(int x=0; x<width;x++){
				pixels[x+y*width] = sheet.pixels[(x+this.x) + (y+this.y)*sheet.SPRITE_WIDTH];
			}
		}
	}

	private void setColour(int colour) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = colour;
		}
	}
}
