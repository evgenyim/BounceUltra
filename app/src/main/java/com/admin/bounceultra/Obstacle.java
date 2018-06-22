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
import android.util.Log;

import java.util.ArrayList;

class Obstacle extends GameObject implements Cloneable{

     float x_left;
     float y_top;
     float x_right;
     float y_bottom;
     float x_centre;
     float y_centre;
     float degrees;


     Obstacle(float x_left,float y_top,float x_right,float y_bottom, float degrees, int id) {
         this.x_left = x_left;
         this.x_right = x_right;
         this.y_bottom = y_bottom;
         this.y_top = y_top;
         this.degrees = degrees;
         this.id = id;
         this.x = (x_left + x_right) / 2;
         this.y = (y_bottom + y_top) / 2;
     }

     void moveToXY(float newX, float newY){
         float deltaX = newX - x;
         float deltaY = newY - y;
         x_right += deltaX;
         x_left += deltaX;
         y_top += deltaY;
         y_bottom += deltaY;
         x += deltaX;
         y += deltaY;

     }

     boolean iftouchinside (float x, float y){
         if ((x <= x_right) && (x >= x_left) && (y >= y_bottom) && (y <= y_top)){
             return true;
         }
         return false;
     }

    void draw(Canvas canvas, Paint paint, Bitmap bitmap) {
         Matrix m = new Matrix();
         m.setTranslate(x_left, y_bottom);
         m.preRotate(degrees);
         canvas.drawBitmap(bitmap, m, null);
    }

    public Obstacle clone() throws CloneNotSupportedException{

        Obstacle newObstacle = (Obstacle) super.clone();
        return newObstacle;
    }
 }
