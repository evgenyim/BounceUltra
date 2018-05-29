package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

public class RoomCreate {
    static ArrayList<Room> RoomList1 = new ArrayList<>();
    public static ArrayList<Room> create() {
        Point start_point = new Point(400, 400);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addObstacle(500,500,600,600);
        RoomList1.add(Room0);
        Room Room1 = new Room();
        Point start_point2 = new Point(800,800);
        Room1.addBall(start_point2,100);
        Room1.addObstacle(800,800,900,900);
        RoomList1.add(Room1);
        return RoomList1;
    }

}
