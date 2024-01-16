package com.king.lock;

public class Thread4 implements Runnable{
    Demo demo;
    int num;
    public Thread4(Demo demo,int num){
        this.demo = demo;
        this.num = num;
    }
    @Override
    public void run() {
        demo.minus2(num);
    }

}
