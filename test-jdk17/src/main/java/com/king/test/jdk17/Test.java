package com.king.test.jdk17;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Test {

    public static ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap();
    public static Stack<Integer> stack = new Stack<>();


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()->{
            for(int i=0;i<100;i++){
               Integer val = map.get("age");
               if(val==null){
                   map.put("age", 1);
               }else{
                   map.put("age",val+1);
               }
            }
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            for(int i=0;i<100;i++){
                Integer val = map.get("age");
                if(val==null){
                    map.put("age", 1);
                }else{
                    map.put("age",val+1);
                }
            }
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            for(int i=0;i<100;i++){
                Integer val = map.get("age");
                if(val==null){
                    map.put("age", 1);
                }else{
                    map.put("age",val+1);
                }
            }
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
        System.out.println(map.get("age"));



        for(int i=0;i<100;i++){
            stack.push(i);
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
