package com.example.user.flippy_box;

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
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static com.example.user.flippy_box.MainActivity.box;
import static com.example.user.flippy_box.MainActivity.hole_x_l;
import static com.example.user.flippy_box.MainActivity.hole_x_r;
import static com.example.user.flippy_box.MainActivity.last_box;
import static com.example.user.flippy_box.MainActivity.image_side;
import static com.example.user.flippy_box.MainActivity.space_to_bottom;
import static com.example.user.flippy_box.MainActivity.width1;
import static java.lang.Math.round;
import static java.lang.Math.*;


/**
 * Created by user on 6/7/17.
 */

public class GameScreen extends View {


    public GameScreen(@NonNull Context context) {
        super(context);
    }

    public GameScreen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameScreen(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.square);
    Bitmap cube = Bitmap.createScaledBitmap(bitmap, image_side, image_side, false);

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        for (int i = 0; i < box.size(); i++) {
            Matrix m = new Matrix();
            double mx = box.get(i).x - (image_side / 2) * sqrt(2) * sin((float) toRadians(45 - box.get(i).rotation_angle));
            double my = box.get(i).y - (image_side / 2) * sqrt(2) * cos((float) toRadians(45 - box.get(i).rotation_angle));
            m.setTranslate((float) mx, (float) my);
            m.preRotate(box.get(i).rotation_angle);
            canvas.drawBitmap(cube, m, null);
        }
        canvas.drawLine(0, MainActivity.height1 - space_to_bottom, MainActivity.width1, MainActivity.height1 - space_to_bottom, paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(hole_x_l, MainActivity.height1 - space_to_bottom, hole_x_r, MainActivity.height1 - space_to_bottom - 10, paint);
        paint.setColor(Color.RED);

//        for (int i = 0; i < 4; i++) {
//            canvas.drawCircle((float) last_box.corners()[i].x, (float) last_box.corners()[i].y, 10, paint);
//        }
        canvas.drawCircle((float) last_box.intersection_point.x, (float) last_box.intersection_point.y, 8, paint);
        if (MainActivity.action_down) canvas.drawLine(last_box.x, last_box.y, MainActivity.xx, MainActivity.yy, paint);
        if (MainActivity.First_touch && MainActivity.action_up && last_box.moving) last_box.shot();
        if (MainActivity.First_touch && MainActivity.action_up && last_box.needs_spining) last_box.spin();
        paint.setColor(Color.BLUE);
        Paint fontPaint;
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(100);
        if (MainActivity.winer) {
            Log.d("1", "pobeda");
            canvas.drawText("pobeda", 400, 300, fontPaint);
        } else {
            canvas.drawText("ebat' ti loh", 200, 300, fontPaint);
        }


        postInvalidateDelayed(0);
    }
}

