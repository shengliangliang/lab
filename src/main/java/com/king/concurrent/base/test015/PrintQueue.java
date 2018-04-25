package com.king.concurrent.base.test015;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建一个会实现print queue的类名为 PrintQueue。
public class PrintQueue {

    private boolean freePrinters[];

    //接着，声明一个名为lockPrinters的Lock对象。将要使用这个对象来保护freePrinters array的访问。
    private Lock lockPrinters;

    private Semaphore semaphore;

    //修改类的构造函数并初始化新声明的对象们。freePrinters array 有3个元素，全部初始为真值。semaphore用3作为它的初始值。
    public PrintQueue() {

        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];

        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();
    }

    //修改printJob()方法。它接收一个称为document的对象最为唯一参数。
    public void printJob(Object document) {

        //首先，调用acquire()方法获得semaphore的访问。由于此方法会抛出 InterruptedException异常，所以必须加入处理它的代码。
        try {
            semaphore.acquire();

            //接着使用私有方法 getPrinter()来获得被安排打印任务的打印机的号码。
            int assignedPrinter = getPrinter();

            //然后， 随机等待一段时间来实现模拟打印文档的行。
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job in Printer%d during %d seconds\n", Thread.currentThread().getName(), assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            //最后，调用release() 方法来解放semaphore并标记打印机为空闲，通过在对应的freePrinters array引索内分配真值。
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }


    //实现 getPrinter() 方法。它是一个私有方法，返回一个int值，并不接收任何参数。
    private int getPrinter(){

        //首先，声明一个int变量来保存printer的引索值。
        int ret = -1;

        //然后， 获得lockPrinters对象 object的访问。
        try {
            lockPrinters.lock();

            //然后，在freePrinters array内找到第一个真值并在一个变量中保存这个引索值。修改值为false，因为等会这个打印机就会被使用。
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }

            //最后，解放lockPrinters对象并返回引索对象为真值。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPrinters.unlock();
        }
        return ret;

    }

}

/**
 * 在例子中的PrintQueue类的关键是：Semaphore对象创建的构造方法是使用3作为参数的。这个例子中，前3个调用acquire() 方法的线程会获得临界区的访问权，其余的都会被阻塞 。
 * 当一个线程结束临界区的访问并解放semaphore时，另外的线程才可能获得访问权。
 在这个临界区，线程获得被分配打印的打印机的引索值。
 * */
