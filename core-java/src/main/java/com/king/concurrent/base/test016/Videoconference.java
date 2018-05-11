package com.king.concurrent.base.test016;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {


    //声明 CountDownLatch 对象名为 controller。
    private final CountDownLatch controller;



    @Override
    public void run() {

        //首先，使用 getCount() 方法来输出这次video conference的参与值的数量信息。
        System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());

        //然后， 使用 await() 方法来等待全部的参与者。由于此法会抛出 InterruptedException 异常，所以要包含处理代码。
        try {
            controller.await();

            //11. 最后，输出信息表明全部参与者已经到达。
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    //实现类的构造函数，初始 CountDownLatch 属性。Videoconference 类接收将要等待的参与者的量为参数。
    public Videoconference(int number) {
        controller=new CountDownLatch(number);
    }

    //实现 arrive() 方法。每次有参与者到达都会调用此方法。它接收String类型的参数名为 name。
    public void arrive(String name) {

        //首先，它输出某某参数已经到达。
        System.out.printf("%s has arrived. ",name);

        //然后，调用CountDownLatch对象的 countDown() 方法。
        controller.countDown();

        //最后，使用CountDownLatch对象的 getCount() 方法输出另一条关于还未确定到达的参与者数。
        System.out.printf( " VideoConference:Waiting for %d participants.\n ",controller.getCount());

    }
}