package com.king.algorithm;

public class Factorial {
    private static Integer factor(Integer n){
        if(n>1){
            return n * factor(n-1);
        }
        return 1;
    }
    public static void main(String[] args) {
        System.out.println(factor(6));
    }
}
