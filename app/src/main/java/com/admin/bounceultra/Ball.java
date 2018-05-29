package com.admin.bounceultra;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

class Ball extends GameObject {
    float x;
    float y;
    float r;
    float x_speed;
    float y_speed;
    double k = 2e-2;
    double g = MainActivity.height * 1.0 / 1500;
    double decreas = 0.2;

    void shot(float to_x, float to_y) {
        x_speed += (to_x - x) * k;
        y_speed += (to_y - y) * k;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }

    void move() {
        if (y + y_speed > MainActivity.height - r - 300) {
            y_speed *= -1;
            y_speed += g;
            y_speed *= 1 - decreas;
            if (Math.abs(y_speed) < 1) {
                y_speed = 0;
            }
        }

        if (x + x_speed > MainActivity.width - r) {
            x_speed *= -1;
        }

        if (x + x_speed < r) {
            x_speed *= -(1 - decreas);
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
