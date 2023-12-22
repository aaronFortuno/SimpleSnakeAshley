package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PositionComponent implements Component, Pool.Poolable {

    // attributes
    public float x;
    public float y;

    // public methods
    @Override
    public void reset() {
        x = 0f;
        y = 0f;
    }
}
