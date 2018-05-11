package com.king.concurrent.base.test001;

public class Calculator implements Runnable {

    private int number;

    Calculator(int number){
        this.number = number;
    }

    @Override
    public void run() {

        for (int i=1;i<10;i++){
            System.out.printf("%s:%d * %d = %d \n",Thread.currentThread().getName(),number,i,i*number);
        }
    }
}
