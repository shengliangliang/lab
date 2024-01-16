package com.king.concurrent.high.test008;

public class WaitAndNotify {
    private Object lock = new Object();
    private boolean envReady  = false;
    private class WorkerThread extends Thread{
        @Override
        public void run(){
            System.out.println("线程 WorkerThread 等待拿锁");
            synchronized (lock){
                try {
                   // Thread.sleep(50);

                    for(int i=0;i<10000;i++){

                    }
                    System.out.println("线程 WorkerThread 拿到锁");

                    if(!envReady){
                        System.out.println("线程 WorkerThread 放弃锁");
                        lock.wait();
                    }

                    System.out.println("线程 WorkerThread 收到通知后继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class PrepareEnvThread extends Thread{

        @Override
        public void run(){
            System.out.println("线程 PrepareEnvThread 等待拿锁");
            synchronized (lock){
                try {

                    System.out.println("线程 PrepareEnvThread 拿到锁");
                    envReady = true;
                    lock.notify();
                    System.out.println("通知 WorkerThread ");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  void prepareEnv(){
        new PrepareEnvThread().start();
    }

    public void work(){
        new WorkerThread().start();
    }

    public static void main(String[] args) {
        WaitAndNotify t = new WaitAndNotify();
        t.work();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.prepareEnv();
    }
}
