package com.king.concurrent.high.test001;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TestExecutor2 {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask = new CallableDemo();
        //创建FutureTask
        FutureTask<List<Dto>> futureTask = new FutureTask<>(calTask);
        //执行任务
        es.submit(futureTask);
        //关闭线程池
        es.shutdown();
        try {
            System.out.println("主线程在执行其他任务");
            List<Dto> list = futureTask.get();
            if(list!=null){
                //输出获取到的结果
                list.forEach( dto-> System.out.println("name:"+dto.getName()+",age:"+dto.getAge())  );
            }else{
                //输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }
}
