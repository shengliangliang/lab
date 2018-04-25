package com.king.concurrent.high.test004;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyExecutor extends ThreadPoolExecutor{

    private ConcurrentHashMap<String, Date> startTimes;


    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
       // super(i, i1, l, timeUnit, blockingQueue);
        super(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit,
                blockingQueue);

        startTimes=new ConcurrentHashMap<>();

    }

    public MyExecutor(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i1, l, timeUnit, blockingQueue, threadFactory);
    }

    public MyExecutor(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i1, l, timeUnit, blockingQueue, rejectedExecutionHandler);
    }

    public MyExecutor(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i1, l, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }


    @Override

    public void shutdown() {

        System.out.println("MyExecutor: Going to shutdown.\n");

        System.out.println("MyExecutor: Executed tasks:"+getCompletedTaskCount());

        System.out.println("MyExecutor: Running tasks:"+getActiveCount());
        System.out.println("MyExecutor: Pending tasks:"+getQueue().size());

        super.shutdown();

    }


    @Override
    public List<Runnable> shutdownNow() {

        System.out.println("MyExecutor: Going to immediately shutdown.\n");

        System.out.println("MyExecutor: Executed tasks:"+getCompletedTaskCount());
        System.out.println("MyExecutor: Running tasks:"+getActiveCount());

        System.out.println("MyExecutor: Pending tasks:"+getQueue().size());

        return super.shutdownNow();

    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {

        System.out.println("MyExecutor-beforeExecute: a task is beginning: %s :"+t.getName()+","+r.hashCode());

        startTimes.put(String.valueOf(r.hashCode()), new Date());

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        Future<?> result=(Future<?>)r;

        try {

            System.out.println("MyExecutor-afterExecute: a task is finishing.\n");

            System.out.println("MyExecutor-afterExecute: Result: %s"+result.get());

            Date startDate=startTimes.remove(String.valueOf(r.hashCode()));

            Date finishDate=new Date();

            long diff=finishDate.getTime()-startDate.getTime();

            System.out.println("MyExecutor: Duration: %d"+diff);

        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();

        }

    }

}

