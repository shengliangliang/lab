package com.king.lock;

public class Thread3 implements Runnable{
    Demo demo;
    int num;
    public Thread3(Demo demo,int num){
        this.demo = demo;
        this.num = num;
    }
    @Override
    public void run() {
        demo.minus(num);
    }
}
