package com.admin.bounceultra;

public class Hole extends GameObject {
    Hole(float x_left, float y_top, float x_right, float y_bottom, float degrees, int imageId , Point startPoint) {
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degrees = degrees;
        this.imageId = imageId;
        this.x = (x_left + x_right) / 2;
        this.y = (y_bottom + y_top) / 2;
        this.start_x = startPoint.x;
        this.start_y = startPoint.y;
    }

    public Hole clone() throws CloneNotSupportedException{

        Hole newHole = (Hole) super.clone();
        return newHole;
    }
}
