package com.admin.bounceultra;

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
    static int width;
    static int height;
    static ArrayList<Room> RoomList;
    static int current_room = 0;
    static ArrayList <com.admin.bounceultra.Point> trajectory = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        RoomList = RoomCreate.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameScreen gameScreen = (GameScreen) findViewById(R.id.game_screen);

        final Button btn = (Button) findViewById(R.id.button);
        btn.setText("Baranov");

        Vector vec = new Vector(0, 1);
        Vector vec1 = new Vector(-1, 0);
//        Log.d("angle", String.valueOf(Math.toDegrees(vec.orianted_angle(vec1))));
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
                            for (int i = 0; i < 100; i++) {
                                draft_ball.move(RoomList.get(current_room).ObjectList, true);
                                com.admin.bounceultra.Point p = new com.admin.bounceultra.Point(draft_ball.x, draft_ball.y);
                                trajectory.add(p);
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
