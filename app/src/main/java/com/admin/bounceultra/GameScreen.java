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

import java.net.ResponseCache;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class GameScreen extends SurfaceView {
    public GameScreen(@NonNull Context context) {
        super(context);
    }
    public GameScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public GameScreen(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ArrayList<GameObject> cur_room_objects;
    Ball cur_ball;

    Bitmap cube = BitmapFactory.decodeResource(getResources(), R.drawable.cube);
    Bitmap brick = BitmapFactory.decodeResource(getResources(), R.drawable.brick);
    Bitmap branch = BitmapFactory.decodeResource(getResources(), R.drawable.branch);
    Bitmap alfa_chanel = BitmapFactory.decodeResource(getResources(), R.drawable.alfa_chanel);
    Bitmap upLattice = BitmapFactory.decodeResource(getResources(), R.drawable.up_lattice);
    Bitmap downLattice = BitmapFactory.decodeResource(getResources(), R.drawable.down_lattice);
    Bitmap key = BitmapFactory.decodeResource(getResources(), R.drawable.key);
    Bitmap lock = BitmapFactory.decodeResource(getResources(), R.drawable.lock);


    Paint paint = new Paint();
    ArrayList<Bitmap> imageList = new ArrayList<>();

    protected void onDraw(Canvas canvas) {
        if (imageList.size() == 0) {
            createImage();
        }
        cur_room_objects = MainActivity.RoomList.get(MainActivity.current_room).objectList;
        ArrayList<Bitmap> bitmapList = MainActivity.RoomList.get(MainActivity.current_room).bitmapList;
        cur_ball = MainActivity.RoomList.get(MainActivity.current_room).ball;
        if (bitmapList.size() == 0) {
            for(int i = 0; i < cur_room_objects.size(); i++) {
                Bitmap source = imageList.get(cur_room_objects.get(i).imageId);
                Bitmap bMap = Bitmap.createScaledBitmap(source,
                        (int) (cur_room_objects.get(i).x_right - cur_room_objects.get(i).x_left),
                        (int) (cur_room_objects.get(i).y_bottom - cur_room_objects.get(i).y_top),
                        false);
                bitmapList.add(bMap);
                cur_room_objects.get(i).bitmapId = bitmapList.size() - 1;
            }
        }

        paint.setColor(Color.RED);
        for(int i = 0; i < cur_room_objects.size(); i++) {
            Bitmap bMap = bitmapList.get(cur_room_objects.get(i).bitmapId);
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

        cur_ball.draw(canvas, paint, cube);
        cur_ball.move(MainActivity.RoomList.get(MainActivity.current_room).objectList, false);

        postInvalidateDelayed(0);
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
    }

    void drawInventory(Ball ball, Canvas canvas) {
        for (int i = 0; i < ball.inventory.size(); i++) {
            Matrix m = new Matrix();
            m.setTranslate(i * 200, MainMenu.height - 100);
            Bitmap bitmap = MainActivity.RoomList.get(MainActivity.current_room).bitmapList.get(ball.inventory.get(i).bitmapId);
            canvas.drawBitmap(bitmap, m, null);
        }
    }
}
