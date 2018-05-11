package com.king.concurrent.base.test009;

public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String s) {
        super(s);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n",t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();

    }
}
