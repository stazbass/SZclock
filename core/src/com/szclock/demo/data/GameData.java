package com.szclock.demo.data;

import java.util.HashMap;
import java.util.Map;

public class GameData {
    public Map dataMap;

    public GameData(){
        this.dataMap = new HashMap<>();
    }

    public <K>void updateValue(String key, K value){
        dataMap.put(key, value);
    }

    public <K> K getValue(String key){
        return (K) dataMap.get(key);
    }
}
