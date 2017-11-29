package com.szclock.demo.clock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeCalcTest {
    TimeCalc timeCalc;
    CurrentTime currentTime = new CurrentTime();

    @Before
    public void setUp(){
        this.timeCalc = new TimeCalc();
        this.currentTime = new CurrentTime();
    }

    @Test
    public void getSeconds() throws Exception {
        assertEquals(timeCalc.getSeconds(1511923852096l), 52);
    }

    @Test
    public void getHour() throws Exception {
        System.out.println(timeCalc.getHour());
        System.out.println(currentTime.getHour());
    }

    @Test
    public void getMinute() throws Exception {
        assertEquals(50, timeCalc.getMinute(1511923852096l));
    }

}