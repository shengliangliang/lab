package com.king.concurrent.base.test020;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Java 并发 API 提供了一种允许2个并发任务间相互交换数据的同步应用。更具体的说，Exchanger 类允许在2个线程间定义同步点，当2个线程到达这个点，他们相互交换数据类型，
 * 使用第一个线程的数据类型变成第二个的，然后第二个线程的数据类型变成第一个的。

 这个类在遇到类似生产者和消费者问题时，是非常有用的。来一个非常经典的并发问题：你有相同的数据buffer，一个或多个数据生产者，和一个或多个数据消费者。
 只是Exchange类只能同步2个线程，所以你只能在你的生产者和消费者问题中只有一个生产者和一个消费者时使用这个类。
 *
 * */
public class Core {

    public static void main(String[] args) {

        //创建2个buffers。分别给producer和consumer使用.
        List<String> buffer1 = new ArrayList<String>();
        List<String> buffer2 = new ArrayList<String>();

        //创建Exchanger对象，用来同步producer和consumer。
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

        //创建Producer对象和Consumer对象。
        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(buffer2, exchanger);

        //创建线程来执行producer和consumer并开始线程。
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }

}

/**消费者开始时是空白的buffer，然后调用Exchanger来与生产者同步。因为它需要数据来消耗。
 * 生产者也是从空白的buffer开始，然后创建10个字符串，保存到buffer，并使用exchanger与消费者同步。

 在这儿，2个线程(生产者和消费者线程)都是在Exchanger里并交换了数据类型，所以当消费者从exchange() 方法返回时，它有10个字符串在buffer内。
 当生产者从 exchange() 方法返回时，它有空白的buffer来重新写入。这样的操作会重复10遍。

 如你执行例子，你会发现生产者和消费者是如何并发的执行任务和在每个步骤它们是如何交换buffers的。
 与其他同步工具一样会发生这种情况，第一个调用 exchange()方法会进入休眠直到其他线程的达到。
 *
 * */
