package net.estemon.studio.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.DirectionComponent;
import net.estemon.studio.component.MovementComponent;
import net.estemon.studio.component.PlayerComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.SnakeComponent;
import net.estemon.studio.config.GameConfig;

public class EntityFactory {

    // attributes
    private final PooledEngine engine;

    // constructors
    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    // public methods
    public Entity createSnake() {
        // snake
        SnakeComponent snake = engine.createComponent(SnakeComponent.class);
        snake.head = createSnakeHead();

        // entity
        Entity snakeEntity = engine.createEntity();
        snakeEntity.add(snake);

        engine.addEntity(snakeEntity);

        return snakeEntity;
    }

    public Entity createSnakeHead() {
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

        // direction
        DirectionComponent direction = engine.createComponent(DirectionComponent.class);

        // movement
        MovementComponent movement = engine.createComponent(MovementComponent.class);

        // player
        PlayerComponent player = engine.createComponent(PlayerComponent.class);

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(direction);
        entity.add(movement);
        entity.add(player);

        // add to engine
        engine.addEntity(entity);

        return entity;
    }
}