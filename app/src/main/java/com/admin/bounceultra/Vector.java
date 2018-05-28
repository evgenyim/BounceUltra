package com.admin.bounceultra;

public class Vector {
    float x;
    float y;

    float scalar(Vector vec) {
        return x * vec.x + y * vec.y;
    }

    float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    float angle(Vector vec) {
        return (float) Math.acos(scalar(vec) / (length() * vec.length()));
    }

    public static float dPointSegment(Point a, Point b, Point c) {
        Vector ab = new Vector(a, b);
        Vector ac = new Vector(a, c);
        Vector bc = new Vector(b, c);
        Vector ba = new Vector(b, a);
        if (ac.scalar(ab) * bc.scalar(ba) > 0) {
            double al = b.y - a.y;
            double bl = a.x - b.x;
            double div = Math.sqrt(al * al + bl * bl);
            al /= div;
            bl /= div;
            double cl = -b.x * al - b.y * bl;
            return (float) (Math.abs(al * c.x + bl * c.y + cl) / Math.sqrt(al * al + bl * bl));
        } else {
            return Math.min(a.dist(c), b.dist(c));
        }
    }

    Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }
    Vector(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    Vector(Point p1, Point p2) {
        this.x = p1.x - p2.x;
        this.y = p1.y - p2.y;
    }
    Vector(Segment segment) {
        this.x = segment.a.x - segment.b.x;
        this.y = segment.a.y - segment.b.y;
    }

}
