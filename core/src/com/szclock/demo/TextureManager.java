package com.szclock.demo;

import com.badlogic.gdx.graphics.Texture;
import com.szclock.demo.logger.GameLogger;

import javax.inject.Inject;

public class TextureManager {
    private GameLogger logger;

    @Inject
    public TextureManager(GameLogger logger){
        this.logger = logger;
    }

    public Texture loadTexture(String path){
        Texture result = new Texture(path);
        result.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logger.log("Loaded: ", path);
        return result;
    }
}
