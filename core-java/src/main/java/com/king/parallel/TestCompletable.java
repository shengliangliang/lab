package com.king.parallel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class TestCompletable {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        d1();
        System.out.println("main process");

    }

    public static void d1() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("do something...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Map<String,String> map = new HashMap<>();
            map.put("k1","succ");
            map.put("k2","fail");
            return "";
        });
        String result = completableFuture.get(1050, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }

}
