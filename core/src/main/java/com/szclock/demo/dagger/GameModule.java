package com.szclock.demo.dagger;

import com.szclock.demo.TextureManager;
import com.szclock.demo.clock.CircleMath;
import com.szclock.demo.clock.Clock;
import com.szclock.demo.entities.CurrentTime;
import com.szclock.demo.data.GameData;
import com.szclock.demo.logger.GameLogger;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class GameModule {
    @Provides
    @Singleton
    public static Clock provideClock(TextureManager textureManager) {
        return new Clock(textureManager);
    }

    @Provides
    @Singleton
    public static TextureManager provideTextureManager(GameLogger logger) {
        return new TextureManager(logger);
    }

    @Provides
    @Singleton
    public static GameLogger provideLogger() {
        return new GameLogger();
    }

    @Provides
    @Singleton
    public static CircleMath provideCircleMath() {
        return new CircleMath();
    }

    @Provides
    @Singleton
    public static GameData provideGameData() {
        return new GameData();
    }

    @Provides
    @Singleton
    public static CurrentTime provideCurrentTime() {
        return new CurrentTime();
    }
}
