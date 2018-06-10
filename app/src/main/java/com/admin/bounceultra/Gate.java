package com.admin.bounceultra;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class Gate extends GameObject {

    float x_left;
    float y_top;
    float x_right;
    float y_bottom;
    Matrix m = new Matrix();
    float degrees;

    Gate(float x_left,float y_top,float x_right,float y_bottom, float degrees, int next_room_id, int id) {
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degrees = degrees;
        this.next_room_id = next_room_id;
        this.id = id;
        x = (this.x_left + this.x_right) / 2;
        y = this.y_bottom + 2;
    }
    void draw(Canvas canvas, Paint paint, Bitmap bitmap) {
        m.setTranslate(x_left, y_top);
        m.preRotate(degrees);
        canvas.drawBitmap(bitmap, m, null);
    }
}
