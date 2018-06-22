package com.admin.bounceultra;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

abstract class GameObject implements Cloneable{
    float x;
    float y;
    float start_x;
    float start_y;
    int id;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    Segment main_segment;
    int next_room_id;

    abstract void draw(Canvas canvas, Paint paint, Bitmap bitmap);

    abstract void moveToXY(float newX, float newY);

    GameObject() {
    }

    public GameObject clone() throws CloneNotSupportedException{

        GameObject newGameObject = (GameObject) super.clone();
        return newGameObject;
    }

}
