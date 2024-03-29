package com.king.test.jdk17.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class QuickResult {



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,200,TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        CompletionService<String> completionService = new ExecutorCompletionService<>(threadPoolExecutor);

        List<Future<String>> futureList = new ArrayList<>();
        futureList.add(completionService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Random random =  new Random();
                int delay = random.nextInt(100);
                System.out.println("a:"+delay);
                Thread.sleep(delay);
                String r = a();
                System.out.println("a:complete");
                return r;
            }
        }));
        futureList.add(completionService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Random random =  new Random();
                int delay = random.nextInt(100);
                System.out.println("b:"+delay);
                Thread.sleep(delay);
                String r = b();
                System.out.println("b:complete");
                return r;
            }
        }));
        futureList.add(completionService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Random random =  new Random();
                int delay = random.nextInt(100);
                System.out.println("c:"+delay);
                Thread.sleep(delay);
                String r = c();
                System.out.println("c:complete");
                return r;
            }
        }));
        // 只取出最快的 一个 结果
        //String result = completionService.take().get();

        // 取出全部的计算结果
        while(true){
            String result = completionService.take().get();
            if(null!=result&&!"".equals(result)){
                System.out.println(result);
            }else{
                break;
            }
        }

        //executorService.shutdownNow();
        threadPoolExecutor.shutdownNow();

    }

    public static String a( ){
        // do sth...

        return "1";
    }
    public static String b(){
        // do sth...
        return "2";
    }
    public static String c(){
        // do sth...
        return "3";
    }
}
