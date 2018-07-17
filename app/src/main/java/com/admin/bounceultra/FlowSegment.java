package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

class FlowSegment extends Segment {
    void comunicate (Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> objectList, int intersected_obj_ind, float min_d, boolean draft) {
        if(!draft) {
            ball.y_speed += ball.g;
            ball.x += ball.x_speed;
            ball.y += ball.y_speed;
        }
    }

    FlowSegment(Point p1, Point p2) { super(p1,p2); }
}
