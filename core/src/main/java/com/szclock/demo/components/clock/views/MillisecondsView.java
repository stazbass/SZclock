package com.szclock.demo.components.clock.views;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.szclock.demo.components.clock.IClockRenderable;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.entities.TimeRecord;

import java.util.ArrayList;
import java.util.List;

import static com.szclock.demo.math.CircleMath.getPointOnCircle;

public class MillisecondsView implements IClockRenderable {
    private static String MILLISECONDS_TEXTURE = "RedDot8.png";

    @Override
    public List<RenderItem> render(TimeRecord time, Vector2 origin, double scale) {
        long secondsNow = time.getSeconds();
        List<RenderItem> result = new ArrayList<>(1);

        Vector2 position = getSecondPointPosition(origin, scale, secondsNow * (time.getMilliseconds() % 1000 / 1000.0));
        float millisDelta = time.getMilliseconds() % 1000 / 1000.0f;

        float scaleVal = 5 * (float) Math.sin(millisDelta * Math.PI * 2.0f / 3);
        result.add(new RenderItem(position, new Vector2(scaleVal, scaleVal), MILLISECONDS_TEXTURE));

        if (secondsNow == 59) {
            result.stream().forEach(i -> i.getPosition().interpolate(origin, time.getMilliseconds() % 1000 / 1000.0f, Interpolation.exp5));
        }
        return result;
    }

    private Vector2 getSecondPointPosition(Vector2 origin, double scale, double second) {
        float degree = (float) (Math.PI / 2.0 - second * Math.PI * 2.0 / 60.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * second);
    }
}
