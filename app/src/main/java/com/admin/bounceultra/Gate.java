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

class Gate extends GameObject {

    float x_left;
    float y_top;
    float x_right;
    float y_bottom;
    Matrix m = new Matrix();
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
        x = (this.x_left + this.x_right) / 2;
        y = this.y_top + 2;
        Point A = new Point(this.x_left, this.y_top);
        Point B = new Point(x, y);
        Vector vec = new Vector(A,B);
        Vector new_vec = Vector.rotate_by_angle(vec,(float) degree);
        x = this.x_left + new_vec.x;
        y = this.y_top + new_vec.y;
        Log.d("x", String.valueOf(x));
        Log.d("y", String.valueOf(y));
    }
    void draw(Canvas canvas, Paint paint, Bitmap bitmap) {
        m.setTranslate(x_left, y_top);
        m.preRotate((float) degree);
        canvas.drawBitmap(bitmap, m, null);
    }
}
