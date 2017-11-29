package com.szclock.demo.clock;

import java.util.Calendar;

public class CurrentTime {

    public int getSeconds() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR);
    }

    public int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int getMilliseconds() {
        return Calendar.getInstance().get(Calendar.MILLISECOND);
    }
}
