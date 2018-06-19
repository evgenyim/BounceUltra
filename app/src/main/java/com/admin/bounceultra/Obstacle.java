package com.admin.bounceultra;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

class Obstacle extends GameObject {

     float x_left;
     float y_top;
     float x_right;
     float y_bottom;
     Matrix m = new Matrix();
     float degrees;
     ArrayList<Segment> segments = new ArrayList<Segment>();



     Obstacle(float x_left,float y_top,float x_right,float y_bottom, float degrees, int id) {
         this.x_left = x_left;
         this.x_right = x_right;
         this.y_bottom = y_bottom;
         this.y_top = y_top;
         this.degrees = degrees;
         this.id = id;
     }

     void move(float x, float y){

     }

     boolean iftouchinside (float x, float y){
         if ((x <= x_right) && (x >= x_left) && (y >= y_bottom) && (y <= y_top)){
             return true;
         }
         return false;
     }

    void draw(Canvas canvas, Paint paint, Bitmap bitmap) {
         m.setTranslate(x_left, y_bottom);
         m.preRotate(degrees);
         canvas.drawBitmap(bitmap, m, null);
    }

 }
