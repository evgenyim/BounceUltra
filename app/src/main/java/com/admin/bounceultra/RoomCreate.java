package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

public class RoomCreate {
    static ArrayList<Room> RoomList1 = new ArrayList<>();
    public static ArrayList<Room> create() {
        Point start_point = new Point(100, 500);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addObstacle(0, 0, MainActivity.width, MainActivity.height - 200,0,1);
        Room0.addGate(500,500,700,600,0,1, 3);
        RoomList1.add(Room0);
        Room Room1 = new Room();
        Point start_point1 = new Point(300, 300);
        Room1.addBall(start_point1,50);
        Room1.addObstacle(700,1200,900,1300, 0, 1);
        Room1.addObstacle(0, 0, MainActivity.width, MainActivity.height - 200,0,1);
        Room1.addObstacle((MainActivity.width  / 2), MainActivity.height / 4, (MainActivity.width / 2) + 2, MainActivity.height,  80, 2);
        Room1.addGate(800,350,1000,450,0,0,3);
        RoomList1.add(Room1);
        return RoomList1;
    }

}
