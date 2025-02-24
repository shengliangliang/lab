package com.king.algorithm;

/***
 * 1、请写一个程序，它能够打印出符合以下规则的任意行数的图形。
 * 其中输入为int型参数，代表图形行数，且该行数为大于3的奇数。
 *   *
 *  ***
 * *****
 *  ***
 *   *
 */
public class PrintGraph {

    static void print(int n){
        for(int i=1;i<=n;i+=2){
            if(i%2==1){
                printer(i);
                System.out.println("\r");
            }
        }
        for(int i=n-2;i>0;i-=2){
            if(i%2==1){
                printer(i);
                System.out.println("\r");
            }
        }
    }

    static void printer(int k){
        for(int i=0;i<k;i++){
            System.out.print("*");
        }
    }

    public static void main(String[] args) {
        print(7);
    }
}
