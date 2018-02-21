package com.szclock.demo.clock;

public class TimeProvider {
    private CurrentTime clock;

    public TimeProvider(CurrentTime clock){
        this.clock = clock;
    }
    public TimeRecord getTimeRecord(){
        TimeRecord result = new TimeRecord();
        result.setHours(clock.getHour());
        result.setMilliseconds(clock.getMilliseconds());
        result.setMinutes(clock.getMinute());
        result.setSeconds(clock.getSeconds());
        return result;
    }
}
