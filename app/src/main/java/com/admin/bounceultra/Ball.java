package com.admin.bounceultra;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

class Ball extends GameObject {
    float x;
    float y;
    float r;
    float x_speed;
    float y_speed;
    static double k = 2e-2;
    static double g = MainActivity.height * 1.0 / 1500;
    static double decreas = 0.2;
    Vector velocity;

    void shot(float to_x, float to_y) {
        x_speed += (to_x - x) * k;
        y_speed += (to_y - y) * k;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }

    static void move(Ball ball, ArrayList<Segment> segments) {
        if (ball.y + ball.y_speed > MainActivity.height - ball.r - 300) {
            ball.y_speed *= -1;
            ball.y_speed += g;
            ball.y_speed *= 1 - decreas;
            if (Math.abs(ball.y_speed) < 1) {
                ball.y_speed = 0;
            }
        }

        if (ball.x + ball.x_speed > MainActivity.width - ball.r) {
            ball.x_speed *= -1;
        }

        if (ball.x + ball.x_speed < ball.r) {
            ball.x_speed *= -(1 - decreas);
        }

        if (ball.y_speed == 0) {
            ball.x_speed *= 0.99;
            if (ball.x_speed < 1e-4) {
                ball.x_speed = 0;
            }
        }

        for (int i = 0; i < segments.size(); i++) {
            ball.x += ball.x_speed;
            ball.y += ball.y_speed;
            if (segments.get(i).intersect_ball(ball)) {
                Log.d("huy", "hay");
                ball.velocity = new Vector(ball.x_speed, ball.y_speed);
                Vector segment = new Vector(segments.get(i));
                ball.velocity = Vector.reflect(ball.velocity, segment);
                ball.x_speed = ball.velocity.x;
                ball.y_speed = ball.velocity.y;
            } else {
                ball.x -= ball.x_speed;
                ball.y -= ball.y_speed;
            }
        }

        ball.x += ball.x_speed;
        ball.y += ball.y_speed;
        ball.y_speed += g;
    }

    Ball(Point p, float r) {
        this.x = p.x;
        this.y = p.y;
        this.r = r;
        this.x_speed = 0;
        this.y_speed = 0;
    }
}
