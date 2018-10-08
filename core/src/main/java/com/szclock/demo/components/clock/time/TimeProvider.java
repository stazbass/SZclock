package com.szclock.demo.components.clock.time;

import com.szclock.demo.entities.CurrentTime;
import com.szclock.demo.entities.TimeRecord;

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
//        result.setSeconds(clock.getSeconds()%2 == 0 ? 59 : 0);
        return result;
    }
}
