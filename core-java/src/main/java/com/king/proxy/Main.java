package com.king.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Main {
    public static void main(String[] args) {
        /*HelloInterface helloInterface = new HelloStaticProxy();
        helloInterface.sayHello();*/


        HelloInterface  hello = new Hello1();
        InvocationHandler invocationHandler = new HelloDynamicProxy(hello);

        HelloInterface helloInterface1 = (HelloInterface)Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                invocationHandler);
        helloInterface1.sayHello();

    }
}
