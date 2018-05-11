package com.king.concurrent.base.test013;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    /**
     * 一个类型为LinkedList<String>，名为buffer的属性，用来存储共享数据
     一个类型为int，名为maxSize的属性，用来存储缓冲区的长度
     一个名为lock的ReentrantLock对象，用来控制修改缓冲区代码块的访问
     两个名分别为lines和space，类型为Condition的属性
     一个Boolean类型，名为pendingLines的属性，表明如果缓冲区中有行
    * */
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition space;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize=maxSize;
        buffer=new LinkedList<>();
        lock=new ReentrantLock();
        lines=lock.newCondition();
        space=lock.newCondition();
        pendingLines=true;
    }

    /**
        实现insert()方法，接收一个String类型参数并试图将它存储到缓冲区。首先，它获得锁的控制。当它有锁的控制，它将检查缓冲区是否有空闲空 间。
        如果缓冲区已满，它将调用await()方法在space条件上等待释放空间。如果其他线程在space条件上调用signal()或 signalAll()方法，这个线程将被唤醒。
        当这种情况发生，这个线程在缓冲区上存储行并且在lines条件上调用signallAll()方法，稍 后我们将会看到，这个条件将会唤醒所有在缓冲行上等待的线程。
    */
    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(),buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    /**
     *
     * 实现get()方法，它返回存储在缓冲区上的第一个字符串。首先，它获取锁的控制。当它拥有锁的控制，它检查缓冲区是否有行。
     * 如果缓冲区是空的，它调用 await()方法在lines条件上等待缓冲区中的行。如果其他线程在lines条件上调用signal()或signalAll()方法，这个线程将 被唤醒。
     * 当它发生时，这个方法获取缓冲区的首行，并且在space条件上调用signalAll()方法，然后返回String
     *
     * */
    public String get() {
        String line=null;
        lock.lock();
        try {
            while ((buffer.size() == 0) &&(hasPendingLines())) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n",Thread.currentThread().getName(),buffer.size());

                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines=pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size()>0;
    }

}
