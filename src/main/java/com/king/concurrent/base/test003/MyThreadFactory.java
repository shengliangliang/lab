package com.king.concurrent.base.test003;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private int counter;
    private String prefix;

    public MyThreadFactory(String prefix){
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        MyThread myThread = new MyThread(runnable,prefix+"-"+counter);
        counter++;
        return myThread;
    }


}
