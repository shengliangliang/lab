package com.king.btrace;

/*
import com.sun.btrace.annotations.*;

import java.util.concurrent.atomic.AtomicInteger;

import static com.sun.btrace.BTraceUtils.println;

@BTrace(unsafe = true)
public class AppTrace {

    static AtomicInteger count = new AtomicInteger(0);

    @OnMethod( clazz = "com.king.App",
            method = "sayHello",
            location = @Location(Kind.RETURN)//函数返回的时候执行，如果不填，则在函数开始的时候执行
            )
    public static void sayHello(String name, int age, @Return String result) {
        println("name: " + name+"  age: " + age);
        println("result:"+result);
        count.incrementAndGet();
        println("count:"+count.get());
    }

}*/


/**
 * BTrace是Java的安全可靠的动态跟踪工具。 他的工作原理是通过 instrument + asm 来对正在运行的java程序中的class类进行动态增强。
 *
 * 说他是安全可靠的，是因为它对正在运行的程序是只读的。也就是说，他可以插入跟踪语句来检测和分析运行中的程序，不允许对其进行修改。因此他存在一些限制：
 *
 *     不能创建对象
 *     不能创建数组
 *     不能抛出和捕获异常
 *     不能调用任何对象方法和静态方法
 *     不能给目标程序中的类静态属性和对象的属性进行赋值
 *     不能有外部、内部和嵌套类
 *     不能有同步块和同步方法
 *     不能有循环(for, while, do..while)
 *     不能继承任何的类
 *     不能实现接口
 *     不能包含assert断言语句
 *
 * 这些限制其实是可以使用unsafe模式绕过。通过声明 *@BTrace(unsafe = true) annotation 并且以unsafe 模式-u运行btrace
 * **/