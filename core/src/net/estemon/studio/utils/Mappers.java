package net.estemon.studio.utils;

import com.badlogic.ashley.core.ComponentMapper;

import net.estemon.studio.component.BoundsComponent;
import net.estemon.studio.component.DimensionComponent;
import net.estemon.studio.component.PositionComponent;

public final class Mappers {

    private Mappers() {} // not instantiable

    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);
}
