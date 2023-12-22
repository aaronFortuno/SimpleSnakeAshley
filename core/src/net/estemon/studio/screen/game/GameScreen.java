package net.estemon.studio.screen.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.SimpleSnakeGame;
import net.estemon.studio.common.EntityFactory;
import net.estemon.studio.common.GameManager;
import net.estemon.studio.config.GameConfig;
import net.estemon.studio.screen.menu.MenuScreen;
import net.estemon.studio.system.debug.DebugCameraSystem;
import net.estemon.studio.system.debug.DebugRenderSystem;
import net.estemon.studio.system.debug.GridRenderSystem;
import net.estemon.studio.utils.GdxUtils;

public class GameScreen extends ScreenAdapter {

    // constants
    private static final Logger log = new Logger(GameScreen.class.getSimpleName(), 3);

    // attributes
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private PooledEngine engine;
    private EntityFactory factory;

    // constructors
    public GameScreen(SimpleSnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();
    }

    // public methods
    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        engine = new PooledEngine();
        factory = new EntityFactory(engine);

        engine.addSystem(new GridRenderSystem(viewport, renderer));
        engine.addSystem(new DebugCameraSystem(
                GameConfig.WORLD_CENTER_X,
                GameConfig.WORLD_CENTER_Y,
                camera)
        );
        engine.addSystem(new DebugRenderSystem(viewport, renderer));

        factory.createSnakeHead();
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
