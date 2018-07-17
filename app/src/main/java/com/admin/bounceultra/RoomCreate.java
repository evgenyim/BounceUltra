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
        int upLattice = 4;
        int downLattice = 5;
        int key = 6;
        int lock = 7;

        RoomList1 = new ArrayList<>();
        Point start_point = new Point(75, MainMenu.height - 100);
        Room Room0 = new Room();
        Room0.addBall(start_point,50);
        Room0.addLock(500, MainMenu.height / 2 - 100, 700, MainMenu.height / 2 + 100, 0, lock);
        Room0.addItem(0, 300, 300, 400, 0, "key", key);
        Room0.addObstacle(0, 400,  300, 500, 0, brick);
        Room0.addObstacle(500, 0, 700, MainMenu.height / 2 - 100, 0, upLattice);
        Room0.addObstacle(500, MainMenu.height / 2 + 100, 700, MainMenu.height, 0, downLattice);
        Room0.addObstacle(0, 0, MainMenu.width, MainMenu.height,0, alfa_chanel);
        Room0.addGate(MainMenu.width - 200,  MainMenu.height - 100, MainMenu.width, MainMenu.height,0,1, cube);
        RoomList1.add(Room0);

        Room Room1 = new Room();
        Point start_point1 = new Point(100, MainMenu.height - 100);
        Room1.addBall(start_point1,50);
        Room1.addObstacle(0, 0, MainMenu.width, MainMenu.height,0, alfa_chanel);
        Room1.addObstacle(0, 1000 ,  450, 1100 ,  0, brick);
        Room1.addHole(350, MainMenu.height - 10, 550, MainMenu.height,0, brick, start_point1);
        Room1.addFlow(500, 1100, 1100, 1300, 90, branch,5);
        Room1.addObstacle(400, 600, MainMenu.width, 700,0, brick);
        Room1.addFlow(600,700,1600,800,90, branch,35);
        Room1.addGate(MainMenu.width - 100, MainMenu.height,MainMenu.width + 100,MainMenu.height + 100, 270,4, cube);
        Room1.addGate(MainMenu.width, 100, MainMenu.width + 200, 200, 180, 0, cube);
        RoomList1.add(Room1);

        Room Room2 = new Room();
        Point start_point2 = new Point(100, MainMenu.height - 100);
        Room2.addBall(start_point2, 50);
        Room2.addObstacle(0, 0, MainMenu.width, MainMenu.height,0,alfa_chanel);
        Room2.addGate(100, MainMenu.height - 200, 300, MainMenu.height - 100, 90, 1, cube);

        RoomList1.add(Room0);
        RoomList1.add(Room1);
        RoomList1.add(Room2);
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
