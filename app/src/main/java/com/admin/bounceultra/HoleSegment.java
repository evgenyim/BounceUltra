package com.admin.bounceultra;

import java.util.ArrayList;

public class HoleSegment extends Segment {
    void comunicate (Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> objectList, int intersected_obj_ind, float min_d, boolean draft) {
        if(!draft) {
            ball.x_speed = 0;
            ball.y_speed = 0;
            ball.x = objectList.get(intersected_obj_ind).x;
            ball.y = objectList.get(intersected_obj_ind).y;
        }
    }
    HoleSegment(Point p1, Point p2) { super(p1,p2); }
}
