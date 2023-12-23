package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

public class SnakeComponent implements Component, Pool.Poolable {

    // constants
    private static final Logger log = new Logger(SnakeComponent.class.getSimpleName(), Logger.DEBUG);

    // attributes
    public Entity head;
    public final Array<Entity> bodyParts = new Array<Entity>();

    // public methods

    @Override
    public void reset() {
        log.debug("resetting snake component");
        head = null;
        bodyParts.clear();
        log.debug("reset done");
    }
}
