package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class BodyPartComponent implements Component, Pool.Poolable {

    // attributes
    // just a flag to indicate that body part was just added
    public boolean justAdded = true;

    // public methods
    @Override
    public void reset() {
        justAdded = true;
    }
}
