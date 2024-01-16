package com.king.test.jdk17;

import java.util.HashMap;
import java.util.Map;

public class DeadLock {

    private static String r1 = "a";
    private static String r2 = "b";
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (r1){
                System.out.println("get r1 ");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (r2){
                    System.out.println("get r2 ");
                }
            }
        }).start();
        Map<String,String> h = new HashMap<>();

        new Thread(()->{
            synchronized (r2){
                System.out.println("get r2 ");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (r1){
                    System.out.println("get r1 ");
                }
            }
        }).start();
    }

}
