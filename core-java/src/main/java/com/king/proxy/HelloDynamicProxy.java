package com.king.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloDynamicProxy implements InvocationHandler {
    Object subject = null;
    public  HelloDynamicProxy(Object o){
        subject = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before:"+method.getName());

        method.invoke(subject,args);

        System.out.println("after:"+method.getName());
        return null;
    }
}
