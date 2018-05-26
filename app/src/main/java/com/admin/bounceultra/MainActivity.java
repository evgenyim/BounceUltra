package com.admin.bounceultra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int i = r.nextInt(3);
                if (i == 1) {
                    Toast.makeText(getApplicationContext(), "Im", Toast.LENGTH_LONG).show();
                } else if (i == 2) {
                    Toast.makeText(getApplicationContext(), "Emdin", Toast.LENGTH_LONG).show();
                } else if (i == 0) {
                    Toast.makeText(getApplicationContext(), "Paramonov", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
