package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Logger;

import net.estemon.studio.component.DirectionComponent;
import net.estemon.studio.component.MovementComponent;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.utils.Mappers;

public class DirectionSystem extends IteratingSystem {

    // constants
    private static final Family FAMILY = Family.all(
            DirectionComponent.class,
            MovementComponent.class
    ).get();

    // constructors
    public DirectionSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent direction = Mappers.DIRECTION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        // reset speed to 0
        movement.xSpeed = 0f;
        movement.ySpeed = 0f;

        // based on direction set speed
        if (direction.isRight()) {
            movement.xSpeed = GameConfig.SNAKE_SPEED;
        } else if (direction.isLeft()) {
            movement.xSpeed = -GameConfig.SNAKE_SPEED;
        } else if (direction.isUp()) {
            movement.ySpeed = GameConfig.SNAKE_SPEED;
        } else if (direction.isDown()) {
            movement.ySpeed = -GameConfig.SNAKE_SPEED;
        }
    }
}