package com.szclock.demo.components.clock;

import com.badlogic.gdx.math.Vector2;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.entities.TimeRecord;

import java.util.List;

public interface IClockRenderable {
    List<RenderItem> render(TimeRecord time, Vector2 origin, double scale);
}
