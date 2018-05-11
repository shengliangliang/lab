package com.king.concurrent.high.test005;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyWorkerThreadFactory factory=new MyWorkerThreadFactory();

        ForkJoinPool pool=new ForkJoinPool(4, factory, null, false);

        int array[]=new int[100000];

        for (int i=0; i<array.length; i++){

            array[i]=1;

        }

        MyRecursiveTask task = new MyRecursiveTask(array,0,array.length);

        pool.execute(task);

        task.join();

        pool.shutdown();

        pool.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Main: Result: %d"+task.getRawResult());

        System.out.println("Main: End of the program\n");

    }
}
