package com.admin.bounceultra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class LevelsScreen extends View {
    public LevelsScreen (@NonNull Context context) {
        super(context);
    }
    public LevelsScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public LevelsScreen(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cube);
    Bitmap bitmap_brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
    Bitmap cube = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
    Bitmap rect = Bitmap.createScaledBitmap(bitmap, 200, 100, false);
   // Bitmap wall = Bitmap.createScaledBitmap(bitmap, 10,(int) (MainActivity.height) * 3 / 4, false);
    Bitmap gate = Bitmap.createScaledBitmap(bitmap, 200, 100,false);
    Bitmap brick = Bitmap.createScaledBitmap(bitmap_brick, 300, 100,false);
    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    Paint paint = new Paint();

    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        createBitmap();

     //   MainActivity.RoomList.get(MainActivity.current_room).draw(100, 100, 6, canvas, paint, bitmapList);

    }

    void createBitmap() {
        bitmapList.add(cube);
        bitmapList.add(rect);
       // bitmapList.add(wall);
        bitmapList.add(gate);
        bitmapList.add(brick);
    }

}
