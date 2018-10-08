package com.szclock.demo.components.clock;

import com.szclock.demo.entities.CurrentTime;
import com.szclock.demo.entities.TimeRecord;
import com.szclock.demo.components.clock.time.TimeProvider;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TimeProviderTest {

    public static final int EXPECTED_HOUR = 5;
    public static final int EXPECTED_MILLIS = 6;
    public static final int EXPECTED_SECONDS = 7;
    public static final int EXPECTED_MINUTE = 8;

    @Test
    public void getTimeRecord() {
        CurrentTime clock = Mockito.mock(CurrentTime.class);
        Mockito.when(clock.getHour()).thenReturn(EXPECTED_HOUR);
        Mockito.when(clock.getMilliseconds()).thenReturn(EXPECTED_MILLIS);
        Mockito.when(clock.getSeconds()).thenReturn(EXPECTED_SECONDS);
        Mockito.when(clock.getMinute()).thenReturn(EXPECTED_MINUTE);


        TimeProvider provider = new TimeProvider(clock);

        TimeRecord time = provider.getTimeRecord();
        Assert.assertEquals(EXPECTED_HOUR, time.getHours());
        Assert.assertEquals(EXPECTED_MINUTE, time.getMinutes());
        Assert.assertEquals(EXPECTED_MILLIS, time.getMilliseconds());
        Assert.assertEquals(EXPECTED_SECONDS, time.getSeconds());
    }
}