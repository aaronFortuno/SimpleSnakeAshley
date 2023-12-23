package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import net.estemon.studio.common.Direction;
import net.estemon.studio.component.DirectionComponent;
import net.estemon.studio.component.PlayerComponent;
import net.estemon.studio.utils.Mappers;

public class PlayerControlSystem extends IteratingSystem {

    // constants
    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    // constructors
    public PlayerControlSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        DirectionComponent direction = Mappers.DIRECTION.get(entity);

        if (leftPressed) {
            updateDirection(direction, Direction.LEFT);
        } else if (rightPressed) {
            updateDirection(direction, Direction.RIGHT);
        } else if (upPressed) {
            updateDirection(direction, Direction.UP);
        } else if (downPressed) {
            updateDirection(direction, Direction.DOWN);
        }
    }

    // private methods
    private void updateDirection(DirectionComponent directionComponent, Direction newDirection) {
        if (!directionComponent.isOpposite(newDirection)) {
            directionComponent.direction = newDirection;
        }
    }
}
