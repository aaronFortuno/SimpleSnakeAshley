package net.estemon.studio.screen.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.SimpleSnakeGame;
import net.estemon.studio.assets.AssetDescriptors;
import net.estemon.studio.collision.CollisionListener;
import net.estemon.studio.common.EntityFactory;
import net.estemon.studio.common.GameManager;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.screen.menu.MenuScreen;
import net.estemon.studio.system.BoundsSystem;
import net.estemon.studio.system.CoinSystem;
import net.estemon.studio.system.CollisionSystem;
import net.estemon.studio.system.DirectionSystem;
import net.estemon.studio.system.HudRenderSystem;
import net.estemon.studio.system.PlayerControlSystem;
import net.estemon.studio.system.RenderSystem;
import net.estemon.studio.system.SnakeMovementSystem;
import net.estemon.studio.system.WorldWrapSystem;
import net.estemon.studio.system.debug.DebugCameraSystem;
import net.estemon.studio.system.debug.DebugRenderSystem;
import net.estemon.studio.system.debug.GridRenderSystem;
import net.estemon.studio.system.passive.SnakeSystem;
import net.estemon.studio.utils.GdxUtils;

public class GameScreen extends ScreenAdapter {

    // attributes
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private final SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;
    private BitmapFont font;
    private Sound coinSound;
    private Sound loseSound;
    private CollisionListener listener;

    // constructors
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
        batch = game.getBatch();

        listener = new CollisionListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };
    }

    // public methods
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine, assetManager);
        font = assetManager.get(AssetDescriptors.UI_FONT);
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);

        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugCameraSystem(
                GameConfig.WORLD_CENTER_X,
                GameConfig.WORLD_CENTER_Y,
                camera)
        );
        engine.addSystem(new DebugRenderSystem(viewport, renderer));
        engine.addSystem(new SnakeSystem());
        engine.addSystem(new DirectionSystem());
        engine.addSystem(new SnakeMovementSystem());
        engine.addSystem(new BoundsSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new WorldWrapSystem());
        engine.addSystem(new CoinSystem());
        engine.addSystem(new CollisionSystem(factory, listener));
        engine.addSystem(new RenderSystem(batch, viewport));
        engine.addSystem(new HudRenderSystem(batch, hudViewport, font));

        factory.createBackground();
        factory.createCoin();
        factory.createSnake();

        GameManager.INSTANCE.reset();
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();

        engine.update(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        engine.removeAllEntities();
    }
}
