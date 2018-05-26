package com.admin.bounceultra;


import android.graphics.Canvas;
import android.graphics.Paint;

class Ball extends GameObject {
    static float x;
    static float y;
    static float r;
    static float x_speed;
    static float y_speed;
    static double k = 1e-4;
    static double g = 1e-4;

    public static void shot(float to_x, float to_y) {
        x_speed += (to_x - x) * k;
        y_speed += (to_y - y) * k;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }

    public static void move() {
        x += x_speed;
        y += y_speed;
        y_speed -= g;
    }

    Ball(Point p, float r) {
        this.x = p.x;
        this.y = p.y;
        this.r = r;
    }
}
