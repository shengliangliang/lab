package com.king.test.jdk17;

/**
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续
 * 子数组
 *  [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 */
public class ShortestSubArray {

    static int shortest(int[] arr,int target){

        if(arr==null){
            return 0;
        }

        int slow=0;
        int fast=1;

        int length=Integer.MAX_VALUE;
        while(slow<=fast&&fast<=arr.length){
            int sum = compute(arr,slow,fast);
            if(sum>=target){
                if(fast-slow<length){
                    length=fast-slow;
                }
                slow++;
            }else{
                fast++;
            }
        }
        System.out.println(slow);
        System.out.println(fast);
        if(length==Integer.MAX_VALUE){
            return 0;
        }
        return length;
    }

    private static int compute(int[] arr, int slow, int fast) {
        int sum = 0;
        for(int i=slow;i<fast;i++){
            sum+=arr[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(shortest(new int[]{1,2,3,4,1,2,3,4,5,6,7,8},15));
    }




}
