package com.king.concurrent.base.test022;

import java.util.concurrent.Semaphore;

public class Main {


    static class Printer1 implements Runnable{
        /**
         * 两个线程交替打印0~100的奇偶数：
         * 偶线程：0
         * 奇线程：1
         * 偶线程：2
         * 奇线程：3
         * */
        static int counter = 0;
        String name ;
        Printer1(String name){
            this.name = name;
        }
        @Override
        public void run() {

            while (counter<100) {
                synchronized (Printer1.class) {
                    System.out.println(name + "--" + counter++);
                    Printer1.class.notify();
                    try {
                        Printer1.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       // new Thread(new Printer1("偶数")).start();
        //new Thread(new Printer1("奇数")).start();


        printer2();

    }

    static int result = 0;
    public static void printer2() throws InterruptedException {
        /**通过N个线程顺序循环打印从0至100，如给定N=3则输出:
         thread0: 0
         thread1: 1
         thread2: 2
         thread0: 3
         thread1: 4
        *
        * */
        int N = 5;
        Thread[] threads = new Thread[N];
        final Semaphore[] syncObjects = new Semaphore[N];
        for (int i = 0; i < N; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != N-1){
                syncObjects[i].acquire();
            }
        }
        for (int i = 0; i < N; i++) {
            final Semaphore lastSemphore = i == 0 ? syncObjects[N - 1] : syncObjects[i - 1];
            final Semaphore curSemphore = syncObjects[i];
            final int index = i;
            threads[i] = new Thread(new Runnable() {

                public void run() {
                    try {
                        while (true) {
                            lastSemphore.acquire();
                            System.out.println("thread" + index + ": " + result++);
                            if(result>100){
                                break;
                            }
                            curSemphore.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            threads[i].start();
        }
    }
}
