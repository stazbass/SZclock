package com.szclock.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SizyAdapter extends ApplicationAdapter{
    OrthographicCamera camera;
    SpriteBatch spriteBatch;

    Texture redDot;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        spriteBatch = new SpriteBatch();
        redDot = new Texture("RedDot8.png");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0 , 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        // draw
        spriteBatch.draw(redDot, 10, 10);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width + " " + height);

    }
}
