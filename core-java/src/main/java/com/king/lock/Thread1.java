package com.king.lock;

public class Thread1 implements Runnable{
    Demo demo;
    int num;
    public Thread1(Demo demo,int num){
        this.demo = demo;
        this.num = num;
    }
    @Override
    public void run() {
        demo.add1(num);
    }
}
