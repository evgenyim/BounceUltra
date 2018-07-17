package com.admin.bounceultra;

import static java.lang.Math.toRadians;

class Gate extends GameObject implements Cloneable{

    Gate(float x_left,float y_top,float x_right,float y_bottom, float degrees, int next_room_id, int id) {
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degrees = degrees;
        this.next_room_id = next_room_id;
        this.id = id;
        degrees = (float) (toRadians(degrees));
        start_x = (this.x_left + this.x_right) / 2;
        start_y = this.y_top + 2;
        Point A = new Point(this.x_left, this.y_top);
        Point B = new Point(start_x, start_y);
        Vector vec = new Vector(A,B);
        Vector new_vec = Vector.rotate_by_angle(vec,(float) degrees);
        start_x = this.x_left + new_vec.x;
        start_y = this.y_top + new_vec.y;
        this.x = (x_left + x_right) / 2;
        this.y = (y_bottom + y_top) / 2;
    }

    public Gate clone() throws CloneNotSupportedException{

        Gate newGate = (Gate) super.clone();
        return newGate;
    }
}