package com.king.lock;

public class Thread2 implements Runnable{
    Demo demo;
    int num;
    public Thread2(Demo demo,int num){
        this.demo = demo;
        this.num = num;
    }
    @Override
    public void run() {
        demo.add2(num);
    }
}
