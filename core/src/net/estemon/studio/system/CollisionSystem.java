package net.estemon.studio.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;

import net.estemon.studio.system.passive.EntityFactorySystem;
import net.estemon.studio.common.GameManager;
import net.estemon.studio.component.BodyPartComponent;
import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.CoinComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.SnakeComponent;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.system.passive.SoundSystem;
import net.estemon.studio.utils.Mappers;

public class CollisionSystem extends IntervalSystem {

    // constants
    private static final Family SNAKE_FAMILY = Family.all(SnakeComponent.class).get();
    private static final Family COIN_FAMILY = Family.all(CoinComponent.class).get();

    // attributes
    private EntityFactorySystem factory;
    private SoundSystem soundSystem;

    // constructors
    public CollisionSystem() {
        super(GameConfig.MOVE_TIME);
    }

    @Override
    public void addedToEngine(Engine engine) {
        factory = engine.getSystem(EntityFactorySystem.class);
        soundSystem = engine.getSystem(SoundSystem.class);
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

                    // get head position and add body part
                    PositionComponent position = Mappers.POSITION.get(snake.head);
                    Entity bodyPart = factory.createBodyPart(position.x, position.y);
                    snake.bodyParts.insert(0, bodyPart);
                    soundSystem.hitCoin();

                    // update score
                    GameManager.INSTANCE.incrementScore(GameConfig.COIN_SCORE);
                }
            }
        }

        // head <-> body parts
        for (Entity snakeEntity : snakes) {
            SnakeComponent snake = Mappers.SNAKE.get(snakeEntity);

            for (Entity bodyPartEntity : snake.bodyParts) {
                BodyPartComponent bodyPart = Mappers.BODY_PART.get(bodyPartEntity);

                if (bodyPart.justAdded) {
                    bodyPart.justAdded = false;
                    continue;
                }

                if (overlaps(snake.head, bodyPartEntity)) {
                    soundSystem.lose();
                    GameManager.INSTANCE.updateHighScore();
                    GameManager.INSTANCE.setGameOver();
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
