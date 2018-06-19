package com.admin.bounceultra;

import java.util.ArrayList;

class SimpleSegment extends Segment {

    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> ObjectList, int intersected_obst_ind, float min_d, boolean draft) {
        if (!ball.stick_to_segment(cur_seg)) {
            ArrayList<Segment> segments = ObjectList.get(intersected_obst_ind).segments;
            float axil = 1;
            ball.MindTheGap(segments.get(intersected_seg_ind), axil);
            axil *= ball.g;
            min_d = Vector.dPointSegment(segments.get(intersected_seg_ind).a, segments.get(intersected_seg_ind).b, ball.centre());
            Vector segment = new Vector(segments.get(intersected_seg_ind));
            if (min_d  == ball.centre().dist(segments.get(intersected_seg_ind).a) || min_d  == ball.centre().dist(segments.get(intersected_seg_ind).b)) {
                Vector new_velocity;
                if (min_d  == ball.centre().dist(segments.get(intersected_seg_ind).a)) {
                    new_velocity = new Vector(segments.get(intersected_seg_ind).a, ball.centre());
                } else {
                    new_velocity = new Vector(segments.get(intersected_seg_ind).b, ball.centre());
                }
                new_velocity.unit();
                new_velocity.multiplying(ball.velocity.length());
                ball.velocity = new_velocity;
            } else {
                if (!ball.velocity.if_null()) {
                    ball.velocity = Vector.reflect(ball.velocity, segment);
                }
            }
            ball.velocity.y = (float) (ball.velocity.y - ball.g + axil);
            ball.x_speed = ball.velocity.x * (1 - ball.decreas);
            ball.y_speed = ball.velocity.y * (1 - ball.decreas);
        } else {
            ball.roll(cur_seg);
            ball.x_speed = ball.velocity.x;
            ball.y_speed = ball.velocity.y;
            ball.x += ball.x_speed;
            ball.y += ball.y_speed;
        }
    }

    SimpleSegment(Point p1, Point p2) {
        super(p1, p2);
    }
}
