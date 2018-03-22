package com.szclock.demo.entities;

public class TimeRecord {
    private long milliseconds;
    private long seconds;
    private long minutes;
    private long hours;

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "TimeRecord{" +
                "milliseconds=" + milliseconds +
                ", seconds=" + seconds +
                ", minutes=" + minutes +
                ", hours=" + hours +
                '}';
    }
}
