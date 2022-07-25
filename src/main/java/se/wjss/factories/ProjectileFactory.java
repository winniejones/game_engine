package se.wjss.factories;

import se.wjss.gx.Sprite;
import se.wjss.gx.SpriteSheet;

public class ProjectileFactory {
    private static SpriteSheet projectile = new SpriteSheet("/sheets/projectiles/wizard.png", 48);
    
    public static Sprite projectile_wizard = new Sprite(16, 0, 0, projectile);
    public static Sprite projectile_arrow = new Sprite(16, 1, 0, projectile);
}
