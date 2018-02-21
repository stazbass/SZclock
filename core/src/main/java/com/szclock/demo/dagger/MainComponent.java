package com.szclock.demo.dagger;

import com.szclock.demo.clock.CircleMath;
import com.szclock.demo.clock.Clock;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {GameModule.class})
@Singleton
public interface MainComponent {
    Clock provideClock();
}
