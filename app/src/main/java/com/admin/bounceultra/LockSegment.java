package com.admin.bounceultra;

import java.util.ArrayList;

public class LockSegment extends  Segment{
    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> objectList, int intersected_obj_ind, float min_d, boolean draft) {
        SimpleSegment simple = new SimpleSegment(this.a, this.b);
        simple.comunicate(ball, intersected_seg_ind, cur_seg, objectList, intersected_obj_ind, min_d, draft);
        if (!draft) {
            for (int i = 0; i < ball.inventory.size(); i++) {
                if (ball.inventory.get(i).name == "key") {
                    objectList.remove(intersected_obj_ind);
                    ball.inventory.remove(i);
                }
            }
        }
    }
    LockSegment(Point p1, Point p2) {
        super(p1, p2);
    }
}
