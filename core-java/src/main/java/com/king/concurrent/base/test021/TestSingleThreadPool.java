package com.king.concurrent.base.test021;

import java.util.concurrent.*;

public class TestSingleThreadPool {


    //static ExecutorService executorService = Executors.newSingleThreadExecutor();
    static ExecutorService executorService2 = Executors.newFixedThreadPool(8);

     static class ThreadLocalMap{
        ThreadLocal<Entity> entityLocal = new ThreadLocal<Entity>();

        public void set(Entity entity){
            entityLocal.set(entity);
        }

        public Entity get(){
            return  entityLocal.get();
        }
    }


    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            10,
            200,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(10),new SaturatePolicy());


    public static void main(String[] args) {
        String msg = "jacky";
        for(int i=0;i<100000;i++){
            System.out.println("put " + i +" to pool ");
            executor.submit(new SingleThread(i,i+msg,new ThreadLocalMap()));
        }
    }


    static class SingleThread implements Runnable {

        Integer i = 0;
        String name = "";
        ThreadLocalMap threadLocalMap;
        SingleThread(int i,String name,ThreadLocalMap threadLocalMap){
            this.i = i;
            this.name = name;
            this.threadLocalMap = threadLocalMap;
        }
        @Override
        public void run() {
            Entity entity = new Entity();
            entity.setId(Thread.currentThread().getId());
            entity.setName(Thread.currentThread().getName());

            threadLocalMap.set(entity);

            System.out.println(Thread.currentThread().getId()+"==="+Thread.currentThread().getName());
            System.out.println();
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println("this is "+ i +"，I'm out ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 自定义线程池队列饱和策略
     */
    public static class SaturatePolicy implements RejectedExecutionHandler {
        /**
         */
        public SaturatePolicy() { }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getId()+"-"+thread.getName());
        }
    }



}
