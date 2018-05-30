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
    static double k = 5e-2;
    static double g = MainActivity.height * 1.0 / 1500;
    static float decreas = (float) 0.2;
    Vector velocity;
    static int index;

    void shot(float to_x, float to_y) {
        x_speed += (to_x) * k;
        y_speed += (to_y) * k;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(x, y, r, paint);
    }

    Point centre() {
        Point p = new Point(x, y);
        return p;
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
            ball.x_speed *= -1;
        }


        ball.velocity = new Vector(ball.x_speed, ball.y_speed);
        Segment bias = new Segment(ball.x, ball.y, ball.x + ball.velocity.x, ball.y + ball.velocity.y);
        Ball future_ball = new Ball(ball.x + ball.x_speed, ball.y + ball.y_speed, ball.r);
        float min_d = 1000000;
        index = -1;

        for (int i = 0; i < segments.size(); i++) {
            if (Segment.segments_intersect(bias, segments.get(i)) || segments.get(i).intersect_ball(future_ball)) {
                if (Vector.dPointSegment(segments.get(i).a, segments.get(i).b, ball.centre()) < min_d) {
                    min_d = Vector.dPointSegment(segments.get(i).a, segments.get(i).b, ball.centre());
                    index = i;
                }
            }
        }
        if (index != - 1) {
            Vector segment = new Vector(segments.get(index));
            Log.d("pre_x", String.valueOf(ball.velocity.x));
            Log.d("pre_y", String.valueOf(ball.velocity.y));
            ball.velocity = Vector.reflect(ball.velocity, segment);
            Log.d("post_x", String.valueOf(ball.velocity.x));
            Log.d("post_y", String.valueOf(ball.velocity.y));
            ball.x_speed = ball.velocity.x * (1 - decreas);
            ball.y_speed = ball.velocity.y * (1 - decreas);
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

    Ball(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
