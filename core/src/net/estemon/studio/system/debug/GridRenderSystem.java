package net.estemon.studio.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.utils.ViewportUtils;

public class GridRenderSystem extends EntitySystem {

    // attributes
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    // constructors
    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);
    }
}
