package se.wjss.factories;

import se.wjss.components.Tile;
import se.wjss.gx.Sprite;
import se.wjss.gx.SpriteSheet;

public class TileFactory {
    private static SpriteSheet tiles = new SpriteSheet("/sheets/spritesheet1.png", 256);
    private static SpriteSheet spawn_level = new SpriteSheet("/sheets/spawn_lvl.png", 48);

    private static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    public static Tile createTileFromSheet(int x, int y, int size, SpriteSheet sheet, boolean bool) {
        return new Tile(new Sprite(size, x, y, sheet), bool);
    }

    public static Tile createTileFromSprite(Sprite sprite) {
        return new Tile(sprite, false);
    }

    public static Tile void_tile = createTileFromSprite(voidSprite);

    public static Tile grass_tile = createTileFromSheet(0, 5, 16, tiles, false);
    public static Tile flower_tile = createTileFromSheet(1, 0, 16, tiles, false);
    public static Tile rock_tile = createTileFromSheet(2, 0, 16, tiles, true);

    public static Tile spawn_grass = createTileFromSheet(0, 0, 16, spawn_level, false);
	public static Tile spawn_hedge = createTileFromSheet(1, 0, 16, spawn_level, true);
	public static Tile spawn_water = createTileFromSheet(2, 0, 16, spawn_level, true);
	public static Tile spawn_wall1 = createTileFromSheet(0, 1, 16, spawn_level, true);
	public static Tile spawn_wall2 = createTileFromSheet(0, 2, 16, spawn_level, true);
	public static Tile spawn_floor = createTileFromSheet(1, 1, 16, spawn_level, false);
}