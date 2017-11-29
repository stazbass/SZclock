package com.szclock.demo.clock;

public class TimeCalc {
    public int getSeconds() {
        return getSeconds(getMilliseconds());
    }

    public int getSeconds(long milliseconds) {
        return (int) ((milliseconds / 1000) % 60);
    }

    public int getHourOfDay() {
        return getHourOfDay(getMilliseconds());
    }

    public int getHourOfDay(long milliseconds) {
        return (int) ((milliseconds / (1000 * 60 * 60)) % 24);
    }


    public int getHour() {
        return getHour(getMilliseconds());
    }

    public int getHour(long milliseconds) {
        return (int) ((milliseconds / (1000l * 60l * 60l)) % 24);
    }

    public long getMinute() {
        return getMinute(getMilliseconds());
    }

    public long getMinute(long milliseconds) {
        return (int) ((milliseconds / (1000 * 60)) % 60);
    }

    public long getMilliseconds() {
        return System.currentTimeMillis();
    }
}
