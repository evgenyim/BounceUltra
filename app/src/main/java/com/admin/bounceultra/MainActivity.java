package com.admin.bounceultra;

import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static int width;
    static int height;
    static ArrayList<Room> RoomList;
    static int current_room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameScreen gameScreen = (GameScreen) findViewById(R.id.game_screen);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        final Button btn = (Button) findViewById(R.id.button);
        RoomList = new ArrayList<>();
        RoomList = RoomCreate.RoomList;

        final View.OnTouchListener list = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                Room.ball.shot(x, y);
                return true;
            }
        };
        gameScreen.setOnTouchListener(list);
    }
}
