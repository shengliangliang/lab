package com.king.test.jdk17.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTaskTest{

    public static void main(String[] args) {
        List<FutureTask<Integer>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int  i=0;i<10;i++){
            FutureTask<Integer>  futureTask  = new FutureTask<>(new ComputeTask("第"+i+"个计算任务"));
            list.add(futureTask);
            executorService.submit(futureTask);
        }

        System.out.println("【主线程任务】开始============");
        //主线程睡眠5秒，模拟主线程做某些任务
        try {
            Thread.sleep(5000);
            System.out.println("【主线程任务】开始执行某些任务============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("【主线程任务】结束============");


        for (FutureTask<Integer> task : list) {
            try {
                //FutureTask的get()方法会自动阻塞，知道得到任务执行结果为止
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdownNow();
    }
}

class ComputeTask implements Callable<Integer>{
    private String  taskName;
    ComputeTask(String  taskName){
        this.taskName = taskName;
    }
    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        for (int i = 1; i <=50; i++) {
            result = +i;
        }
        System.out.println("【计算任务】"+taskName +"执行完成。");
        return result;
    }
}