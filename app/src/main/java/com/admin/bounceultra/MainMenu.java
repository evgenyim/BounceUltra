package com.admin.bounceultra;

import android.content.Intent;
import android.graphics.Point;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    static int width;
    static int height;
    static boolean firstStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Display display = getWindowManager().getDefaultDisplay();
        android.graphics.Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final MainMenu this_ = this;
        Button play_button = findViewById(R.id.play_button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(this_, LevelsActivity.class);
                startActivity(intent);
            }
        });

    }
}
