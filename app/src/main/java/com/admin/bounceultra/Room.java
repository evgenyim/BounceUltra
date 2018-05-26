package com.admin.bounceultra;

import java.util.ArrayList;

public class Room {
    static ArrayList <GameObject> ObjectList = new ArrayList<GameObject>();
    public static void create() {
        Point start_point = new Point(100, 100);
        Ball ball = new Ball(start_point, 50);
        ObjectList.add(ball);
        Obstacle obst = new Obstacle();
        obst.x_left = 100;
        obst.y_top = 100;
        obst.x_left = 300;
        obst.y_bottom = 300;
        ObjectList.add(obst);
    }
}
