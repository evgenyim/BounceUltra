package com.admin.bounceultra;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Water extends GameObject {

    boolean isSwitched;

    void switchAction() {
        if (!isSwitched) {
            Timer timer = new Timer();
            MyTimerTask myTimerTask = new MyTimerTask();
            timer.schedule(myTimerTask, 0, 3000);
            isSwitched = true;
        }
    }

    void insideCommunicate(Ball ball, ArrayList<GameObject> objectList, int intersected_obj_ind, boolean draft) {
        if (!draft) {
            if (ball.centre().y - y_top >= ball.r) {
                Room.restart(MainActivity.current_room);
            }
        }
    }

    Water(float x_left, float y_top, float x_right, float y_bottom, int imageId){
        this.x_left = x_left;
        this.x_right = x_right;
        this.y_bottom = y_bottom;
        this.y_top = y_top;
        this.degrees = 0;
        this.x = (x_left + x_right) / 2;
        this.y = (y_bottom + y_top) / 2;
        this.imageId = imageId;
        this.isSwitched = false;
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            moveToXY(x, y - 10);
        }
    }
}
