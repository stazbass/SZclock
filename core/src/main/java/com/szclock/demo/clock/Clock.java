package com.szclock.demo.clock;

import com.badlogic.gdx.utils.Disposable;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.render.Renderable;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class Clock implements Disposable, Renderable {
    private ClockView clockView;
    private TimeProvider timeProvider;

    @Inject
    public Clock(TimeProvider provider, ClockView clockView) {
        this.timeProvider = provider;
        this.clockView = clockView;
    }


    @Override
    public List<RenderItem> collectRenderItems() {
        List<RenderItem> result = new LinkedList<>();
        result.addAll(clockView.getSeconds(timeProvider.getTimeRecord()));
        result.addAll(clockView.getMinutes(timeProvider.getTimeRecord()));
        return clockView.getSeconds(timeProvider.getTimeRecord());
    }

    @Override
    public void dispose() {
    }
}
