package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {

    // attribute
    public TextureRegion region;

    // public methods
    @Override
    public void reset() {
        region = null;
    }
}
