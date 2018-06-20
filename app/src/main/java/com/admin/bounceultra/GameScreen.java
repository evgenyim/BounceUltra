package com.admin.bounceultra;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.RotateDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class GameScreen extends View {
    static ArrayList<GameObject> cur_room = MainActivity.RoomList.get(MainActivity.current_room).ObjectList;
    static Ball cur_ball = MainActivity.RoomList.get(MainActivity.current_room).ball;
    public GameScreen(@NonNull Context context) {
        super(context);
    }
    public GameScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public GameScreen(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cube);
    Bitmap bitmap_brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
    Bitmap cube = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
    Bitmap rect = Bitmap.createScaledBitmap(bitmap, 200, 100, false);
    Bitmap wall = Bitmap.createScaledBitmap(bitmap, 10,(int) (MainActivity.height) * 3 / 4, false);
    Bitmap gate = Bitmap.createScaledBitmap(bitmap, 200, 100,false);
    Bitmap brick = Bitmap.createScaledBitmap(bitmap_brick, 300, 100,false);
    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    Paint paint = new Paint();
  
    protected void onDraw(Canvas canvas) {

        cur_room = MainActivity.RoomList.get(MainActivity.current_room).ObjectList;
        cur_ball = MainActivity.RoomList.get(MainActivity.current_room).ball;

        paint.setColor(Color.RED);
        createBitmap();
        for(int i = 0; i < cur_room.size(); i++) {
            Bitmap bMap = bitmapList.get(cur_room.get(i).id);
            cur_room.get(i).draw(canvas, paint, bMap);
            for (int j = 0; j < cur_room.get(i).segments.size(); j++){
                cur_room.get(i).segments.get(j).draw(canvas,paint);
            }
        }
        for (int i = 0; i < MainActivity.trajectory.size(); i++) {
            com.admin.bounceultra.Point p = MainActivity.trajectory.get(i);
            canvas.drawCircle(p.x, p.y, 10, paint);
        }
       // MainActivity.RoomList.get(MainActivity.current_room).draw(100, 100, 6, canvas, paint, bitmapList);
      /*  for(int i = 0; i < MainActivity.RoomList.get(MainActivity.current_room).segments.size(); i++) {
            if (i == Ball.index) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLUE);
            }
            MainActivity.RoomList.get(MainActivity.current_room).segments.get(i).draw(canvas, paint);
        }*/
        paint.setColor(Color.RED);
        cur_ball.draw(canvas, paint);
        cur_ball.move(MainActivity.RoomList.get(MainActivity.current_room).ObjectList, false);

        postInvalidateDelayed(0);
    }
    void createBitmap() {
        bitmapList.add(cube);
        bitmapList.add(rect);
        bitmapList.add(wall);
        bitmapList.add(gate);
        bitmapList.add(brick);
    }
}
