package com.admin.bounceultra;

import java.util.ArrayList;

public class Switch extends GameObject {

    GameObject target;

    void act() {
        target.switchAction();
    }

    Switch(float x_left,float y_top, float x_right,float y_bottom, float degrees, int imageId, GameObject target) {
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degrees = degrees;
        this.imageId = imageId;
        this.x = (x_left + x_right) / 2;
        this.y = (y_bottom + y_top) / 2;
        this.target = target;
    }


}
