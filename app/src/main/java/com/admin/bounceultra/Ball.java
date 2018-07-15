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
    double mu = 1 - 2e-2;
    double g = MainMenu.height * 1.0 / 1500;
    float decreas = (float) 0.2;
    float eps = (float) 1e-1;
    boolean stop;
    Vector velocity;
    ArrayList<GameObject> inventory = new ArrayList<>();


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

    void move(ArrayList<GameObject> ObjectList, boolean draft) {
        if (stop) {
            return;
        }
        velocity = new Vector(x_speed, y_speed);
        Segment bias = new Segment(x, y, x + velocity.x, y + velocity.y);
        Ball future_ball = new Ball(x + x_speed, y + y_speed, r);

        float min_d = 1000000;
        int intersected_seg_ind = -1;
        int intersected_obj_ind = -1;

        for (int i = 0; i < ObjectList.size(); i++) {
            ArrayList<Segment> segments = ObjectList.get(i).segments;
            for(int j = 0; j < segments.size(); j++) {
                if (segments.get(j).intersect_ball(future_ball) || Segment.segments_intersect(bias, segments.get(j))) {
                    if (Vector.dPointSegment(segments.get(j).a, segments.get(j).b, centre()) < min_d) {
                        min_d = Vector.dPointSegment(segments.get(j).a, segments.get(j).b, centre());
                        intersected_seg_ind = j;
                        intersected_obj_ind = i;
                    }
                }
            }
        }

        Segment cur_seg;
        if (intersected_seg_ind != -1) {
            cur_seg = ObjectList.get(intersected_obj_ind).segments.get(intersected_seg_ind);
            cur_seg.comunicate(this, intersected_seg_ind, cur_seg, ObjectList, intersected_obj_ind, min_d, draft);
        } else {
            y_speed += g;
            x += x_speed;
            y += y_speed;
        }
        Log.d("x", String.valueOf(x));
        Log.d("y", String.valueOf(y));
    }

    void roll(Segment segment) {
        Vector horizon = new Vector(1, 0);
        Vector seg = new Vector(segment);
        float betta = Math.abs(Vector.good_angle(seg, horizon));
        float a = (float) (g * Math.sin(betta));
        Vector vel = new Vector(x_speed, y_speed);
        float alfa = Vector.good_angle(vel, seg);
        vel = Vector.rotate_by_angle(vel, alfa);
        float sin = seg.y / seg.length();
        float cos = seg.x / seg.length();
        Vector axi = new Vector(a * cos, a * sin);
        if (axi.y < 0) {
            axi.reverse();
        }
        vel.add(axi);
        if (vel.length() < eps) {
            vel.x = 0;
            vel.y = 0;
        }
        vel.x *= mu;
        vel.y *= mu;
        velocity.x = vel.x;
        velocity.y = vel.y;
    }

    boolean stick_to_segment(Segment segment) {
        Vector seg = new Vector(segment);
        Vector vel = new Vector(x_speed, y_speed);
        float alfa = Vector.good_angle(vel, seg);
        if (Math.abs(vel.length() * Math.sin(alfa)) <  1) {
            return true;
        } else {
            return false;
        }
    }

    void MindTheGap(Segment segment, float axil, float min_d) {
        if (velocity.x == 0 && velocity.y == 0) {
            return;
        }
        Segment bias = new Segment(x, y, x + velocity.x, y + velocity.y);
        Line n = new Line(bias);
        Line m = new Line(segment);
        Point A = Line.intersect(n, m);
        float l;
        float d;
        float new_x;
        float new_y;
        if ((A == null)) {
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
            if ((min_d == centre().dist(segment.a)) || (min_d == centre().dist(segment.b))) {
                Point p;
                if (min_d == centre().dist(segment.a)) {
                    p = segment.a;
                } else {
                    p = segment.b;
                }


                float a = (n.a / n.b) * (n.a / n.b) + 1;
                float b = (2 * (p.y + n.c / n.b) * (n.a / n.b) - 2 * p.x);
                float c = (p.y + n.c / n.b) * (p.y + n.c / n.b) + p.x * p.x - r * r;
                float discr = b * b - 4 * a * c;

                float x1 = (float) ((-b + sqrt(discr)) / (2 * a));
                float y1;
                float y_1_1 = (float) (p.y - sqrt(r * r - (p.x - x1) * (p.x - x1)));
                float y_1_2 = (float) (p.y + sqrt(r * r - (p.x - x1) * (p.x - x1)));
                float check_1 = abs (n.b * y_1_1);
                float check_2 = abs (n.b * y_1_2);
                if (check_1 < check_2) {
                    y1 = y_1_1;
                } else {
                    y1 = y_1_2;
                }

                float x2 = (float) ((-b - sqrt(discr)) / (2 * a));
                float y2;
                float y_2_1 = (float) (p.y - sqrt(r * r - (p.x - x2) * (p.x - x2)));
                float y_2_2 = (float) (p.y + sqrt(r * r - (p.x - x2) * (p.x - x2)));
                check_1 = abs (n.b * y_2_1);
                check_2 = abs (n.b * y_2_2);
                if (check_1 < check_2) {
                    y2 = y_2_1;
                } else {
                    y2 = y_2_2;
                }
                Log.d("asdfghjk", "asdfghjk");
                Log.d("x1", String.valueOf(x1));
                Log.d("x2", String.valueOf(x2));
                Log.d("y1", String.valueOf(y1));
                Log.d("y2", String.valueOf(y2));
                Log.d("y_1_1", String.valueOf(y_1_1));
                Log.d("y_1_2", String.valueOf(y_1_2));
                Log.d("y_2_1", String.valueOf(y_2_1));
                Log.d("y_2_2", String.valueOf(y_2_2));
                Log.d("n.a", String.valueOf(n.a));
                Log.d("n.b", String.valueOf(n.b));
                Log.d("n.c", String.valueOf(n.c));

                Point centre1 = new Point(x1, y1);
                if (Vector.dPointSegment(centre1, segment.a, segment.b) == r) {
                    new_x = x1;
                    new_y = y1;
                } else {
                    new_x = x2;
                    new_y = y2;
                }
            } else {
                float alfa = Segment.angle(bias, segment);
                l = (float) sqrt((x - A.x) * (x - A.x) + (y - A.y) * (y - A.y));
                d = (float) (r / sin(alfa));
                new_x = x + (A.x - x) * (l - d) / l;
                new_y = y + (A.y - y) * (l - d) / l;
            }
        }

        Segment real_bias = new Segment(x, y, new_x, new_y);
        if (bias.length() == 0) {
            axil = 1;
        } else {
            axil = real_bias.length() / bias.length();
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
