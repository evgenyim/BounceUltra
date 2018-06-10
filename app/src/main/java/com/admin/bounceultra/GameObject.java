package com.admin.bounceultra;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class GameObject {
    float x;
    float y;
    int id;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    Segment main_segment;
    int next_room_id;

    void draw(Canvas canvas, Paint paint, Bitmap bitmap){
    }
}
