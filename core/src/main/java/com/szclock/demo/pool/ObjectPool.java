package com.szclock.demo.pool;

import java.util.LinkedList;
import java.util.List;

public class ObjectPool <T> {
    List<T> available;

    public ObjectPool() {
        this.available = new LinkedList<>();
    }

    public T get(){
        return available.get(0);
    }
}
