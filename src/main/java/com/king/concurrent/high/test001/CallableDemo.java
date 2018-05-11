package com.king.concurrent.high.test001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<List<Dto>> {



    @Override
    public List<Dto> call() throws Exception {
        System.out.println("Callable子线程开始计算啦！");
        Thread.sleep(2000);

        List<Dto> list = new ArrayList<>();

        for(int i=0 ;i<5000;i++){
           Dto dto = new Dto("name"+i,i);
           list.add(dto);
        }
        System.out.println("Callable子线程计算结束！");
        return list;
    }
}
