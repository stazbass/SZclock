package com.szclock.demo.components.clock;

import com.badlogic.gdx.math.Vector2;
import com.szclock.demo.components.clock.views.MillisecondsView;
import com.szclock.demo.components.clock.views.MinuteView;
import com.szclock.demo.components.clock.views.SecondsView;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.entities.TimeRecord;

import java.util.LinkedList;
import java.util.List;

public class ClockView {
    private Vector2 origin;
    private double scale;

    private MinuteView minuteView;
    private SecondsView secondsView;
    private MillisecondsView millisecondsView;

    public ClockView(Vector2 origin, double scale) {
        this.origin = origin;
        this.scale = scale;
        this.minuteView = new MinuteView();
        this.secondsView = new SecondsView();
        this.millisecondsView = new MillisecondsView();
    }

    public List<RenderItem> render(TimeRecord record){
        List<RenderItem> result = new LinkedList<>();
        result.addAll(millisecondsView.render(record, origin, scale));
        result.addAll(secondsView.render(record, origin, scale));
        result.addAll(minuteView.render(record, origin, scale));
        return result;
    }

    public List<RenderItem> getSeconds(TimeRecord record) {
        return secondsView.render(record, origin, scale);
    }

    public List<RenderItem> getMinutes(TimeRecord record) {
        return minuteView.render(record, origin, scale);
    }

    public List<RenderItem> getMilliseconds(TimeRecord record) {
        return millisecondsView.render(record, origin, scale);
    }
}
