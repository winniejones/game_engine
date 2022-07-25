package se.wjss.factories;

import se.wjss.components.level.Level;

public class LevelFactory {
    public static Level createSpawnLevel() {
        return new Level("/levels/spawn.png");
    }
}
