package com.admin.bounceultra;

import java.util.ArrayList;

class Room {
    ArrayList <GameObject> ObjectList = new ArrayList<GameObject>();
    ArrayList <Segment> segments = new ArrayList<Segment>();
    Ball ball;
    static Obstacle obstacle;

    void addObstacle(float x_left, float y_top, float x_right, float y_bottom) {
        obstacle = new Obstacle(x_left, y_top, x_right, y_bottom);
        ObjectList.add(obstacle);
    }
    void addSegments(float x_left, float y_top, float x_right, float y_bottom){
        Point point_left_top = new Point(x_left, y_top);
        Point point_right_top = new Point(x_right, y_top);
        Point point_left_bottom = new Point(x_left, y_bottom);
        Point point_right_bottom = new Point(x_right, y_bottom);
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
        ball = new Ball(p,r);
    }
}