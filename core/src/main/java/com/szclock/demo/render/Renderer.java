package com.szclock.demo.render;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.szclock.demo.entities.RenderItem;

import java.util.List;
import java.util.function.Supplier;

public class Renderer {
    private TextureManager textureManager;

    public Renderer(TextureManager textureManager){
        this.textureManager = textureManager;
    }

    public void render(Supplier<SpriteBatch> batchSupplier, Supplier<List<RenderItem>> spriteSupplier) {
        SpriteBatch spriteBatch = batchSupplier.get();
        spriteBatch.begin();
        spriteBatch.enableBlending();
        spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        spriteSupplier.get().stream().forEach(renderAtom -> {
            Texture texture = textureManager.loadTexture(renderAtom.getTexture());
            spriteBatch.draw(texture, renderAtom.getPosition().x - texture.getWidth() / 2.0f * renderAtom.getScale().x, renderAtom.getPosition().y - texture.getHeight() / 2.0f * renderAtom.getScale().y,
                    texture.getWidth() * renderAtom.getScale().x, texture.getHeight() * renderAtom.getScale().y);
        });
        spriteBatch.end();
    }
}
