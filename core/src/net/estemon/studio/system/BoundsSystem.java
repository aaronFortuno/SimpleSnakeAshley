package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.DirectionComponent;
import net.estemon.studio.component.PositionComponent;
import net.estemon.studio.utils.Mappers;

public class BoundsSystem extends IteratingSystem {

    // constants
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            BoundsComponent.class
    ).get();

    // constructors
    public BoundsSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);

        bounds.rectangle.setPosition(position.x, position.y);
        bounds.rectangle.setSize(dimension.width, dimension.height);
    }
}
