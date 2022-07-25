package se.wjss.components;

import se.wjss.gx.Screen;
import se.wjss.gx.Sprite;

public class Tile {
    public Sprite sprite;
    public boolean isSolid;

    public Tile(Sprite sprite, boolean isSolid) {
		this.sprite = sprite;
        this.isSolid = isSolid;
	}

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, sprite.SIZE, sprite.SIZE, sprite.pixels);
	}

    public boolean solid() {
		return isSolid;
	}
}
