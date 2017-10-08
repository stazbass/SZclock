package com.szclock.demo;

import com.szclock.demo.data.GameData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameDataTest {
    GameData data;

    @Before
    public void setUp() throws Exception {
        data = new GameData();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateGenericTest(){
        GameData data = new GameData();
        String key = "test_key";
        Integer value = 22;
        data.updateValue(key, value);
        Integer result = data.getValue(key);
        assertEquals(result, value);
    }


}