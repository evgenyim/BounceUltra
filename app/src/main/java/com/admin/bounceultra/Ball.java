package com.admin.bounceultra;

import static java.lang.Math.*;
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
    static float eps = (float) 1e-4;
    Vector velocity;


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
        ball.velocity = new Vector(ball.x_speed, ball.y_speed);
        Segment bias = new Segment(ball.x, ball.y, ball.x + ball.velocity.x, ball.y + ball.velocity.y);
        Ball future_ball = new Ball(ball.x + ball.x_speed, ball.y + ball.y_speed, ball.r);
        float min_d = 1000000;
        int index = -1;

        for (int i = 0; i < segments.size(); i++) {
            if (Segment.segments_intersect(bias, segments.get(i)) || segments.get(i).intersect_ball(future_ball)) {
                if (Vector.dPointSegment(segments.get(i).a, segments.get(i).b, ball.centre()) < min_d) {
                    min_d = Vector.dPointSegment(segments.get(i).a, segments.get(i).b, ball.centre());
                    index = i;
                }
            }
        }
        if (index != - 1) {
            MindTheGap(ball, segments.get(index));
            min_d = Vector.dPointSegment(segments.get(index).a, segments.get(index).b, ball.centre());
            Vector segment = new Vector(segments.get(index));
            if (min_d  == ball.centre().dist(segments.get(index).a) || min_d  == ball.centre().dist(segments.get(index).b)) {
                Vector new_velocity;
                if (min_d  == ball.centre().dist(segments.get(index).a)) {
                    new_velocity = new Vector(segments.get(index).a, ball.centre());
                } else {
                    new_velocity = new Vector(segments.get(index).b, ball.centre());
                }
                new_velocity.unit();
                new_velocity.multiplying(ball.velocity.length());
                ball.velocity = new_velocity;
            } else {
                ball.velocity = Vector.reflect(ball.velocity, segment);
                roll(ball, segments.get(index));
            }
            ball.x_speed = ball.velocity.x * (1 - decreas);
            ball.y_speed = ball.velocity.y * (1 - decreas);
            return;
        }

        ball.x += ball.x_speed;
        ball.y += ball.y_speed;
        ball.y_speed += g;
    }

    static void roll(Ball ball, Segment segment) {
        Vector seg = new Vector(segment);
        Vector vel = new Vector(ball.x_speed, ball.y_speed);
        float alfa = Vector.good_angle(vel, seg);
        vel.write();
        if (Math.abs(vel.length() * Math.sin(alfa) * decreas) <  g + eps) {
            vel = Vector.rotate_by_angle(vel, alfa);
            ball.velocity.x = vel.x;
            ball.velocity.y = vel.y;
        }
    }

    static void MindTheGap(Ball ball, Segment segment) {
        Segment bias = new Segment(ball.x, ball.y, ball.x + ball.velocity.x, ball.y + ball.velocity.y);
        Line n = new Line(bias);
        Line m = new Line(segment);
        Point A = Line.intersect(n, m);
        float l;
        float d;
        float new_x;
        float new_y;
        if (A == null) {
            Vector normal = Segment.normal(segment);
            Point E;
            if (ball.centre().dist(segment.a) < ball.centre().dist(segment.b)) {
                E = segment.a;
            } else{
                E = segment.b;
            }
            Line p = new Line(E, normal);
            Point B = Line.intersect(n, p);
            l = (float) sqrt((ball.x - B.x) * (ball.x - B.x) + (ball.y - B.y) * (ball.y - B.y));
            d = (float) sqrt(Math.abs(ball.r * ball.r - E.dist(B) * E.dist(B)));
            new_x = ball.x + (B.x - ball.x) * (l - d) / l;
            new_y = ball.y + (B.y - ball.y) * (l - d) / l;
        } else {
            float alfa = Segment.angle(bias, segment);
            l = (float) sqrt((ball.x - A.x) * (ball.x - A.x) + (ball.y - A.y) * (ball.y - A.y));
            d = (float) (ball.r / sin(alfa));
            new_x = ball.x + (A.x - ball.x) * (l - d) / l;
            new_y = ball.y + (A.y - ball.y) * (l - d) / l;
        }

        ball.x = new_x;
        ball.y = new_y;
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
