package com.admin.bounceultra;

import java.util.ArrayList;

public class Room {
    static ArrayList <GameObject> ObjectList = new ArrayList<GameObject>();
    static ArrayList <Segment> segments = new ArrayList<Segment>();
    static Ball ball;
    static Obstacle obstacle;

    public static void addObstacle(float x_left, float y_top, float x_right, float y_bottom) {
        obstacle = new Obstacle(x_left, y_top, x_right, y_bottom);
        ObjectList.add(obstacle);
    }
    public static void addBall(Point p, float r) {
        ball = new Ball(p,r);
        ObjectList.add(ball);
    }
}
