package com.king.concurrent.high.test001;

import java.util.List;
import java.util.concurrent.*;

public class TestExecutor {

    public static void main(String[] args){
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask = new CallableDemo();
        //提交任务并获取执行结果
        Future<List<Dto>> future = es.submit(calTask);

        //关闭线程池
        es.shutdown();
        System.out.println("==========begin========");
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            List<Dto> list = future.get();
            if(list!=null){
                list.forEach( dto-> System.out.println("name:"+dto.getName()+",age:"+dto.getAge())  );
                //System.out.println("future.get()-->"+future.get());
            }else{
                System.out.println("future.get()--> no result");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=================End================");

    }
}
