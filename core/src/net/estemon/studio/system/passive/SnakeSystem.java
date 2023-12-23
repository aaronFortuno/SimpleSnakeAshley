package net.estemon.studio.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Logger;

import net.estemon.studio.component.SnakeComponent;
import net.estemon.studio.utils.Mappers;

public class SnakeSystem extends EntitySystem implements EntityListener {

    // constants
    private static final Logger log = new Logger(SnakeSystem.class.getSimpleName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(
            SnakeComponent.class
    ).get();

    //

    // public methods
    @Override
    public boolean checkProcessing() {
        // returning false makes this system passive: update will NOT be called
        return false;
    }

    @Override
    public void update(float deltaTime) {
        // not processing anything
    }

    @Override
    public void addedToEngine(Engine engine) {
        log.debug("SnakeSystem addedToEngine adding listener");
        engine.addEntityListener(FAMILY, this);

    }

    @Override
    public void removedFromEngine(Engine engine) {
        log.debug("SnakeSystem removedFromEngine");
        engine.removeEntityListener(this);
    }

    @Override
    public void entityAdded(Entity entity) {
        log.debug("entityAdded: " +entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        log.debug("entityRemoved: " + entity);
        SnakeComponent snake = Mappers.SNAKE.get(entity);

        Engine engine = getEngine();
        engine.removeEntity(snake.head);

        for (Entity bodyPart : snake.bodyParts) {
            engine.removeEntity(bodyPart);
        }
    }
}
