package net.estemon.studio.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import net.estemon.studio.assets.AssetDescriptors;

public class SoundSystem extends EntitySystem {

    // attributes
    private final AssetManager assetManager;

    private Sound coinSound;
    private Sound loseSound;

    // constructor
    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    private void init() {
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);
    }

    // public methods

    @Override
    public void update(float deltaTime) {
        // not processing
    }

    @Override
    public boolean checkProcessing() {
        return false;
    }

    public void hitCoin() {
        coinSound.play();
    }

    public void lose() {
        loseSound.play();
    }
}
