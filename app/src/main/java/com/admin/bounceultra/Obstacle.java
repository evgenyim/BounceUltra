package com.admin.bounceultra;

import android.graphics.Canvas;
import android.graphics.Paint;

class Obstacle extends GameObject {

     float x_left;
     float y_top;
     float x_right;
     float y_bottom;

     Obstacle(float x_left,float y_top,float x_right,float y_bottom) {
         this.x_left = x_left;
         this.x_right = x_right;
         this.y_bottom = y_bottom;
         this.y_top = y_top;
     }

//     void addSegments(){
//         Point point_left_top = new Point(x_left, y_top);
//         Point point_right_top = new Point(x_right, y_top);
//         Point point_left_bottom = new Point(x_left, y_bottom);
//         Point point_right_bottom = new Point(x_right, y_bottom);
//         Segment segm_top = new Segment(point_left_top, point_right_top);
//         Segment segm_right = new Segment(point_right_top, point_right_bottom);
//         Segment segm_bottom = new Segment(point_left_bottom, point_right_bottom);
//         Segment segm_left = new Segment(point_left_top, point_left_bottom);
//         Room.segments.add(segm_top);
//         Room.segments.add(segm_right);
//         Room.segments.add(segm_bottom);
//         Room.segments.add(segm_left);
//     }
     void move(float x, float y){

     }

     boolean iftouchinside (float x, float y){
         if ((x <= x_right) && (x >= x_left) && (y >= y_bottom) && (y <= y_top)){
             return true;
         }
         return false;
     }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(x_left, y_top, x_right, y_bottom, paint);
    }

 }
