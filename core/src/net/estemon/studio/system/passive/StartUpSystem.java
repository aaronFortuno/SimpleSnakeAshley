package net.estemon.studio.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

public class StartUpSystem extends EntitySystem {

    // attributes
    private EntityFactorySystem factory;

    // constructors

    public StartUpSystem() {
    }

    // public methods
    @Override
    public void update(float deltaTime) {
        // not processing
    }

    @Override
    public boolean checkProcessing() {
        // not processing
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        factory = engine.getSystem(EntityFactorySystem.class);
        startUp();
    }

    // private methods
    private void startUp() {
        factory.createBackground();
        factory.createCoin();
        factory.createSnake();
    }
}
