package net.estemon.studio.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.estemon.studio.common.GameManager;

public class HudRenderSystem extends EntitySystem {

    // constants
    private static final float PADDING = 20.0f;

    // attributes
    private final SpriteBatch batch;
    private final Viewport hudViewport;
    private final BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();

    // constructors
    public HudRenderSystem(SpriteBatch batch, Viewport hudViewport, BitmapFont font) {
        this.batch = batch;
        this.hudViewport = hudViewport;
        this.font = font;
    }

    @Override
    public void update(float deltaTime) {
        GameManager.INSTANCE.updateDisplayScore(deltaTime);

        hudViewport.apply();
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        // high score
        String highScoreString = "HIGH SCORE: " + GameManager.INSTANCE.getDisplayHighScore();
        float y = hudViewport.getWorldHeight() - PADDING;

        layout.setText(font, highScoreString);
        font.draw(batch, layout, PADDING, y);

        // score
        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        float scoreX = hudViewport.getWorldWidth() - layout.width;

        layout.setText(font, scoreString);
        font.draw(batch, layout, scoreX, y);

        batch.end();
    }
}
