package com.king.annotation;


import java.lang.reflect.Method;


public class TestTarget {
    public static void main(String[] args) throws Exception{
        //1.1通过反射获取类
        Class<?> forName = Class.forName("com.king.annotation.Target");
        //1.2获取该类自身声明的所有方法
        Method[] methods = forName.getDeclaredMethods();
        int checkCount = 0; //测试的数量
        int uncheckCount = 0;  //未测试的数量
        for (Method method : methods) {
            if(method.isAnnotationPresent(Login.class)){
                checkCount++;
            }else{
                uncheckCount++;
            }
        }
        System.out.println("测试的方法有" + checkCount);
        System.out.println("未测试的方法有" + uncheckCount);
    }
}
