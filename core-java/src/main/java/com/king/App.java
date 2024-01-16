package com.king;

import jdk.nashorn.internal.ir.Block;

import java.util.*;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();

        map.put(null,"val1");
        map.put(null,"val2");

        //StringBuffer sb = new StringBuffer("");

        /*Executors.newFixedThreadPool();
        List list = new CopyOnWriteArrayList();
        BlockingDeque blockingDeque = new ArrayBlockingQueue<>()

        List<String> list = new ArrayList<>();*/
        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
