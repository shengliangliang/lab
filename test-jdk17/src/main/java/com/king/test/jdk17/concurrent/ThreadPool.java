package com.king.test.jdk17.concurrent;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.*;

/***
 * 手写简易线程池
 */
public class ThreadPool {

    private int corePoolSize;
    private int maxPoolSize;
    private int capacity;
    private int idle;
    private TimeUnit timeUnit;
    private BlockingDeque<Runnable> blockingDeque;
    //private RejectedExecutionHandler rejectedExecutionHandler;
    private ThreadFactory threadFactory;

    private HashSet<Worker> workers = new HashSet<>();

    public ThreadPool(int corePoolSize,int maxPoolSize,int capacity){
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.capacity = capacity;
        this.idle = 5;
        this.timeUnit = TimeUnit.SECONDS;
        this.blockingDeque = new LinkedBlockingDeque<>(this.capacity);
        this.threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
    }
    public ThreadPool(int corePoolSize,int maxPoolSize,int idle,TimeUnit timeUnit,BlockingDeque<Runnable> blockingDeque,ThreadFactory threadFactory){
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.idle = idle;
        this.timeUnit = timeUnit;
        this.blockingDeque = blockingDeque;
        this.threadFactory = threadFactory;
    }

    public void execute(Runnable runnable){
        synchronized (workers){
            if(runnable==null){
                return;
            }
            if(workers.size()<corePoolSize){
                Worker worker = new Worker(runnable);
                workers.add(worker);
                System.out.println("添加工作线程："+workers.size());
                worker.start();
            }else {

                if(blockingDeque.size()<capacity){
                    System.out.println("添加到等待队列:"+blockingDeque.size());
                    blockingDeque.add(runnable);
                }else{
                    if(workers.size()<maxPoolSize){
                        Worker worker = new Worker(runnable);
                        workers.add(worker);
                        System.out.println("再次添加添加工作线程："+workers.size());
                        worker.start();
                    }else{
                        System.out.println("抛弃任务：");
                    }

                }
            }

        }
    }

    class Worker extends Thread{
        Runnable task;
        Worker(Runnable task){
            this.task = task;
        }
        @Override
        public void run() {
            while (task!=null||(task=blockingDeque.poll())!=null){
               System.out.println("工作线程执行任务");
               try {
                   task.run();
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   task = null;
               }
            }

            /*synchronized (workers){
                workers.remove(this);
            }*/
        }
    }


    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2,5,10);
        for(int i=0;i<20;i++){

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("任务完成");
                }
            };

            threadPool.execute(task);
            System.out.println("添加任务："+i);
        }
    }
}
