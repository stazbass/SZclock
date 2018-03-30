package com.szclock.demo.clock;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.szclock.demo.entities.RenderItem;
import com.szclock.demo.entities.TimeRecord;

import java.util.ArrayList;
import java.util.List;

import static com.szclock.demo.clock.CircleMath.*;

public class ClockView {
    private static String SECONDS_TEXTURE = "BlueDot8.png";
    private static String MINUTES_TEXTURE = "GreenDot8.png";
    private static String GOLDEN_TEXTURE = "GoldenDot8.png";

    private Vector2 origin;
    private double scale;

    public ClockView(Vector2 origin, double scale) {
        this.origin = origin;
        this.scale = scale;
    }

    public List<RenderItem> getSeconds(TimeRecord record) {
        long secondsNow = record.getSeconds();
        List<RenderItem> result = new ArrayList<>(70);

        for (int secondIterator = 0; secondIterator < secondsNow; secondIterator++) {
            Vector2 position = getSecondPointPosition(origin, scale, secondIterator);
            float scaleVal = (secondIterator + 2.0f)/ (secondsNow + 2.0f);
            Vector2 scale = new Vector2(scaleVal, scaleVal );
            result.add(new RenderItem(position, scale, SECONDS_TEXTURE));
        }

        if(secondsNow == 59){
            result.stream().forEach(i->i.getPosition().interpolate(origin, record.getMilliseconds()%1000/1000.0f, Interpolation.exp5));
        }

        return result;
    }

    public List<RenderItem> getMinutes(TimeRecord record){
        List<RenderItem> result = new ArrayList<>(60);
        long hour = record.getHours();
        long minute = record.getMinutes();
        long seconds = record.getSeconds();
        long milliseconds = record.getMilliseconds()%1000;

        for (int minuteIter = 0; minuteIter < minute; minuteIter++) {
            result.add(
                    new RenderItem(getMinutesPointPosition(origin, scale, minuteIter, hour), MINUTES_TEXTURE));
        }
        result.add(new RenderItem(getMinutesPointPosition(origin, scale, minute, hour).interpolate(origin, - (seconds + (milliseconds/1000.0f))/60.0f, Interpolation.smoother ),
                new Vector2((float)Math.sin(milliseconds/1000.0*Math.PI), (float)Math.sin(milliseconds/1000.0*Math.PI)), MINUTES_TEXTURE));
        return result;
    }

    public List<RenderItem> getMilliseconds(TimeRecord record){
        long secondsNow = record.getSeconds();
        List<RenderItem> result = new ArrayList<>(1);

        Vector2 position = getSecondPointPosition(origin, scale, secondsNow * (record.getMilliseconds()%1000/1000.0));
        float scaleVal = 2*record.getMilliseconds()%1000/1000.0f;
        Vector2 scale = new Vector2(scaleVal, scaleVal );
        result.add(new RenderItem(position, scale, GOLDEN_TEXTURE));
        if(secondsNow == 59){
            result.stream().forEach(i->i.getPosition().interpolate(origin, record.getMilliseconds()%1000/1000.0f, Interpolation.exp5));
        }
        return result;
    }

    private Vector2 getMinutesPointPosition(Vector2 origin, double scale, long minute, double hour){
        double degree = (Math.PI / 2.0 - hour * Math.PI * 2.0 / 12.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * minute);
    }

    private Vector2 getSecondPointPosition(Vector2 origin, double scale, double second) {
        float degree = (float) (Math.PI / 2.0 - second * Math.PI * 2.0 / 60.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * second);
    }
}
