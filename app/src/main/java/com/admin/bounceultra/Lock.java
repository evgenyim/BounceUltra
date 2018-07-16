package com.admin.bounceultra;

public class Lock extends GameObject {
    Lock(float x_left, float y_top, float x_right, float y_bottom, float degrees, int id) {
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degrees = degrees;
        this.id = id;
        this.x = (x_left + x_right) / 2;
        this.y = (y_bottom + y_top) / 2;
    }
}
