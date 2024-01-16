package com.king.test.jdk17;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static int fibonacci(int n){
        if(n<=1){
            return 1;
        }
        return fibonacci(n-1)+n;
    }
    public static void main(String[] args) {
        System.out.println(fibonacci(3));
    }




}
