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
    Bitmap cube = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
    Bitmap rect = Bitmap.createScaledBitmap(bitmap, 500, 300, false);
    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    Paint paint = new Paint();
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.BLUE);
        createBitmap();
        for(int i = 0; i < cur_room.size(); i++) {
            Bitmap bMap = bitmapList.get(cur_room.get(i).id);
            cur_room.get(i).draw(canvas, paint,bMap);
        }
        paint.setColor(Color.RED);
        cur_ball.draw(canvas, paint);
        Ball.move(cur_ball);


        postInvalidateDelayed(0);
    }
    void createBitmap() {
        bitmapList.add(cube);
        bitmapList.add(rect);
    }
}
