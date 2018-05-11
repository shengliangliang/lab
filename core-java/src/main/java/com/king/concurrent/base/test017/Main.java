package com.king.concurrent.base.test017;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {

        //声明并初始5个常量来储存应用的参数。
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;

        //Create a MatrixMock 对象，名为 mock. 它将有 10,000 行，每行1000个元素。现在，你要查找的数字是5。
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        //创建 Results 对象，名为 results。它将有 10,000 元素。
        Results results = new Results(ROWS);

        //创建 Grouper 对象，名为 grouper。
        Grouper grouper = new Grouper(results);

        //创建 CyclicBarrier 对象，名为 barrier。此对象会等待5个线程。当此线程结束后，它会执行前面创建的 Grouper 对象。
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        //创建5个 Searcher 对象，5个执行他们的线程，并开始这5个线程。
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, 5, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");

    }
}



/**
 * 例子中解决的问题比较简单。我们有一个很大的随机的整数矩阵，然后你想知道这矩阵里面某个数字出现的次数。为了更好的执行，我们使用了 divide 和 conquer 技术。
 * 我们 divide 矩阵成5个子集，然后在每个子集里使用一个线程来查找数字。这些线程是 Searcher 类的对象。

 我们使用 CyclicBarrier 对象来同步5个线程的完成，并执行 Grouper 任务处理个别结果，最后计算最终结果。

 如我们之前提到的，CyclicBarrier 类有一个内部计数器控制到达同步点的线程数量。每次线程到达同步点，它调用 await() 方法告知 CyclicBarrier 对象到达同步点了。
 CyclicBarrier 把线程放入睡眠状态直到全部的线程都到达他们的同步点。

 当全部的线程都到达他们的同步点，CyclicBarrier 对象叫醒全部正在 await() 方法中等待的线程们，然后，选择性的，为CyclicBarrier的构造函数
 传递的 Runnable 对象（例子里，是 Grouper 对象）创建新的线程执行外加任务。
 * */
