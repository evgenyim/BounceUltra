package com.admin.bounceultra;

import java.util.ArrayList;

class Room {
    ArrayList <GameObject> ObjectList = new ArrayList<GameObject>();
    ArrayList <Segment> segments = new ArrayList<>();
    Ball ball;
    static Obstacle obstacle;


    void addObstacle(float x_left, float y_top, float x_right, float y_bottom, float degrees, int id) {
        obstacle = new Obstacle(x_left, y_top, x_right, y_bottom, degrees, id);
        ObjectList.add(obstacle);
    }
    void addBall(Point p, float r) {
        ball = new Ball(p,r);
    }
}
