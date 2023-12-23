package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

public class SnakeComponent implements Component, Pool.Poolable {

    // attributes
    public Entity head;
    public final Array<Entity> bodyParts = new Array<Entity>();

    // public methods
    public boolean hasBodyParts() {
        return bodyParts.size > 0;
    }

    @Override
    public void reset() {
        head = null;
        bodyParts.clear();
    }
}
