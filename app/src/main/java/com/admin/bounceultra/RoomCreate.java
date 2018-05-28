package com.admin.bounceultra;

import java.util.ArrayList;

public class RoomCreate {
    static ArrayList<Room> RoomList = new ArrayList<>();
    public static void create() {
        Point start_point = new Point(400, 400);
        RoomList.add(new Room());
        RoomList.get(0).addBall(start_point,50);
        RoomList.get(0).addObstacle(500,500,600,600);
    }

}
