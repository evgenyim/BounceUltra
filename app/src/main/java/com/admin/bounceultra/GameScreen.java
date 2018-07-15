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
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class GameScreen extends SurfaceView {
    static ArrayList<GameObject> cur_room_objects = MainActivity.RoomList.get(MainActivity.current_room).objectList;
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
    Bitmap bitmap_branch = BitmapFactory.decodeResource(getResources(), R.drawable.branch);

    Bitmap cube = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
    Bitmap rect = Bitmap.createScaledBitmap(bitmap, 200, 100, false);
    Bitmap wall = Bitmap.createScaledBitmap(bitmap, 10,(int) (MainMenu.height) * 3 / 4, false);
    Bitmap gate = Bitmap.createScaledBitmap(bitmap, 200, 100,false);
    Bitmap brick = Bitmap.createScaledBitmap(bitmap_brick, 300, 100,false);
    Bitmap branch = Bitmap.createScaledBitmap(bitmap_branch, 300, 100,false);
    ArrayList<Bitmap> bitmapList = new ArrayList<>();
    Paint paint = new Paint();
    boolean firstTime = true;
  
    protected void onDraw(Canvas canvas) {
        if (firstTime) {
            createBitmap();
            firstTime = false;
        }

        cur_room_objects = MainActivity.RoomList.get(MainActivity.current_room).objectList;
        cur_ball = MainActivity.RoomList.get(MainActivity.current_room).ball;

        paint.setColor(Color.RED);
        if (bitmapList.size() == 0) {
            createBitmap();
        }
        for(int i = 0; i < cur_room_objects.size(); i++) {
            Bitmap bMap = bitmapList.get(cur_room_objects.get(i).id);
            cur_room_objects.get(i).draw(canvas, paint, bMap);
            for (int j = 0; j < cur_room_objects.get(i).segments.size(); j++){
                cur_room_objects.get(i).segments.get(j).draw(canvas,paint);
            }
        }

        for (int i = 0; i < MainActivity.trajectory.size(); i++) {
            com.admin.bounceultra.Point p = MainActivity.trajectory.get(i);
            canvas.drawCircle(p.x, p.y, 10, paint);
        }

        drawInventory(cur_ball, canvas);
//        MainActivity.RoomList.get(MainActivity.current_room).draw(0, 0, 2, canvas, paint, bitmapList);
//        canvas.drawLine(0, MainActivity.height / 2, MainActivity.width / 2, MainActivity.height / 2, paint);
//        canvas.drawLine(MainActivity.width / 2, 0, MainActivity.width / 2, MainActivity.height / 2, paint);

        cur_ball.draw(canvas, paint, cube);
        cur_ball.move(MainActivity.RoomList.get(MainActivity.current_room).objectList, false);

        postInvalidateDelayed(0);
    }
    void createBitmap() {
        bitmapList.add(cube);
        bitmapList.add(rect);
        bitmapList.add(wall);
        bitmapList.add(gate);
        bitmapList.add(brick);
        bitmapList.add(branch);
    }

    void drawInventory(Ball ball, Canvas canvas) {
        for (int i = 0; i < ball.inventory.size(); i++) {
            Matrix m = new Matrix();
            m.setTranslate(i * 200, MainMenu.height - 100);
            Log.d("ki", String.valueOf(ball.inventory.get(i).id));
            Bitmap bitmap = bitmapList.get(ball.inventory.get(i).id);
            canvas.drawBitmap(bitmap, m, null);
        }
    }
}
