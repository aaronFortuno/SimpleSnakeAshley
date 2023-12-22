package net.estemon.studio.entity;

import net.estemon.studio.config.GameConfig;

@Deprecated
public class Coin extends EntityBase {

    // attributes
    private boolean available;

    // constructors
    public Coin() {
        setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
