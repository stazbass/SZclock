package com.szclock.demo.clock;

import com.badlogic.gdx.math.Vector2;

public class CircleMath {
    public static Vector2 getPointOnCircle(double originX, double originY, double degree, double radius) {
        return new Vector2((float)(originX + radius * Math.cos(degree)), (float) (originY + radius * Math.sin(degree)));
    }

    public static double getPointOnCircleX(double originX, double degree, double radius){
        return (originX + radius * Math.cos(degree));
    }
    public static double getPointOnCircleY(double originY, double degree, double radius){
        return (originY + radius * Math.sin(degree));
    }
}
