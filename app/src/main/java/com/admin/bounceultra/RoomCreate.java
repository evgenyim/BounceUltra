package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

public class RoomCreate {
    static ArrayList<Room> RoomList1;
    public static ArrayList<Room> create() {
        RoomList1 = new ArrayList<>();
        Point start_point = new Point(900, 51);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addLock(600, 300, 700, 400, 0, 0);
        Room0.addItem(200, 200, 500, 300, 0, "key", 5);
        Room0.addObstacle(MainMenu.width - 300, MainMenu.height / 2 - 100, MainMenu.width, MainMenu.height / 2, 0, 4);
        Room0.addObstacle(0, 0, MainMenu.width, MainMenu.height,0,1);
        Room0.addGate(200, (float) (MainMenu.height * 0.9) - 100,400, (float) (MainMenu.height * 0.9),0,1, 3);
        RoomList1.add(Room0);

        Room Room1 = new Room();
        Point start_point1 = new Point(300, 300);
        Room1.addGate(MainMenu.width / 2,100,MainMenu.width / 2 + 200,200, 180,0,3);
        Room1.addBall(start_point1,50);
        Room1.addObstacle(0, 0, MainMenu.width, MainMenu.height,0,1);
        Room1.addObstacle((MainMenu.width / 2 + 200), MainMenu.height / 4, (MainMenu.width / 2) + 202, MainMenu.height ,  80, 2);
        Room1.addObstacle(MainMenu.width / 2, MainMenu.height / 2, MainMenu.width / 2 + 2, MainMenu.height - 10, 0, 2);
        Room1.addGate(0, (float) (MainMenu.height * 0.9)-100,200, (float) (MainMenu.height * 0.9),180,1   , 3);
        RoomList1.add(Room1);

        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room0);
        RoomList1.add(Room1);
        return RoomList1;
    }

}
