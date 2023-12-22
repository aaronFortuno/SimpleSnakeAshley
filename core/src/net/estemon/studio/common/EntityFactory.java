package net.estemon.studio.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.config.GameConfig;

public class EntityFactory {

    // attributes
    private final PooledEngine engine;

    // constructors
    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    // public methods
    public void createSnakeHead() {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;

        // bounds
        BoundsComponent  bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);

        // add to engine
        engine.addEntity(entity);
    }
}
