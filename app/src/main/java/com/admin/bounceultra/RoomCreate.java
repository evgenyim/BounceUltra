package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

public class RoomCreate {
    static ArrayList<Room> RoomList1 = new ArrayList<>();
    public static ArrayList<Room> create() {
        Point start_point = new Point(100, 500);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addObstacle(0,0,0,0, 0, 0);
        Room0.addSegments(100, 100, 600, 600, 0);
        RoomList1.add(Room0);
        Room Room1 = new Room();
        Point start_point1 = new Point(MainActivity.width/2 + 200, 0);
        Room1.addBall(start_point1,50);
        Room1.addObstacle(800,800,900,900, 30, 0);
        Room1.addObstacle((MainActivity.width  / 2), MainActivity.height / 4, (MainActivity.width / 2), MainActivity.height, 0, 2);
        Room1.addSegments((MainActivity.width  / 2), MainActivity.height / 4, (MainActivity.width / 2 + 30), MainActivity.height, 0);
        Room1.addSegments(800, 800, 900, 900, 30);
        RoomList1.add(Room1);
        return RoomList1;
    }

}
