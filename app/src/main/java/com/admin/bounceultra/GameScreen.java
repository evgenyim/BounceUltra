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
import static android.content.Context.MODE_PRIVATE;

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

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        for(int i = 0; i < Room.ObjectList.size(); i++) {
            Room.ObjectList.get(i).draw(canvas, paint);
        }

    }
}
