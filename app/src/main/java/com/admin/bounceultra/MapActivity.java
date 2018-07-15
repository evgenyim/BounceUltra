package com.admin.bounceultra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MapActivity extends AppCompatActivity {
    static int startRoom;
    static int roomAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final LevelsScreen map = findViewById(R.id.levelsScreen);
        final MapActivity this_ = this;
        final View.OnTouchListener list = new View.OnTouchListener() {
            float x;
            float y;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                y = event.getY();
                MainActivity.current_room = roomNumber(x, y) + startRoom - 1;
                Intent intent = new Intent(this_, MainActivity.class);
                startActivity(intent);
                return false;
            }
        };
        map.setOnTouchListener(list);
    }

    int roomNumber (float x, float y) {
        int side = (int) Math.sqrt(roomAmount);
        int answer = (int) y  / (MainMenu.height / side);
        answer *= side;
        answer += (int) x / (MainMenu.width / side) + 1;
        return answer;
    }
}
