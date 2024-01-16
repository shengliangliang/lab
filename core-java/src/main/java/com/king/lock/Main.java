package com.king.lock;

public class Main {
    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread t1 = new Thread(new Thread1(demo,100));
        Thread t2 = new Thread(new Thread2(demo,100));
        Thread t3 = new Thread(new Thread3(demo,100));
        Thread t4 = new Thread(new Thread4(demo,100));
        t4.start();
        t1.start();
        t2.start();
        t3.start();

    }
}
