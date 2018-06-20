package com.admin.bounceultra;
import java.util.ArrayList;


class GateSegment extends Segment {

    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> ObjectList, int intersected_obst_ind, float min_d, boolean draft) {
                Room room;
                if (!draft) {
                    room = MainActivity.RoomList.get(ObjectList.get(intersected_obst_ind).next_room_id);
                    room.ball.y_speed = ball.y_speed;
                    room.ball.x_speed = ball.x_speed;
                    room.ball.velocity = ball.velocity;
                    for (int j = 0; j < room.ObjectList.size(); j++) {
                        if (room.ObjectList.get(j).id == 3 && room.ObjectList.get(j).next_room_id == MainActivity.current_room) {
                            room.ball.x = room.ObjectList.get(j).start_x;
                            room.ball.y = room.ObjectList.get(j).start_y + Math.signum(ball.y_speed) * 50;
                        }
                    }
                    MainActivity.current_room = ObjectList.get(intersected_obst_ind).next_room_id;
                }
    }

    GateSegment(Point a, Point b) {
        super(a, b);
    }
}
