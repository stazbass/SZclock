package com.szclock.demo.dagger;

import com.szclock.demo.clock.CircleMath;
import com.szclock.demo.clock.Clock;
import com.szclock.demo.clock.CurrentTime;
import com.szclock.demo.data.GameData;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class GameModule {
    @Provides
    @Singleton
    public static Clock provideClock(CircleMath math, CurrentTime currentTime) {
        return new Clock(math, currentTime);
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
