package com.king.concurrent.base.test002;


import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();

        try {
            //Thread.sleep(5000);
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();

    }
}
