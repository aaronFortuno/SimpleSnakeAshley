package net.estemon.studio.config;

public class GameConfig {

    private GameConfig() {} // not instantiable

    // constants
    public static final float Y_OFFSET = 2;
    public static final float WIDTH = 960f; // px
    public static final float HEIGHT = 480f; // px

    public static final float HUD_WIDTH = 960f;
    public static final float HUD_HEIGHT = 480f;

    public static final float WORLD_WIDTH = 30f;
    public static final float WORLD_HEIGHT = 15f;

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;

    public static final float SNAKE_SIZE = 1f;
    public static final float MOVE_TIME = 0.1f;
    public static final float SNAKE_SPEED = 1f;

    public static final float COIN_SIZE = 1f;
    public static final int COIN_SCORE = 20;

    public static final float MAX_Y = WORLD_HEIGHT - Y_OFFSET;
}
