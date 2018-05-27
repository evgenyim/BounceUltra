package com.admin.bounceultra;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

class Ball extends GameObject {
    static float x;
    static float y;
    static float r;
    static float x_speed;
    static float y_speed;
    static double k = 1e-2;
    static double g = MainActivity.height * 1.0 / 1500;

    void shot(float to_x, float to_y) {
        x_speed += (to_x - x) * k;
        y_speed += (to_y - y) * k;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }

    public static void move() {
        if (y + y_speed > MainActivity.height - r - 300) {
            y_speed *= -1;
            y_speed += g;
            y_speed *= 0.8;
            if (Math.abs(y_speed) < 1) {
                y_speed = 0;
            }
        }
        
        x += x_speed;
        y += y_speed;
        y_speed += g;
    }

    Ball(Point p, float r) {
        this.x = p.x;
        this.y = p.y;
        this.r = r;
        this.x_speed = 0;
        this.y_speed = 0;
    }
}
