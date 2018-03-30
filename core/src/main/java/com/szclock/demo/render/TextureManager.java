package com.szclock.demo.render;

import com.badlogic.gdx.graphics.Texture;
import com.szclock.demo.logger.GameLogger;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private GameLogger logger;
    private Map<String, Texture> textures;

    @Inject
    public TextureManager(GameLogger logger) {
        this.logger = logger;
        this.textures = new HashMap<>();
    }

    public Texture loadTexture(String path) {
        if(!textures.containsKey(path)){
            Texture result = new Texture(path);
            result.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textures.put(path, result);
            logger.log("Loaded: ", path);
        }
        return textures.get(path);
    }
}
