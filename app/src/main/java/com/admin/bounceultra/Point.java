package com.admin.bounceultra;

class Point {
    float x;
    float y;

    float dist(Point a) {
        return (float) Math.sqrt((x - a.x) * (x - a.x) + (y - a.y) * (y - a.y));
    }
    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}