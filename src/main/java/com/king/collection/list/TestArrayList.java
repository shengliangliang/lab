package com.king.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestArrayList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        Random random = new Random();
        for(int i=0;i<10000;i++){
            list.add(random.nextInt(10000));
        }

        for(Integer v:list){
            System.out.printf("value: %d \n",v);
        }

    }
}
