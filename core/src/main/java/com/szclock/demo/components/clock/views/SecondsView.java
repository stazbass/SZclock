package com.szclock.demo.components.clock.views;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.szclock.demo.components.clock.IClockRenderable;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.entities.TimeRecord;

import java.util.ArrayList;
import java.util.List;

import static com.szclock.demo.math.CircleMath.getPointOnCircle;

public class SecondsView implements IClockRenderable {
    private static String SECONDS_TEXTURE = "BlueDot8.png";

    @Override
    public List<RenderItem> render(TimeRecord time, Vector2 origin, double scale) {
        long secondsNow = time.getSeconds();
        List<RenderItem> result = new ArrayList<>(70);

        for (int secondIterator = 0; secondIterator < secondsNow; secondIterator++) {
            Vector2 position = getSecondPointPosition(origin, scale, secondIterator);
            float scaleVal = (secondIterator + 2.0f) / (secondsNow + 2.0f);
            Vector2 scaleVec = new Vector2(scaleVal, scaleVal);
            result.add(new RenderItem(position, scaleVec, SECONDS_TEXTURE));
        }

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
