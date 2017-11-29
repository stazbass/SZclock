package com.szclock.demo.clock;

import com.badlogic.gdx.math.Vector2;

public class CircleMath {
    public Vector2 getPointOnCircle(float originX, float originY, float degree, float radius) {
        return new Vector2((float)(originX + radius * Math.cos(degree)), (float) (originY + radius * Math.sin(degree)));
    }

    public double getPointOnCircleX(double originX, double degree, double radius){
        return (originX + radius * Math.cos(degree));
    }
    public double getPointOnCircleY(double originY, double degree, double radius){
        return (originY + radius * Math.sin(degree));
    }
}
