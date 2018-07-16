package com.admin.bounceultra;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;

abstract class GameObject implements Cloneable{
    float x;
    float y;
    float start_x;
    float start_y;
    float x_left;
    float y_top;
    float x_right;
    float y_bottom;
    float x_centre;
    float y_centre;
    float degrees;
    int imageId;
    int bitmapId;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    int next_room_id;
    String name;

    void draw(Canvas canvas, Paint paint, Bitmap bitmap) {
        Matrix m = new Matrix();
        m.setTranslate(x_left, y_top);
        m.preRotate(degrees);
        canvas.drawBitmap(bitmap, m, null);
    }

    void moveToXY(float newX, float newY) {
        float deltaX = newX - x;
        float deltaY = newY - y;
        x_right += deltaX;
        x_left += deltaX;
        y_top += deltaY;
        y_bottom += deltaY;
        x += deltaX;
        y += deltaY;
    }

    void compress(float k) {
        float width = x_right - x_left;
        float hight = y_top - y_bottom;
        x_left = x - width / k / 2;
        x_right = x + width / k / 2;
        y_bottom = y - hight / k / 2;
        y_top = y + hight / k / 2;
    }

    GameObject() {
    }

    public GameObject clone() throws CloneNotSupportedException{

        GameObject newGameObject = (GameObject) super.clone();
        return newGameObject;
    }

}
