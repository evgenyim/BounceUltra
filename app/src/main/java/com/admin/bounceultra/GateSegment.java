package com.admin.bounceultra;
import java.util.ArrayList;


class GateSegment extends Segment {

    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> objectList, int intersected_obj_ind, float min_d, boolean draft) {
        Room room;
        if (!draft) {
            room = MainActivity.RoomList.get(objectList.get(intersected_obj_ind).next_room_id);
            room.ball.y_speed = ball.y_speed;
            room.ball.x_speed = ball.x_speed;
            room.ball.velocity = ball.velocity;
            room.ball.inventory = ball.inventory;
            for (int j = 0; j < room.objectList.size(); j++) {
                if (room.objectList.get(j).imageId == 1 && room.objectList.get(j).next_room_id == MainActivity.current_room) {
                    room.ball.x = room.objectList.get(j).start_x;
                    room.ball.y = room.objectList.get(j).start_y;
                    }
            }
            MainActivity.current_room = objectList.get(intersected_obj_ind).next_room_id;
        }
    }

    GateSegment(Point a, Point b) {
        super(a, b);
    }
}
