package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.TextureComponent;
import net.estemon.studio.component.ZOrderComponent;
import net.estemon.studio.utils.Mappers;
import net.estemon.studio.utils.ZOrderComparator;

public class RenderSystem extends IteratingSystem {

    // constants
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            TextureComponent.class,
            ZOrderComponent.class
    ).get();

    // attributes
    private final SpriteBatch batch;
    private final Viewport viewport;
    private final Array<Entity> renderQueue = new Array<Entity>();

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(ZOrderComparator.INSTANCE);
        draw();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    // private methods
    private void draw() {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        for (Entity entity : renderQueue) {
            PositionComponent position = Mappers.POSITION.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION.get(entity);
            TextureComponent texture = Mappers.TEXTURE.get(entity);

            batch.draw(texture.region,
                    position.x, position.y,
                    dimension.width, dimension.height
            );
        }

        batch.end();
    }
}
