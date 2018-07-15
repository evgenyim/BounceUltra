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
import android.util.Log;
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
    Bitmap bitmap_branch = BitmapFactory.decodeResource(getResources(), R.drawable.branch);

    Bitmap cube = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
    Bitmap rect = Bitmap.createScaledBitmap(bitmap, 200, 100, false);
    Bitmap wall = Bitmap.createScaledBitmap(bitmap, 10,(int) (MainMenu.height) * 2 / 4, false);
    Bitmap gate = Bitmap.createScaledBitmap(bitmap, 200, 100,false);
    Bitmap brick = Bitmap.createScaledBitmap(bitmap_brick, 300, 100,false);
    Bitmap branch = Bitmap.createScaledBitmap(bitmap_branch, 300, 100,false);
    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    Paint paint = new Paint();

    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        if(bitmapList.size() == 0) {
            createBitmap();
        }
        int roomAmount =(int) Math.sqrt(MapActivity.roomAmount);
        for(int i = MapActivity.startRoom; i <= MapActivity.startRoom + MapActivity.roomAmount; i++) {
            int num = i - MapActivity.startRoom;
            LevelsActivity.RoomList.get(i).draw(MainMenu.width / roomAmount * (num % roomAmount), MainMenu.height / roomAmount * (num / roomAmount), roomAmount, canvas, paint, bitmapList);
        }
        for(int i = 0; i < roomAmount; i++) {
            canvas.drawLine(MainMenu.width * (i + 1) / roomAmount, 0, MainMenu.width * (i + 1) / roomAmount, MainMenu.height, paint);
            canvas.drawLine(0, MainMenu.height * (i + 1) / roomAmount, MainMenu.width, MainMenu.height * (i + 1) / roomAmount, paint);

        }


    }

    void createBitmap() {
        bitmapList.add(cube);
        bitmapList.add(rect);
        bitmapList.add(wall);
        bitmapList.add(gate);
        bitmapList.add(brick);
        bitmapList.add(branch);
    }

}
