package com.king.concurrent.high.test004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MyExecutor myExecutor;
        myExecutor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        List<Future<String>> results = new ArrayList<>();

        for (int i=0; i<1; i++) {

            SleepTwoSecondsTask task = new SleepTwoSecondsTask();

            Future<String> result=myExecutor.submit(task);

            results.add(result);

        }


        for (int i=0; i<1; i++){

            try {

                String result=results.get(i).get();

                System.out.println("Main: Result for Task :"+i+result);

            } catch (InterruptedException | ExecutionException e) {

                e.printStackTrace();

            }

        }

        myExecutor.shutdown();

        /*for (int i=5; i<10; i++){

            try {

                String result=results.get(i).get();

                System.out.printf("Main: Result for Task %d :",i,result);

            } catch (InterruptedException | ExecutionException e) {

                e.printStackTrace();

            }

        }*/

        try {

            myExecutor.awaitTermination(1, TimeUnit.DAYS);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }


        System.out.printf("Main: End of the program.\n");


    }
}
