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

        for (int secondIterator = 0; secondIterator < secondsNow; secondIterator++) {
            Vector2 position = getSecondPointPosition(origin, scale, secondIterator);
            result.add(position);
        }

        long millisecondsInSecond = milliseconds % 1000;
        double dtime = millisecondsInSecond <= 500 ? millisecondsInSecond / 500.0 : (1000 - millisecondsInSecond) / 500.0;
        double currentSecondArrowScale = Interpolation.smoother.apply((float) dtime);

        for (int j = 1; j <= secondsNow; j++) {
            for (int secondIterator = 0; secondIterator < j * currentSecondArrowScale; secondIterator++) {
//                result.add(getSecondPointPosition(origin, scale, secondIterator, milliseconds));
            }
        }
//        Vector2 millisPosition = getMillisecondsPosition(origin, millisecondsInSecond, secondsNow, 1.0);
//        result.add(millisPosition);
        return result;
    }

    public List<Vector2> getMinutes(TimeRecord record){
        List<Vector2> result = new ArrayList<>(60);
        long hour = record.getHours();
        long minute = record.getMinutes();

        for (int minuteIter = 0; minuteIter < minute; minuteIter++) {
            result.add(getMinutesPointPosition(origin, scale, minuteIter, hour));
        }
        return result;
    }

    private Vector2 getMinutesPointPosition(Vector2 origin, double scale, long minute, long hour){
        double degree = (Math.PI / 2.0 - hour * Math.PI * 2.0 / 12.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * minute);
    }

    private Vector2 getSecondPointPosition(Vector2 origin, double scale, long secondsNow) {
        double secondsArrowDegree = (double) secondsNow;
        float degree = (float) (Math.PI / 2.0 - secondsArrowDegree * Math.PI * 2.0 / 60.0);
        return getPointOnCircle(origin.x, origin.y, degree, scale * secondsNow);
    }

    private Vector2 getMillisecondsPosition(Vector2 origin, long milliseconds, long secondsNow, double scale) {
        float millisecondsNormalized = (1 - milliseconds / 1000.0f);
        Vector2 secondsPosition = getSecondPointPosition(origin, scale, secondsNow);
        Vector2 nextSecondsPosition = getSecondPointPosition(origin, scale, secondsNow + 1);
        Vector2 millisPosition = secondsPosition.interpolate(nextSecondsPosition, millisecondsNormalized, getInterpolation());
        return millisPosition;
    }


    private Interpolation getInterpolation() {
        return Interpolation.smooth;
    }


}
