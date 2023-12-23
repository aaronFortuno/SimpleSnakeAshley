package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

import net.estemon.studio.common.Direction;

public class DirectionComponent implements Component, Pool.Poolable {

    // attributes
    public Direction direction = Direction.RIGHT;

    // public methods
    public boolean isLeft() { return direction.isLeft(); }
    public boolean isRight() { return direction.isRight(); }
    public boolean isUp() { return direction.isUp(); }
    public boolean isDown() { return direction.isDown(); }

    @Override
    public void reset() {
        direction = Direction.RIGHT;
    }

    public boolean isOpposite(Direction newDirection) {
        return direction.isOpposite(newDirection);
    }
}
