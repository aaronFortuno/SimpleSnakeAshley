package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.WorldWrapComponent;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.utils.Mappers;

public class WorldWrapSystem extends IteratingSystem {

    // constants
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            WorldWrapComponent.class
    ).get();

    // constructors
    public WorldWrapSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);

        // check x bounds
        if (position.x >= GameConfig.WORLD_WIDTH) {
            position.x = 0;
        } else if (position.x < 0) {
            position.x = GameConfig.WORLD_WIDTH - GameConfig.SNAKE_SPEED;
        }

        // check y bounds
        if (position.y >= GameConfig.MAX_Y) {
            position.y = 0;
        } else if (position.y < 0) {
            position.y = GameConfig.MAX_Y - GameConfig.SNAKE_SPEED;
        }
    }
}
