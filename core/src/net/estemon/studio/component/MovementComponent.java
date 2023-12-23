package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MovementComponent implements Component, Pool.Poolable {

    // attributes
    public float xSpeed;
    public float ySpeed;

    // public methods
    @Override
    public void reset() {
        xSpeed = 0f;
        ySpeed = 0f;
    }
}
