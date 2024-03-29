package com.king.test.jdk17.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

//模拟一个简单的赛车游戏
public class CountDownTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int racerNum = 5;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(5);

        ExecutorService pool = Executors.newFixedThreadPool(5);
        CompletionService<Long> completionService = new ExecutorCompletionService<>(pool);
        //List<Future> list  = new ArrayList<>();
        List<Future> list2  = new ArrayList<>();
        for(int i=0;i<racerNum;i++){
            //new Thread(new Racer(start,finish)).start();
            Callable callable  = new Racer(start,finish);
            //Future f = pool.submit(callable);
            //list.add(f);
            Future future = completionService.submit(callable);
            list2.add(future);
        }

        // 模拟倒计时
        System.out.println("倒计时开始...");
        for (int i = 3; i > 0; i--) {
            System.out.println("倒计时: " + i);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("比赛开始!");
        start.countDown();

        finish.await();
        System.out.println("比赛完成");
        pool.shutdown();
        /*for(Future f:list){
            System.out.println("ExecutorService:"+f.get());
        }*/

        for(Future f:list2){
            System.out.println("CompletionService:"+f.get());
        }

    }
}


class Racer implements Callable<Long>{

    private CountDownLatch start;
    private CountDownLatch finish;

    Racer(CountDownLatch start,CountDownLatch finish){
        this.start = start;
        this.finish = finish;
    }

    @Override
    public Long call() throws Exception {
        try {
            start.await();
            System.out.println(Thread.currentThread().getId()+"开始比赛");
            Random  random  = new Random();
            int time = random.nextInt(10000);
            try {
                System.out.println(Thread.currentThread().getId()+" take "+time+" ms");
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println(Thread.currentThread().getId()+"结束比赛");
            finish.countDown();
        }
        return Thread.currentThread().getId();
    }
}