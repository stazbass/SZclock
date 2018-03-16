package com.szclock.demo.clock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.szclock.demo.TextureManager;

import javax.inject.Inject;
import java.util.List;

public class Clock implements Disposable {
    private Texture blueDot8;
    private Texture redDot8;
    private Texture goldenDot16;
    private Texture greenDot8;
    private TextureManager textureManager;
    private ClockModel clockModel;
    private TimeProvider timeProvider;

    @Inject
    public Clock(TextureManager textureManager) {
        this.textureManager = textureManager;
        this.timeProvider = new TimeProvider(new CurrentTime());
    }

    public void init() {
        this.blueDot8 = textureManager.loadTexture("BlueDot8.png");
        this.redDot8 = textureManager.loadTexture("RedDot8.png");
        this.goldenDot16 = textureManager.loadTexture("GoldenDot16.png");
        this.greenDot8 = textureManager.loadTexture("GreenDot8.png");
    }

    public void draw(SpriteBatch batch, int sizeX, int sizeY) {
        Vector2 origin = new Vector2(sizeX/2.0f, sizeY/2.0f);
        clockModel = new ClockModel(origin, 16.0);
        batch.begin();

        TimeRecord time = timeProvider.getTimeRecord();
        clockModel.getSeconds(time).forEach(p->drawCentered(batch, blueDot8, p.x, p.y, 1));
        clockModel.getMinutes(time).forEach(p->drawCentered(batch, greenDot8, p.x, p.y, 1));

        batch.end();
    }

    private void drawCentered(SpriteBatch batch, Texture texture, float x, float y, float scale) {
        batch.draw(texture, x - texture.getWidth() / 2.0f * scale, y - texture.getHeight() / 2.0f * scale, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    @Override
    public void dispose() {
    }
}