package com.admin.bounceultra;

import android.util.Log;

import java.util.ArrayList;

class SimpleSegment extends Segment {

    void comunicate(Ball ball, int intersected_seg_ind, Segment cur_seg, ArrayList<GameObject> ObjectList, int intersected_obj_ind, float min_d, boolean draft) {
        ArrayList<Segment> segments = ObjectList.get(intersected_obj_ind).segments;
        if ((!ball.stick_to_segment(cur_seg)) ||  (min_d  == ball.centre().dist(segments.get(intersected_seg_ind).a) || min_d  == ball.centre().dist(segments.get(intersected_seg_ind).b))) {
            Log.d("antihui", "antiihui");
            float axil = 1;
            ball.MindTheGap(segments.get(intersected_seg_ind), axil, min_d);
            axil *= ball.g;
            min_d = Vector.dPointSegment(segments.get(intersected_seg_ind).a, segments.get(intersected_seg_ind).b, ball.centre());
            Vector segment = new Vector(segments.get(intersected_seg_ind));
            if (min_d  == ball.centre().dist(segments.get(intersected_seg_ind).a) || min_d  == ball.centre().dist(segments.get(intersected_seg_ind).b)) {
                Log.d("3","3");
                Vector new_velocity;
                Point p = new Point(0,0);
                if (min_d  == ball.centre().dist(segments.get(intersected_seg_ind).a)) {
                    p = segments.get(intersected_seg_ind).a;
                } else {
                    p = segments.get(intersected_seg_ind).b;
                }
                float a = p.x - ball.centre().x;
                float b = p.y - ball.centre().y;
                float c = 12;
                //float c = - ball.centre().x * p.x - ball.centre().y * p.y + ball.centre().x * ball.centre().x + ball.centre().y * ball.centre().y - ball.r * ball.r;
                Segment seg = new Segment(a, b, c);
                Vector vec = new Vector(seg);
                ball.velocity = Vector.reflect(ball.velocity, vec);

            } else {
                Log.d("1","1");
                if (!ball.velocity.if_null()) {
                    Log.d("2","2");
                    ball.velocity = Vector.reflect(ball.velocity, segment);
                }
            }
            ball.velocity.y = (float) (ball.velocity.y - ball.g + axil);
            ball.x_speed = ball.velocity.x * (1 - ball.decreas);
            ball.y_speed = ball.velocity.y * (1 - ball.decreas);
        } else {
            Log.d("hui", "hui:");
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