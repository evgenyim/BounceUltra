package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

public class ItemSegment extends Segment {
    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> objectList, int intersected_obj_ind, float min_d, boolean draft) {
        if (!draft) {
            ball.inventory.add(objectList.get(intersected_obj_ind));
            objectList.remove(intersected_obj_ind);
        }
        ball.y_speed += ball.g;
        ball.x += ball.x_speed;
        ball.y += ball.y_speed;
    }
    ItemSegment(Point p1, Point p2) {
        super(p1, p2);
    }
}
