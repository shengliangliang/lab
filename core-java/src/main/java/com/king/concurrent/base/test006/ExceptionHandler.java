package com.king.concurrent.base.test006;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n",thread.getId());
        System.out.printf("Exception: %s: %s\n",throwable.getClass().getName(),throwable.getMessage());
        System.out.printf("Stack Trace: \n");
        throwable.printStackTrace(System.out); System.out.printf("Thread status: %s\n",thread.getState());
    }
}
