package com.szclock.demo.entities;

import com.badlogic.gdx.math.Vector2;

public class RenderItem {
    private Vector2 position;
    private double rotation;
    private Vector2 scale;
    private String texture;

    public RenderItem(Vector2 position, Vector2 scale, double rotation, String texture){
        this.position = new Vector2(position);
        this.scale = new Vector2(scale);
        this.rotation = rotation;
        this.texture = texture;
    }

    public RenderItem(Vector2 position, String texture){
        this(position, new Vector2(1, 1), 0, texture);
    }
    public RenderItem(Vector2 position, Vector2 scale,  String texture){
        this(position, scale, 0, texture);
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

    @Override
    public String toString() {
        return "RenderItem{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                ", texture='" + texture + '\'' +
                '}';
    }
}
