package com.king.concurrent.base;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {




    public static void main(String[] args){
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("我只是打印一句话...而已");
            }
        };
        timer.schedule(tt,0,1000);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tt.cancel();

    }




}
