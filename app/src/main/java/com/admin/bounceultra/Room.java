package com.admin.bounceultra;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.*;

class Room {

    ArrayList<GameObject> ObjectList = new ArrayList<GameObject>();
    Ball ball;
    static Obstacle obstacle;

    @SuppressLint("NewApi")
    void draw(float x, float y, float k, Canvas canvas, Paint paint, ArrayList<Bitmap> bitmap_list) {
        for (int i = 0; i < ObjectList.size(); i++) {
            GameObject cur_object = ObjectList.get(i);
            cur_object.x /= k;
            cur_object.y /= k;
            cur_object.x += x;
            cur_object.y += y;
            Bitmap cur_bitmap = Bitmap.createBitmap(bitmap_list.get(cur_object.id));
            Log.d("height", String.valueOf(cur_bitmap.getHeight()));
            Log.d("width", String.valueOf(cur_bitmap.getWidth()));
            cur_bitmap.setHeight((int) (cur_bitmap.getHeight() / k));
            cur_bitmap.setWidth((int) (cur_bitmap.getWidth() / k));
            cur_object.draw(canvas, paint, cur_bitmap);
        }
    }

    void addObstacle(float x_left, float y_top, float x_right, float y_bottom, float degrees, int id) {
        obstacle = new Obstacle(x_left, y_top, x_right, y_bottom, degrees, id);
        obstacle.x_centre = (x_left + x_right) / 2;
        obstacle.y_centre = (y_bottom + y_top) / 2;
        ObjectList.add(obstacle);
        int obstId = ObjectList.size() - 1;
        addSegments(x_left, y_top, x_right, y_bottom, degrees, obstId);
    }

    void addSegments(float x_left, float y_top, float x_right, float y_bottom, double degree, int obstId) {
        degree = toRadians(degree);
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        SimpleSegment segm_top = new SimpleSegment(point_left_top, point_right_top);
        SimpleSegment segm_right = new SimpleSegment(point_right_top, point_right_bottom);
        SimpleSegment segm_bottom = new SimpleSegment(point_left_bottom, point_right_bottom);
        SimpleSegment segm_left = new SimpleSegment(point_left_top, point_left_bottom);
        ObjectList.get(obstId).segments.add(segm_top);
        ObjectList.get(obstId).segments.add(segm_right);
        ObjectList.get(obstId).segments.add(segm_bottom);
        ObjectList.get(obstId).segments.add(segm_left);
    }

    void addBall(Point p, float r) {
        ball = new Ball(p, r);
    }

    void addGate(float x_left, float y_top, float x_right, float y_bottom, float degree, int next_room_id, int id) {
        Gate gate = new Gate(x_left, y_top, x_right, y_bottom, degree, next_room_id, id);
        degree = (float) toRadians(degree);
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        SimpleSegment segm_right =  new SimpleSegment(point_right_top, point_right_bottom);
        SimpleSegment segm_bottom =  new SimpleSegment(point_left_bottom, point_right_bottom);
        SimpleSegment segm_left = new SimpleSegment(point_left_top, point_left_bottom);
        GateSegment segm_top = new GateSegment(point_left_top, point_right_top);
        gate.segments.add(segm_left);
        gate.segments.add(segm_right);
        gate.segments.add(segm_bottom);
       //gate.main_segment = segm_top;
        gate.segments.add(segm_top);
        ObjectList.add(gate);
    }

   // void addWall()
}