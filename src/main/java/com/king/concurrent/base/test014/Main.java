package com.king.concurrent.base.test014;



//实现例子的main类，创建名为 Main的类并实现main()方法。
public class Main {
    public static void main(String args[]) {

        //创建PrintQueue对象名为printQueue。
        PrintQueue printQueue = new PrintQueue();

        //创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        //最后，开始这10个线程们。
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}



/**
 * 这个例子的关键是PrintQueue类的printJob()方法。此方法展示了3个你必须遵守的步骤当你使用semaphore来实现critical section时，并保护共享资源的访问：

 1. 首先， 你要调用acquire()方法获得semaphore。
 2. 然后， 对共享资源做出必要的操作。
 3. 最后， 调用release()方法来释放semaphore。

 另一个重点是PrintQueue类的构造方法和初始化Semaphore对象。你传递值1作为此构造方法的参数，那么你就创建了一个binary semaphore。初始值为1，
 就保护了访问一个共享资源，在例子中是print queue。

 当你开始10个threads，当你开始10个threads时，那么第一个获得semaphore的得到critical section的访问权。剩下的线程都会被semaphore阻塞直到那个获得semaphore的线程释放它。
 当这情况发生，semaphore在等待的线程中选择一个并给予它访问critical section的访问权。全部的任务都会打印文档，只是一个接一个的执行。
 *
 * */
