package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.component.TextureComponent;
import net.estemon.studio.utils.Mappers;

public class RenderSystem extends IteratingSystem {

    // constants
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            TextureComponent.class
    ).get();

    // attributes
    private final SpriteBatch batch;
    private final Viewport viewport;

    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        super.update(deltaTime);

        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        TextureComponent texture = Mappers.TEXTURE.get(entity);

        batch.draw(texture.region,
                position.x, position.y,
                dimension.width, dimension.height
        );
    }
}
