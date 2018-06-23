package com.admin.bounceultra;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

class Gate extends GameObject implements Cloneable{

    float x_left;
    float y_top;
    float x_right;
    float y_bottom;
    double degree;

    Gate(float x_left,float y_top,float x_right,float y_bottom, double degree, int next_room_id, int id) {
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degree = degree;
        this.next_room_id = next_room_id;
        this.id = id;
        degree = (toRadians(degree));
        start_x = (this.x_left + this.x_right) / 2;
        start_y = this.y_top + 2;
        Point A = new Point(this.x_left, this.y_top);
        Point B = new Point(start_x, start_y);
        Vector vec = new Vector(A,B);
        Vector new_vec = Vector.rotate_by_angle(vec,(float) degree);
        start_x = this.x_left + new_vec.x;
        start_y = this.y_top + new_vec.y;
        this.x = (x_left + x_right) / 2;
        this.y = (y_bottom + y_top) / 2;
    }

    void moveToXY(float newX, float newY){
        float deltaX = newX - x;
        float deltaY = newY - y;
        x_right += deltaX;
        x_left += deltaX;
        y_top += deltaY;
        y_bottom += deltaY;
        x += deltaX;
        y += deltaY;
    }

    void compress(float k) {
        float width = x_right - x_left;
        float hight = y_top - y_bottom;
        x_left = x - width / k / 2;
        x_right = x + width / k / 2;
        y_bottom = y - hight / k / 2;
        y_top = y + hight / k / 2;
    }

    void draw(Canvas canvas, Paint paint, Bitmap bitmap) {
        Matrix m = new Matrix();
        m.setTranslate(x_left, y_top);
        m.preRotate((float) degree);
        canvas.drawBitmap(bitmap, m, null);
    }

    public Gate clone() throws CloneNotSupportedException{

        Gate newGate = (Gate) super.clone();
        return newGate;
    }
}
