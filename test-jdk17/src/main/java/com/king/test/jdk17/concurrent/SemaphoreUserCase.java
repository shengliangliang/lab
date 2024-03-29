package com.king.test.jdk17.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//模拟停车场停车
public class SemaphoreUserCase {

    public static void main(String[] args) {


        Parking  p = new Parking(3);

        ExecutorService pool  = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            pool.execute(new Car(p));
        }
        /*使用单线程的方式*/
        /*for(int i=0;i<10;i++){
            new Thread(new Car(p)).start();
        }*/

    }

}


class Parking{
    private Semaphore semaphore;
    Parking(int count){
        semaphore  =  new Semaphore(count);
    }

    public void park(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getId()+"获得停车许可");
            Random random = new Random();
            int time  = random.nextInt(10000);

            Thread.sleep(time);
            System.out.println(Thread.currentThread().getId()+"在停车场停留"+time+"ms");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println(Thread.currentThread().getId()+"离开停车场");
            semaphore.release();
        }



    }
}

class Car implements Runnable{
    Parking  parking;
    Car(Parking parking){
        this.parking = parking;
    }
    @Override
    public void run() {
        //模拟停车
        parking.park();
    }
}