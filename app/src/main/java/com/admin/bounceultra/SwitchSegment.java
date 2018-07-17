package com.admin.bounceultra;

import java.util.ArrayList;

public class SwitchSegment extends Segment {
    Switch aSwitch;
    void comunicate (Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> objectList, int intersected_obj_ind, float min_d, boolean draft) {
        if(!draft) {
            aSwitch.act();
        }
        SimpleSegment simpleSegment = new SimpleSegment(this.a, this.b);
        simpleSegment.comunicate(ball, intersected_seg_ind, cur_seg, objectList, intersected_obj_ind, min_d, draft);
    }


    SwitchSegment(Point p1, Point p2, Switch aSwitch) {
        this.a = p1;
        this.b = p2;
        this.aSwitch = aSwitch;
    }
}
