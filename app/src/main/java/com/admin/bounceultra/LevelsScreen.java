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


    Bitmap cube = BitmapFactory.decodeResource(getResources(), R.drawable.cube);
    Bitmap brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
    Bitmap branch = BitmapFactory.decodeResource(getResources(), R.drawable.branch);
    Bitmap alfa_chanel = BitmapFactory.decodeResource(getResources(), R.drawable.alfa_chanel);
    Bitmap upLattice = BitmapFactory.decodeResource(getResources(), R.drawable.up_lattice);
    Bitmap downLattice = BitmapFactory.decodeResource(getResources(), R.drawable.down_lattice);
    Bitmap key = BitmapFactory.decodeResource(getResources(), R.drawable.key);
    Bitmap lock = BitmapFactory.decodeResource(getResources(), R.drawable.lock);
    Bitmap switch_right = BitmapFactory.decodeResource(getResources(), R.drawable.switch_right);
    Bitmap switch_left = BitmapFactory.decodeResource(getResources(), R.drawable.switch_left);



    ArrayList<Bitmap> imageList = new ArrayList<>();

    Paint paint = new Paint();

    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        if(imageList.size() == 0) {
            createImage();
        }

        int roomAmount =(int) Math.sqrt(MapActivity.roomAmount);
        for(int i = MapActivity.startRoom; i <= MapActivity.startRoom + MapActivity.roomAmount; i++) {
            int num = i - MapActivity.startRoom;
            if (LevelsActivity.RoomList.get(i).mapBitmapList.size() == 0){
                LevelsActivity.RoomList.get(i).createMapBitmapList(roomAmount, imageList);
            }
            LevelsActivity.RoomList.get(i).draw(MainMenu.width / roomAmount * (num % roomAmount), MainMenu.height / roomAmount * (num / roomAmount), roomAmount, canvas, paint, imageList);
        }
        for(int i = 0; i < roomAmount; i++) {
            canvas.drawLine(MainMenu.width * (i + 1) / roomAmount, 0, MainMenu.width * (i + 1) / roomAmount, MainMenu.height, paint);
            canvas.drawLine(0, MainMenu.height * (i + 1) / roomAmount, MainMenu.width, MainMenu.height * (i + 1) / roomAmount, paint);

        }
    }

    void createImage() {
        imageList.add(alfa_chanel);
        imageList.add(cube);
        imageList.add(brick);
        imageList.add(branch);
        imageList.add(upLattice);
        imageList.add(downLattice);
        imageList.add(key);
        imageList.add(lock);
        imageList.add(switch_left);
        imageList.add(switch_right);
    }
}
