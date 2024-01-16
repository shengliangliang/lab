package com.king.algorithm;

public class FindSubArray {

//最大的子序列和
     static Integer subArraySum(Integer[] array){
        //0， 先定一个变量 Integer result = Integer.MIN_VALUE;
        //1，先找出所有的子序列
        //2，计算每个序列的和 并跟 result变量比较
        //3，根据比较结果，如果子序列的和大于result，那么就替换掉result的值，如果小于result的值，那么计算下一个序列
        //4，直到遍历完全部子序列，result的值就是最大的子序列和结果
        Integer result = Integer.MIN_VALUE;
        for(int i=1;i<=array.length;i++){
            //每次取出定长的数组，最短是1个，最长是整个数组
            for(int j=0;j<(array.length-i);j++){
                Integer sum = 0;
                for(int k=0;k<=i;k++){
                    //把相邻的j个元素的和计算出来
                    Integer item = array[j+k];
                    sum += item;
                }
                if(sum>result){
                    result = sum;
                }
            }
        }
        return  result;
    }

    static Integer subArraySum2(Integer[] arr){
         int n = arr.length;
         int[] end = new int[n];
         int[] all = new int[n];


         end[n-1] = arr[n-1];
         all[n-1] = arr[n-1];
         end[0] = all[0] = arr[0];

         for(int i=1;i<n;i++){
             end[i] = max(end[i-1] + arr[i],arr[i]);
             all[i] = max(end[i],all[i-1]);
         }
         return all[n-1];

    }


    static Integer max(Integer m,Integer n){
         return m>n?m:n;
    }
    public static void main(String[] args){
        Integer[] array = {3, -2, 3, 5,-8,9,-7, -1, 40};
        //System.out.println(subArraySum(array));
        System.out.println(subArraySum2(array));
    }
}
