package com.king.concurrent.high.test005;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask extends RecursiveTask<Integer> {
    private int array[];

    private int start, end;

    @Override
    protected Integer compute() {

        Integer ret;

        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();

        thread.addTask();

        return start;

    }

    public MyRecursiveTask(int array[], int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;

    }


    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {

        int value;
        try {

            value = task1.get().intValue() + task2.get().intValue();

        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {

            e.printStackTrace();
            value = 0;

        }

        try {

            TimeUnit.MILLISECONDS.sleep(10);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        return value;

    }


}

