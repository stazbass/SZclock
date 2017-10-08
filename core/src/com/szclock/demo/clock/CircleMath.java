package com.szclock.demo.clock;

import com.badlogic.gdx.math.Vector2;

public class CircleMath {
    public Vector2 getPointOnCircle(double originX, double originY, double degree, double radius) {
        return new Vector2((float) (originX + radius * Math.cos(degree)), (float) (originY + radius * Math.sin(degree)));
    }
}