package com.szclock.demo.entities;

import com.badlogic.gdx.math.Vector2;

public class RenderAtom {
    private Vector2 position;
    private double rotation;
    private Vector2 scale;
    private String texture;

    public RenderAtom(Vector2 position, Vector2 scale, double rotation, String texture){
        this.position = new Vector2(position);
        this.scale = new Vector2(scale);
        this.rotation = rotation;
        this.texture = texture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public double getRotation() {
        return rotation;
    }

    public Vector2 getScale() {
        return scale;
    }

    public String getTexture() {
        return texture;
    }
}
