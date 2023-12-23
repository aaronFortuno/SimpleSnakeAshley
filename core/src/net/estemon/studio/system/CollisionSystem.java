package net.estemon.studio.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.CoinComponent;
import net.estemon.studio.component.SnakeComponent;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.utils.Mappers;

public class CollisionSystem extends IntervalSystem {

    // constants
    private static final Family SNAKE_FAMILY = Family.all(SnakeComponent.class).get();
    private static final Family COIN_FAMILY = Family.all(CoinComponent.class).get();

    public CollisionSystem() {
        super(GameConfig.MOVE_TIME);
    }

    @Override
    protected void updateInterval() {
        Engine engine = getEngine();
        ImmutableArray<Entity> snakes = engine.getEntitiesFor(SNAKE_FAMILY);
        ImmutableArray<Entity> coins = engine.getEntitiesFor(COIN_FAMILY);

        // head <-> coin
        for (Entity snakeEntity : snakes) {
            for (Entity coinEntity : coins) {
                CoinComponent coin = Mappers.COIN.get(coinEntity);
                SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

                if (coin.available && overlaps(snake.head, coinEntity)) {
                    // mark coin as not available
                    coin.available = false;
                }
            }
        }
    }

    // private methods
    private static boolean overlaps(Entity first, Entity second) {
        BoundsComponent firstBounds = Mappers.BOUNDS.get(first);
        BoundsComponent secondBounds = Mappers.BOUNDS.get(second);

        return Intersector.overlaps(firstBounds.rectangle, secondBounds.rectangle);
    }
}
