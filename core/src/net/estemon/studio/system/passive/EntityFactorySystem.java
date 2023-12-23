package net.estemon.studio.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import net.estemon.studio.assets.AssetDescriptors;
import net.estemon.studio.assets.RegionNames;
import net.estemon.studio.component.BodyPartComponent;
import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.CoinComponent;
import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.DirectionComponent;
import net.estemon.studio.component.MovementComponent;
import net.estemon.studio.component.PlayerComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.SnakeComponent;
import net.estemon.studio.component.TextureComponent;
import net.estemon.studio.component.WorldWrapComponent;
import net.estemon.studio.component.ZOrderComponent;
import net.estemon.studio.config.GameConfig;

public class EntityFactorySystem extends EntitySystem {

    // constants
    private static final int BACKGROUND_Z_ORDER = 0;
    private static final int COIN_Z_ORDER = 1;
    private static final int BODY_PART_Z_ORDER = 2;
    private static final int HEAD_Z_ORDER = 3;

    // attributes
    private final AssetManager assetManager;

    private PooledEngine engine;
    private TextureAtlas gamePlayAtlas;

    // constructors
    public EntityFactorySystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
    }

    // public methods
    @Override
    public void update(float deltaTime) {
        // no processing
    }

    @Override
    public boolean checkProcessing() {
        // returning false since this is "passive" system
        return false;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (PooledEngine) engine;
    }

    public void createBackground() {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.WORLD_WIDTH;
        dimension.height = GameConfig.WORLD_HEIGHT;

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);

        // z order
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BACKGROUND_Z_ORDER;

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(texture);
        entity.add(zOrder);

        // add to engine
        engine.addEntity(entity);
    }

    public void createSnake() {
        // snake
        SnakeComponent snake = engine.createComponent(SnakeComponent.class);
        snake.head = createSnakeHead();

        // entity
        Entity snakeEntity = engine.createEntity();
        snakeEntity.add(snake);

        engine.addEntity(snakeEntity);
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

        // world wrap
        WorldWrapComponent worldWrap = engine.createComponent(WorldWrapComponent.class);

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.HEAD);

        // z order
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = HEAD_Z_ORDER;

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(direction);
        entity.add(movement);
        entity.add(player);
        entity.add(worldWrap);
        entity.add(texture);
        entity.add(zOrder);

        // add to engine
        engine.addEntity(entity);

        return entity;
    }

    public void createCoin() {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.COIN_SIZE;
        dimension.height = GameConfig.COIN_SIZE;

        // bounds
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        // coin
        CoinComponent coin = engine.createComponent(CoinComponent.class);

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.COIN);

        // z order
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = COIN_Z_ORDER;

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(coin);
        entity.add(texture);
        entity.add(zOrder);

        // add to engine
        engine.addEntity(entity);
    }

    public Entity createBodyPart(float x, float y) {
        // position
        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        // dimension
        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.SNAKE_SIZE;
        dimension.height = GameConfig.SNAKE_SIZE;

        // bounds
        BoundsComponent bounds = engine.createComponent(BoundsComponent.class);
        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);

        // body part
        BodyPartComponent bodyPart = engine.createComponent(BodyPartComponent.class);

        // texture
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = gamePlayAtlas.findRegion(RegionNames.BODY);

        // z order
        ZOrderComponent zOrder = engine.createComponent(ZOrderComponent.class);
        zOrder.z = BODY_PART_Z_ORDER;

        // entity
        Entity entity = engine.createEntity();
        entity.add(position);
        entity.add(dimension);
        entity.add(bounds);
        entity.add(bodyPart);
        entity.add(texture);
        entity.add(zOrder);

        // add to engine
        engine.addEntity(entity);

        return entity;
    }
}
