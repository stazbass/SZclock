package com.szclock.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.szclock.demo.clock.Clock;
import com.szclock.demo.dagger.DaggerMainComponent;
import com.szclock.demo.dagger.MainComponent;

public class SZClockGame extends ApplicationAdapter {
    public static final int VIEWPORT_WIDTH = 1200;
    public static final int VIEWPORT_HEIGHT = 1028;
    SpriteBatch batch;
    Clock clock;
    MainComponent mainComponent;
    OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        batch = new SpriteBatch();
        mainComponent = DaggerMainComponent.builder().build();
        clock = mainComponent.provideClock();
        clock.init();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        clock.draw(batch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
    }

    @Override
    public void dispose() {
        clock.dispose();
        batch.dispose();
    }
}
