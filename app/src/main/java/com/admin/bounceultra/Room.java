package com.admin.bounceultra;

import java.util.ArrayList;

public class Room {
    static ArrayList <GameObject> ObjectList = new ArrayList<GameObject>();
    static ArrayList <Segment> segments = new ArrayList<>();
    static Ball ball;

    public static void addObstacle(float x_left, float y_top, float x_right, float y_bottom) {
        Obstacle obst = new Obstacle(x_left, y_top, x_right, y_bottom);
        ObjectList.add(obst);
    }
    public static void addBall(Point p, float r) {
        ball = new Ball(p,r);
        ObjectList.add(ball);
    }
}
