package com.king.concurrent.base.test018;

import java.util.concurrent.Phaser;


/**
 * Java 并发 API 提供的一个非常复杂且强大的功能是，能够使用Phaser类运行阶段性的并发任务。当某些并发任务是分成多个步骤来执行时，那么此机制是非常有用的。
 * Phaser类提供的机制是在每个步骤的结尾同步线程，所以除非全部线程完成第一个步骤，否则线程不能开始进行第二步。

 相对于其他同步应用，我们必须初始化Phaser类与这次同步操作有关的任务数，我们可以通过增加或者减少来不断的改变这个数。

 在这个指南，你将学习如果使用Phaser类来同步3个并发任务。这3个任务会在3个不同的文件夹和它们的子文件夹中搜索扩展名是.log并在24小时内修改过的文件。这个任务被分成3个步骤：

 1. 在指定的文件夹和子文件夹中获得文件扩展名为.log的文件列表。
 2. 过滤第一步的列表中修改超过24小时的文件。
 3. 在操控台打印结果。

 在步骤1和步骤2的结尾我们要检查列表是否为空。如果为空，那么线程直接结束运行并从phaser类中淘汰。
 * */
public class Main {
    public static void main(String[] args){
        //创建 含3个参与者的 Phaser 对象。
        Phaser phaser = new Phaser(3);

        //创建3个 FileSearch 对象，每个在不同的初始文件夹里搜索.log扩展名的文件。
        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Documents And Settings","log", phaser);

        //创建并开始一个线程来执行第一个 FileSearch 对象。
        Thread systemThread = new Thread(system, "System");
        systemThread.start();

        //创建并开始一个线程来执行第二个 FileSearch 对象。
        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();

        //创建并开始一个线程来执行第三个 FileSearch 对象。
        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();

        //等待3个线程们的终结。
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //使用isFinalized()方法把Phaser对象的结束标志值写入操控台。
        System.out.println("Terminated: " + phaser.isTerminated());
    }

}

/**
 * 这程序开始创建的 Phaser 对象是用来在每个phase的末端控制线程的同步。Phaser的构造函数接收参与者的数量作为参数。在这里，Phaser有3个参与者。
 * 这个数向Phaser表示 Phaser改变phase之前执行 arriveAndAwaitAdvance() 方法的线程数，并叫醒正在休眠的线程。

 一旦Phaser被创建，我们运行3个线程分别执行3个不同的FileSearch对象。

 在例子里，我们使用 Windows operating system 的路径。如果你使用的是其他操作系统，那么修改成适应你的环境的路径。

 FileSearch对象的 run() 方法中的第一个指令是调用Phaser对象的 arriveAndAwaitAdvance() 方法。像之前提到的，Phaser知道我们要同步的线程的数量。
 当某个线程调用此方法，Phaser减少终结actual phase的线程数，并让这个线程进入休眠 直到全部其余线程结束phase。
 在run() 方法前面调用此方法，没有任何 FileSearch 线程可以开始他们的工作，直到全部线程被创建。

 在phase 1 和 phase 2 的末端，我们检查phase 是否生成有元素的结果list,或者它没有生成结果且list为空。在第一个情况，checkResults() 方法之前提的调用 arriveAndAwaitAdvance()。
 在第二个情况，如果list为空，那就没有必要让线程继续了，就直接返回吧。但是你必须通知phaser，将会少一个参与者。为了这个，我们使用arriveAndDeregister()。
 它通知phaser线程结束了actual phase， 但是它将不会继续参见后面的phases,所以请phaser不要再等待它了。

 在phase3的结尾实现了 showInfo() 方法, 调用了 phaser 的 arriveAndAwaitAdvance() 方法。这个调用，保证了全部线程在同一时间结束。
 当此方法结束执行，有一个调用phaser的arriveAndDeregister() 方法。这个调用，我们撤销了对phaser线程的注册，所以当全部线程结束时，phaser 有0个参与者。

 最后，main() 方法等待3个线程的完成并调用phaser的 isTerminated() 方法。当phaser 有0个参与者时，它进入termination状态，此状态与此调用将会打印true到操控台。

 Phaser 对象可能是在这2中状态：

 Active: 当 Phaser 接受新的参与者注册，它进入这个状态，并且在每个phase的末端同步。 在此状态，Phaser像在这个指南里解释的那样工作。此状态不在Java 并发 API中。
 Termination: 默认状态，当Phaser里全部的参与者都取消注册，它进入这个状态，所以这时 Phaser 有0个参与者。更具体的说，当onAdvance() 方法返回真值时，
 Phaser 是在这个状态里。如果你覆盖那个方法,你可以改变它的默认行为。当 Phaser 在这个状态，同步方法 arriveAndAwaitAdvance()会 立刻返回，不会做任何同步。
 * */
