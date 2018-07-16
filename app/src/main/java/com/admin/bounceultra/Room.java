package com.admin.bounceultra;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.*;

class Room {

    ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    Ball ball;
    static Obstacle obstacle;

    void draw(float x, float y, float k, Canvas canvas, Paint paint, ArrayList<Bitmap> bitmap_list) {
        for (int i = 0; i < objectList.size(); i++) {
            GameObject cur_object = null;
            try {
                cur_object = objectList.get(i).clone();
            } catch (CloneNotSupportedException ex){
                System.out.println("Clonable not implemented");
            }
            float newX = cur_object.x / k + x;
            float newY = cur_object.y / k + y;
            cur_object.moveToXY(newX, newY);
            cur_object.compress(k);
            Bitmap cur_bitmap = Bitmap.createBitmap(bitmap_list.get(cur_object.imageId));
            cur_bitmap = Bitmap.createScaledBitmap(cur_bitmap, (int) (cur_bitmap.getWidth() / k), (int) (cur_bitmap.getHeight() / k), false);
            cur_object.draw(canvas, paint, cur_bitmap);
        }
    }

    void addItem(float x_left, float y_top, float x_right, float y_bottom, float degree, String name, int id) {
        Item item = new Item(x_left, y_top, x_right, y_bottom, degree, name, id);
        degree = (float) toRadians(degree);
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        ItemSegment segm_right =  new ItemSegment(point_right_top, point_right_bottom);
        ItemSegment segm_bottom =  new ItemSegment(point_left_bottom, point_right_bottom);
        ItemSegment segm_left = new ItemSegment(point_left_top, point_left_bottom);
        ItemSegment segm_top = new ItemSegment(point_left_top, point_right_top);
        item.segments.add(segm_left);
        item.segments.add(segm_right);
        item.segments.add(segm_bottom);
        item.segments.add(segm_top);
        objectList.add(item);
    }

    void addLock(float x_left, float y_top, float x_right, float y_bottom, float degree, int id) {
        Lock lock = new Lock(x_left, y_top, x_right, y_bottom, degree, id);
        degree = (float) toRadians(degree);
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        LockSegment segm_right =  new LockSegment(point_right_top, point_right_bottom);
        LockSegment segm_bottom =  new LockSegment(point_left_bottom, point_right_bottom);
        LockSegment segm_left = new LockSegment(point_left_top, point_left_bottom);
        LockSegment segm_top = new LockSegment(point_left_top, point_right_top);
        lock.segments.add(segm_left);
        lock.segments.add(segm_right);
        lock.segments.add(segm_bottom);
        lock.segments.add(segm_top);
        objectList.add(lock);
    }

    void addHole(float x_left, float y_top, float x_right, float y_bottom, float degree, int id, Point startPoint) {
        Hole hole = new Hole(x_left, y_top, x_right, y_bottom, degree, id, startPoint);
        degree = (float) toRadians(degree);
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        HoleSegment segm_right =  new HoleSegment(point_right_top, point_right_bottom);
        HoleSegment segm_bottom =  new HoleSegment(point_left_bottom, point_right_bottom);
        HoleSegment segm_left = new HoleSegment(point_left_top, point_left_bottom);
        HoleSegment segm_top = new HoleSegment(point_left_top, point_right_top);
        hole.segments.add(segm_left);
        hole.segments.add(segm_right);
        hole.segments.add(segm_bottom);
        hole.segments.add(segm_top);
        objectList.add(hole);
    }

    void addFlow(float x_left, float y_top, float x_right, float y_bottom, float degree, int id, float a) {
        Flow flow = new Flow(x_left, y_top, x_right, y_bottom, degree, id, a);
        degree = (float) toRadians(degree);
        Point point_right_top = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_top - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_top - y_top) * (float) cos(degree));
        Point point_left_top = new Point(x_left, y_top);
        Point point_left_bottom = new Point(x_left + (x_left - x_left)  * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_left - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        Point point_right_bottom = new Point(x_left + (x_right - x_left) * (float) cos(degree) - (y_bottom - y_top) * (float) sin(degree), y_top + (x_right - x_left) * (float) sin(degree) + (y_bottom - y_top) * (float) cos(degree));
        FlowSegment segm_right =  new FlowSegment(point_right_top, point_right_bottom);
        FlowSegment segm_bottom =  new FlowSegment(point_left_bottom, point_right_bottom);
        FlowSegment segm_left = new FlowSegment(point_left_top, point_left_bottom);
        FlowSegment segm_top = new FlowSegment(point_left_top, point_right_top);
        /*flow.segments.add(segm_left);
        flow.segments.add(segm_right);
        flow.segments.add(segm_bottom);
        flow.segments.add(segm_top);*/
        objectList.add(flow);
    }

    void addObstacle(float x_left, float y_top, float x_right, float y_bottom, float degrees, int id) {
        obstacle = new Obstacle(x_left, y_top, x_right, y_bottom, degrees, id);
        obstacle.x_centre = (x_left + x_right) / 2;
        obstacle.y_centre = (y_bottom + y_top) / 2;
        objectList.add(obstacle);
        int obstId = objectList.size() - 1;
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
        objectList.get(obstId).segments.add(segm_top);
        objectList.get(obstId).segments.add(segm_right);
        objectList.get(obstId).segments.add(segm_bottom);
        objectList.get(obstId).segments.add(segm_left);
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
        gate.segments.add(segm_top);
        objectList.add(gate);
    }
}