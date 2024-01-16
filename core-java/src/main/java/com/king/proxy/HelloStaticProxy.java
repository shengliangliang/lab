package com.king.proxy;

public class HelloStaticProxy implements HelloInterface{
    HelloInterface helloInterface = new Hello1();

    @Override
    public void sayHello() {
        System.out.println("before");
        helloInterface.sayHello();
        System.out.println("after");
    }
}
