package com.admin.bounceultra;

import android.util.Log;

public class Vector {
    float x;
    float y;

    float scalar(Vector vec) {
        return x * vec.x + y * vec.y;
    }

    float vec_mult(Vector vec) {
        return x * vec.y - vec.x * y;
    }

    float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    float angle(Vector vec) {
        float cosA = scalar(vec) / (length() * vec.length());
        if (cosA > 1) {
            return 0;
        }
        if (cosA < -1) {
            return (float) Math.PI;
        }
        return (float) Math.acos(cosA);
    }

    float orianted_angle(Vector vec) {
        float sinA = vec_mult(vec) / (length() * vec.length());
        if (sinA > 1) {
            return (float) Math.PI / 2;
        }
        if (sinA < -1) {
            return (float) - Math.PI / 2;
        }
        return (float) Math.asin(sinA);
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

    void unit(){
        float l = this.length();
        this.y = this.y / l;
        this.x = this.x / l;
    }

    Vector multiplying(float k) {
        this.x *= k;
        this.y *= k;
        return this;
    }

    static Vector normal(Vector vec) {
        Vector res = new Vector(-vec.y, vec.x);
        return res;
    }

    static Vector rotate_by_angle(Vector vec, float alfa) {
        float sinB = vec.y / vec.length();
        float cosB = vec.x / vec.length();
        float cosA = (float) Math.cos(alfa);
        float sinA = (float) Math.sin(alfa);
        float new_y = vec.length() * (cosA * sinB + sinA * cosB);
        float new_x = vec.length() * (cosA * cosB - sinA * sinB);
        return new Vector(new_x, new_y);
    }

    static Vector reflect(Vector vec1, Vector vec2) {
        float angle = vec1.angle(normal(vec2));
        if (vec1.orianted_angle(normal(vec2)) < 0) {
            angle *= -1;
        }
        vec1 = rotate_by_angle(vec1, angle * 2);
        vec1.x *= -1;
        vec1.y *= -1;
        return vec1;
    }

    void add(Vector vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    void reverse() {
        this.x *= -1;
        this.y *= -1;
    }

    boolean if_null() {
        if (this.x == 0 && this.y == 0) {
            return true;
        }
        return false;
    }

    void write() {
        Log.d("vector_x", String.valueOf(this.x));
        Log.d("vector_y", String.valueOf(this.y));
    }

    static float good_angle(Vector vec1, Vector vec2) {
        if (vec1.angle(vec2) > Math.PI / 2) {
            vec2.reverse();
        }
        return vec1.orianted_angle(vec2);
    }

    static float first_quater(float alfa) {
        alfa = (float) Math.abs(Math.asin(Math.sin(alfa)));
        return alfa;
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
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
    }
    Vector(Segment segment) {
        this.x = segment.a.x - segment.b.x;
        this.y = segment.a.y - segment.b.y;
    }

}
