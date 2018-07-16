package com.admin.bounceultra;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    static ArrayList<Room> RoomList;
    static int current_room = 0;
    static ArrayList <com.admin.bounceultra.Point> trajectory = new ArrayList<>();
    int dots_in_trajectory = 20;
    int dist_between_dots =  1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RoomList = RoomCreate.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameScreen gameScreen = findViewById(R.id.game_screen);

        final View.OnTouchListener list = new View.OnTouchListener() {
            float xPress;
            float yPress;
            float xUnpress;
            float yUnpress;
            float xMove;
            float yMove;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean unpress = false;
                if(!unpress) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN :
                            xPress = event.getX();
                            yPress = event.getY();
                            unpress = false;
                            break;
                        case MotionEvent.ACTION_UP :
                            xUnpress = event.getX();
                            yUnpress = event.getY();
                            unpress = true;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            trajectory.clear();
                            Ball draft_ball = new Ball(RoomList.get(current_room).ball.x, RoomList.get(current_room).ball.y, RoomList.get(current_room).ball.r);
                            xMove = event.getX();
                            yMove = event.getY();
                            draft_ball.shot(xPress - xMove, yPress - yMove);
                            for (int i = 0; i < (dots_in_trajectory + 1) * dist_between_dots; i++) {
                                draft_ball.move(RoomList.get(current_room).objectList, true);
                                if (i % dist_between_dots == 0) {
                                    com.admin.bounceultra.Point p = new com.admin.bounceultra.Point(draft_ball.x, draft_ball.y);
                                    trajectory.add(p);
                                }
                            }
                            break;
                    }
            }
            if(unpress /*&& RoomList.get(current_room).ball.x_speed == 0 && RoomList.get(current_room).ball.y_speed == 0 */) {
                RoomList.get(current_room).ball.shot(xPress - xUnpress,yPress - yUnpress);
                trajectory.clear();
            }
            return true;
            }
        };
        gameScreen.setOnTouchListener(list);
    }
}
