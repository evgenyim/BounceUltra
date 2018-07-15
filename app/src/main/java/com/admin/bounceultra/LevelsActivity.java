package com.admin.bounceultra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class LevelsActivity extends AppCompatActivity {

    static ArrayList<Room> RoomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final LevelsActivity this_ = this;
        RoomList = RoomCreate.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Button level1Button = findViewById(R.id.button1);
        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.startRoom = 0;
                MapActivity.roomAmount = 1;
                Intent intent = new Intent(this_, MapActivity.class);
                startActivity(intent);
            }
        });

        Button level2Button = findViewById(R.id.button2);
        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.startRoom = 1;
                MapActivity.roomAmount = 4;
                Intent intent = new Intent(this_, MapActivity.class);
                startActivity(intent);
            }
        });
        Button level3Button = findViewById(R.id.button3);
        level3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.startRoom = 5;
                MapActivity.roomAmount = 9;
                Intent intent = new Intent(this_, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
