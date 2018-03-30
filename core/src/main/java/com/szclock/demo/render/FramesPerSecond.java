package com.szclock.demo.render;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FramesPerSecond {
    private BitmapFont font;
    private long lastTime = 0;
    private long currentFPS = 0;
    private long lastFPS = 0;

    public FramesPerSecond(){
        font = new BitmapFont();
    }

    public void draw(SpriteBatch batch){
        long time = System.currentTimeMillis();
        if(time - lastTime < 1000){
            currentFPS++;
        }else{
            lastTime = time;
            lastFPS = currentFPS;
            currentFPS = 0;
        }
        batch.begin();
        font.draw(batch, "FPS : " + String.valueOf(lastFPS), 20, 20);
        batch.end();
    }
}
