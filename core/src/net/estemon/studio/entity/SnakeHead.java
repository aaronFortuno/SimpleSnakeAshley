package net.estemon.studio.entity;

import net.estemon.studio.config.GameConfig;

@Deprecated
public class SnakeHead extends EntityBase {

    // constructors
    public SnakeHead() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SIZE);
    }

    // public methods
    public void updateX(float amount) {
        x += amount;
        updateBounds();
    }

    public void updateY(float amount) {
        y += amount;
        updateBounds();
    }
}
