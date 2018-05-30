package com.admin.bounceultra;


import android.graphics.Canvas;
import android.graphics.Paint;

public class Segment {
    Point a;
    Point b;

    static float angle(Segment segment1, Segment segment2) {
        Vector vec1 = new Vector(segment1);
        Vector vec2 = new Vector(segment2);
        return vec1.angle(vec2);
    }

    boolean intersect_ball(Ball ball) {
        Point ball_center = new Point(ball.x, ball.y);
        float dist = Vector.dPointSegment(a, b, ball_center);
        if (dist > ball.r) {
            return false;
        }
        return true;
    }
    void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(a.x,a.y,b.x,b.y,paint);
    }

    Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    Segment(float x1, float y1, float x2, float y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }
}
