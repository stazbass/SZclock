package com.szclock.demo.dagger;

import com.szclock.demo.components.clock.Clock;
import com.szclock.demo.render.FramesPerSecond;
import com.szclock.demo.render.Renderer;
import com.szclock.demo.render.TextureManager;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {GameModule.class})
@Singleton
public interface MainComponent {
    Clock provideClock();
    TextureManager provideTextureManager();
    Renderer provideRenderer();
    FramesPerSecond provideFramesPerSecond();
}
