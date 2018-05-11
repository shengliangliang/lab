package com.king.concurrent.high.test005;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkerThread extends ForkJoinWorkerThread {

    private static ThreadLocal<Integer> taskCounter=new ThreadLocal<Integer>();

    protected MyWorkerThread(ForkJoinPool forkJoinPool) {
        super(forkJoinPool);
    }


    @Override
    protected void onStart(){
        super.onStart();
        taskCounter.set(0);
    }


    @Override
    protected void onTermination(Throwable throwable){
        super.onTermination(throwable);
    }


    public void addTask(){
        int counter = taskCounter.get().intValue();
        counter ++;
        taskCounter.set(counter);
    }
}
