package com.szclock.demo.clock;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import static com.szclock.demo.clock.CircleMath.*;

public class ClockModel {
    private Vector2 origin;
    private double scale;

    public ClockModel(Vector2 origin, double scale) {
        this.origin = origin;
        this.scale = scale;
    }

    public List<Vector2> getSeconds(TimeRecord record) {
        long secondsNow = record.getSeconds();
        long milliseconds = record.getMilliseconds();
        List<Vector2> result = new ArrayList<>(70);

        System.out.println(secondsNow);
        for (int secondIterator = 0; secondIterator < secondsNow; secondIterator++) {
            Vector2 position = getSecondPointPosition(origin, scale, secondIterator);
            result.add(position);
        }


        long millisecondsInSecond = milliseconds % 1000;
        double dtime = millisecondsInSecond / 1000.0;
        double currentSecondArrowScale = Interpolation.smoother.apply((float) dtime);
        if(secondsNow == 59){
            result.stream().forEach(res -> res.interpolate(origin, (float)dtime, Interpolation.smoother));
        }


        Vector2 position = getSecondPointPosition(origin, scale, secondsNow*currentSecondArrowScale);

        result.add(position);

        return result;
    }

    public List<Vector2> getMinutes(TimeRecord record){
        List<Vector2> result = new ArrayList<>(60);
        long hour = record.getHours();
        long minute = record.getMinutes();
        long seconds = record.getSeconds();
        long milliseconds = record.getMilliseconds()%1000;

        for (int minuteIter = 0; minuteIter < minute; minuteIter++) {
            result.add(getMinutesPointPosition(origin, scale, minuteIter, hour));
        }
        result.add(getMinutesPointPosition(origin, scale, minute, hour).interpolate(origin, 1 - (seconds + (milliseconds/1000.0f))/60.0f, Interpolation.smoother ));
        return result;
    }

    private Vector2 getMinutesPointPosition(Vector2 origin, double scale, long minute, double hour){
        double degree = (Math.PI / 2.0 - hour * Math.PI * 2.0 / 12.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * minute);
    }

    private Vector2 getSecondPointPosition(Vector2 origin, double scale, double secondsNow) {
        double secondsArrowDegree =  secondsNow;
        float degree = (float) (Math.PI / 2.0 - secondsArrowDegree * Math.PI * 2.0 / 60.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * secondsNow);
    }
}
