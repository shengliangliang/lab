package com.king.test.jdk17.concurrent;

import java.util.Random;
import java.util.concurrent.Exchanger;

// exchanger 用于两个线程之间交换数据，不用用于多个线程之间
public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<Message> exchanger = new Exchanger<>();
        new Thread(new Producer(exchanger)).start();
        new Thread(new Consumer(exchanger)).start();
    }
}

class Producer implements Runnable{
    private final Exchanger<Message> exchanger;

    Producer(Exchanger exchanger){
        this.exchanger  =  exchanger;
    }
    @Override
    public void run() {
        Message message  = new Message();
        for(int i=0;i<3;i++){
            Random r =  new Random();
            int time  = r.nextInt(5000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            message.setName("I am "+Thread.currentThread().getName()+ time);
            try {
                message = exchanger.exchange(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"交换得到的数据为："+message.getName());
        }
    }
}

class Consumer implements Runnable {
    private final Exchanger<Message> exchanger;

    Consumer(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        Message msg = new Message();
        while (true) {
            try {
                Thread.sleep(1000);
                msg = exchanger.exchange(msg);
                System.out.println(Thread.currentThread().getName() + ": 消费了数据[" + msg.getName() + "]");
                msg.setName("consumer-"+Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
class Message{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}