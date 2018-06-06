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
    double k = 5e-2;
    double g = MainActivity.height * 1.0 / 1500;
    float decreas = (float) 0.2;
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

    void move(ArrayList<GameObject> ObjectList) {
        /*if (ball.y + ball.y_speed > MainActivity.height - ball.r - 300) {
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
        }*/


        velocity = new Vector(x_speed, y_speed);
        Segment bias = new Segment(x, y, x + velocity.x, y + velocity.y);
        Ball future_ball = new Ball(x + x_speed, y + y_speed, r);
        float min_d = 1000000;
        int index = -1;
        int index1 = -1;

        for (int i = 0; i < ObjectList.size(); i++) {
            ArrayList<Segment> segments = ObjectList.get(i).segments;
            for(int j = 0; j < segments.size(); j++) {
                if (Segment.segments_intersect(bias, segments.get(j)) || segments.get(j).intersect_ball(future_ball)) {
                    if (Vector.dPointSegment(segments.get(j).a, segments.get(j).b, centre()) < min_d) {
                        min_d = Vector.dPointSegment(segments.get(j).a, segments.get(j).b, centre());
                        index = j;
                        index1 = i;
                    }
                }
            }
        }
        if (index != - 1) {
            ArrayList<Segment> segments = ObjectList.get(index1).segments;
            MindTheGap(segments.get(index));
            min_d = Vector.dPointSegment(segments.get(index).a, segments.get(index).b, centre());
            Vector segment = new Vector(segments.get(index));
            if (min_d  == centre().dist(segments.get(index).a) || min_d  == centre().dist(segments.get(index).b)) {
                Vector new_velocity;
                if (min_d  == centre().dist(segments.get(index).a)) {
                    new_velocity = new Vector(segments.get(index).a, centre());
                } else {
                    new_velocity = new Vector(segments.get(index).b, centre());
                }
                new_velocity.unit();
                new_velocity.multiplying(velocity.length());
                velocity = new_velocity;
            } else {
                velocity = Vector.reflect(velocity, segment);
            }
            x_speed = velocity.x * (1 - decreas);
            y_speed = velocity.y * (1 - decreas);
            return;
        }

        x += x_speed;
        y += y_speed;
        y_speed += g;
    }

    void MindTheGap(Segment segment) {
        Segment bias = new Segment(x, y, x + velocity.x, y + velocity.y);
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
            if (centre().dist(segment.a) < centre().dist(segment.b)) {
                E = segment.a;
            } else{
                E = segment.b;
            }
            Line p = new Line(E, normal);
            Point B = Line.intersect(n, p);
            l = (float) sqrt((x - B.x) * (x - B.x) + (y - B.y) * (y - B.y));
            d = (float) sqrt(Math.abs(r * r - E.dist(B) * E.dist(B)));
            new_x = x + (B.x - x) * (l - d) / l;
            new_y = y + (B.y - y) * (l - d) / l;
        } else {
            float alfa = Segment.angle(bias, segment);
            l = (float) sqrt((x - A.x) * (x - A.x) + (y - A.y) * (y - A.y));
            d = (float) (r / sin(alfa));
            new_x = x + (A.x - x) * (l - d) / l;
            new_y = y + (A.y - y) * (l - d) / l;
        }
        x = new_x;
        y = new_y;
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
