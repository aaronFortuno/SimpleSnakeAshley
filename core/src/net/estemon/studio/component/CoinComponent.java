package net.estemon.studio.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CoinComponent implements Component, Pool.Poolable {

    // attributes
    public boolean available;

    @Override
    public void reset() {
        available = false;
    }
}
