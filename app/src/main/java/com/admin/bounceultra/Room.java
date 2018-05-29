package com.admin.bounceultra;

import java.util.ArrayList;

class Room {
    ArrayList <GameObject> ObjectList = new ArrayList<GameObject>();
    static ArrayList <Segment> segments = new ArrayList<>();
    Ball ball;

    void addObstacle(float x_left, float y_top, float x_right, float y_bottom) {
        Obstacle obst = new Obstacle(x_left, y_top, x_right, y_bottom);
        ObjectList.add(obst);
    }
    void addBall(Point p, float r) {
        ball = new Ball(p,r);
    }
}
