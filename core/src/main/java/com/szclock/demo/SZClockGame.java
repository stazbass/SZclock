package com.szclock.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.szclock.demo.components.clock.Clock;
import com.szclock.demo.dagger.DaggerMainComponent;
import com.szclock.demo.dagger.MainComponent;
import com.szclock.demo.render.FramesPerSecond;
import com.szclock.demo.render.Renderable;
import com.szclock.demo.render.Renderer;
import com.szclock.demo.render.TextureManager;

import java.util.LinkedList;
import java.util.List;

public class SZClockGame extends ApplicationAdapter {
    public static final int VIEWPORT_WIDTH = 1200;
    public static final int VIEWPORT_HEIGHT = 1028;
    SpriteBatch mainBatch;
    Clock clock;
    FramesPerSecond fps;
    MainComponent mainComponent;
    OrthographicCamera camera;
    Renderer renderer;
    List<Renderable> renderItems = new LinkedList<>();
    TextureManager textureManager;

    @Override
    public void create() {
        mainBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        mainComponent = DaggerMainComponent.builder().build();
        textureManager = mainComponent.provideTextureManager();
        renderer = mainComponent.provideRenderer();
        clock = mainComponent.provideClock();
        fps = mainComponent.provideFramesPerSecond();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mainBatch.setProjectionMatrix(camera.combined);

        renderer.render(() -> mainBatch, () -> clock.collectRenderItems());
        fps.draw(mainBatch);
    }

    @Override
    public void resize(int width, int height) {
        camera.update(true);
    }

    @Override
    public void dispose() {
        clock.dispose();
        mainBatch.dispose();
    }
}
