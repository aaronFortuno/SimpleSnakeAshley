package net.estemon.studio.entity;

import net.estemon.studio.config.GameConfig;

@Deprecated
public class BodyPart extends EntityBase {

    private boolean justAdded = true;

    public BodyPart() {
        setSize(GameConfig.SNAKE_SIZE, GameConfig.SNAKE_SPEED);
    }

    public boolean isJustAdded() {
        return justAdded;
    }

    public void setJustAdded(boolean justAdded) {
        this.justAdded = justAdded;
    }
}
