package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

public class RoomCreate {
    static ArrayList<Room> RoomList1 = new ArrayList<>();
    public static ArrayList<Room> create() {
        Point start_point = new Point(1000, 1000);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addObstacle(0,0,0,0, 0, 0);
        Room0.addSegments(100, 100, 600, 600);
        RoomList1.add(Room0);
        Room Room1 = new Room();
        Point start_point1 = new Point(500,500);
        Room1.addBall(start_point1,100);
        Room1.addObstacle(800,800,900,900, 0, 0);
        Room1.addSegments(800, 800, 900, 900);
        RoomList1.add(Room1);
        return RoomList1;
    }

}
