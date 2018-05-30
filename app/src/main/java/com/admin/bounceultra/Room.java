package com.admin.bounceultra;

import java.util.ArrayList;

import static java.lang.Math.*;

class Room {
    ArrayList<GameObject> ObjectList = new ArrayList<GameObject>();
    ArrayList<Segment> segments = new ArrayList<Segment>();
    Ball ball;
    static Obstacle obstacle;

    void addObstacle(float x_left, float y_top, float x_right, float y_bottom, float degrees, int id) {
        obstacle = new Obstacle(x_left, y_top, x_right, y_bottom, degrees, id);
        ObjectList.add(obstacle);
    }

    void addSegments(float x_left, float y_top, float x_right, float y_bottom, float degree) {
        degree = (float)(toRadians(degree));
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Segment segm_top = new Segment(point_left_top, point_right_top);
        Segment segm_right = new Segment(point_right_top, point_right_bottom);
        Segment segm_bottom = new Segment(point_left_bottom, point_right_bottom);
        Segment segm_left = new Segment(point_left_top, point_left_bottom);
        segments.add(segm_top);
        segments.add(segm_right);
        segments.add(segm_bottom);
        segments.add(segm_left);
    }

    void addBall(Point p, float r) {
        ball = new Ball(p, r);
    }
}