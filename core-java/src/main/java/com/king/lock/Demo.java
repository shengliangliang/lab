package com.king.lock;

public class Demo {

    private static int k = 5;
    public synchronized int add1 (int a){
        k++;
        System.out.println("add1");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return  a++;
    }
    public synchronized int add2(int a){
        k++;
        System.out.println("add2");
        return  a+=2;
    }
    public int minus(int a){
        k++;
        System.out.println("minus");
        return a--;
    }

    public static synchronized int minus2(int a){
        k++;
        System.out.println("minus2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a--;
    }
}
