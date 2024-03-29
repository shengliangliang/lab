package com.king.algorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
 *
 * 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的 商 。
 *
 * 注意：假设我们的环境只能存储 32 位 有符号整数，其数值范围是 [−231,  231 − 1] 。本题中，如果商 严格大于 231 − 1 ，则返回 231 − 1 ；如果商 严格小于 -231 ，则返回 -231 。
 *
 *
 * */
public class Divide {


    //二分查找 时间复杂度为logC*C
    public static int div(int dividend, int divisor){
        if(dividend==Integer.MIN_VALUE){
            if(divisor==1){
                return Integer.MIN_VALUE;
            }
            if(divisor==-1){
                return Integer.MAX_VALUE;
            }
        }
        if(divisor == Integer.MIN_VALUE){
            return dividend==Integer.MIN_VALUE?1:0;
        }
        if(divisor == 0){
            return 0;
        }
        boolean rev = false;
        if(divisor>0){
            divisor = -divisor;
            rev = !rev;
        }
        if(dividend>0){
            dividend = -dividend;
            rev =  !rev;
        }

        int left  = 1,  right= Integer.MAX_VALUE  ,ans = 0;
        while(left<=right){
            int mid =  left  + ((right-left)>>1);
            Boolean check = quickAdd(divisor,mid,dividend);
            if(check){
                ans = mid;
                if(mid==Integer.MAX_VALUE){
                    break;
                }
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }

    private static Boolean quickAdd(int y, int z, int x) {

        //此时 y 和  x 都是负数，而z是正数
        int result = 0, add = y;

        while(z!=0){
            // 判断奇数还是偶数
            if((z & 1)!=0){//奇数时
                if(result<x-add){//这里表示 result+add 将会小于 x，x是负数，add是负数，result也是负数
                    return false;
                }
                result += add;
            }
            if(z!=1){
                if(add < x-add){//如果add 再累加一次比x小了不行，到达边界了
                    return false;
                }
                add += add;
            }
            z >>= 1;//z每次都缩小2倍，直到变为 0。
        }

        //如果z的值可以为0了，表示找到了具体的z值。
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        int  a = 1;
        System.out.println(a>>=1);
        System.out.println(div(33999848,1000000));
        System.out.println(divide(33999848,1000000));
    }



     // 类二分查找，时间复杂度为logC，更为高效
    public static int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }

        return rev ? -ans : ans;
    }

}
