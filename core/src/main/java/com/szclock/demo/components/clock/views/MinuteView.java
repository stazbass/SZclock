package com.szclock.demo.components.clock.views;

import com.badlogic.gdx.math.Vector2;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.entities.TimeRecord;
import com.szclock.demo.components.clock.IClockRenderable;

import java.util.ArrayList;
import java.util.List;

import static com.szclock.demo.math.CircleMath.getPointOnCircle;

public class MinuteView implements IClockRenderable {
    private static String MINUTES_TEXTURE = "GreenDot8.png";

    public MinuteView(){

    }

    private Vector2 getMinutesPointPosition(Vector2 origin, double scale, double minute, double hour) {
        double degree = (Math.PI / 2.0 - hour * Math.PI * 2.0 / 12.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * minute);
    }
    @Override
    public List<RenderItem> render(TimeRecord time, Vector2 origin, double scale) {
        List<RenderItem> result = new ArrayList<>(60);
        long hour = time.getHours();
        long minute = time.getMinutes();
        long seconds = time.getSeconds();
        long milliseconds = time.getMilliseconds() % 1000;

        for (int minuteIterator = 0; minuteIterator < minute; minuteIterator++) {
            result.add(
                    new RenderItem(getMinutesPointPosition(origin, scale, minuteIterator, hour), MINUTES_TEXTURE));
        }
        float newMinuteScale = (float) Math.sin(milliseconds / 1000.0 * Math.PI) * 2.0f;
        Vector2 newMinutePosition = getMinutesPointPosition(origin, scale, minute * (seconds + milliseconds / 1000.0) / 60.0, hour);
        result.add(new RenderItem(newMinutePosition, new Vector2(newMinuteScale, newMinuteScale), MINUTES_TEXTURE));
        return result;
    }
}
