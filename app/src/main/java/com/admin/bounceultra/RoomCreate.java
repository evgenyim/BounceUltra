package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;


public class RoomCreate {
    static ArrayList<Room> RoomList1;
    public static ArrayList<Room> create() {
        int alfa_chanel = 0;
        int cube = 1;
        int brick = 2;
        int branch = 3;
        RoomList1 = new ArrayList<>();
        Point start_point = new Point(75, MainMenu.height - 100);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addLock(500, MainMenu.height / 2 - 100, 700, MainMenu.height / 2 + 100, 0, brick);
        Room0.addItem(0, 300, 300, 400, 0, "key", branch);
        Room0.addObstacle(0, 400,  300, 500, 0, brick);
        Room0.addObstacle(500, 0, 700, MainMenu.height / 2 - 100, 0, cube);
        Room0.addObstacle(500, MainMenu.height / 2 + 100, 700, MainMenu.height, 0, cube);
        Room0.addObstacle(0, 0, MainMenu.width, MainMenu.height,0, alfa_chanel);
        Room0.addGate(MainMenu.width - 200,  MainMenu.height - 100, MainMenu.width, MainMenu.height,0,1, cube);
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
