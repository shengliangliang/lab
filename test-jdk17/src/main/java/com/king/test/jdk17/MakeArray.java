package com.king.test.jdk17;

public class MakeArray {



    //数组最大长度
    public static int arrayLength=100000000;
    //任务拆分最小单位,拆分到这个单位就不能拆分了,即阈值
    public static int splitMixThreshold=(arrayLength/100);


    public static int[] make(){
        int[] origin=new int[arrayLength];
        //Random random=new Random();
        for (int i = 0; i < arrayLength; ++i) {
            origin[i]=i;
        }
        return origin;
    }



    public static void main(String[] args) {
        int[] array= MakeArray.make();
        int sum=0;
        System.out.println("开始计时!");
        long recordTime=System.currentTimeMillis();
        for (int i = 0; i < array.length; ++i) {
            sum+=array[i];
        }
        System.out.println("数组累加最终结果:"+sum+",耗时时长:"+(System.currentTimeMillis()-recordTime)+"ms");

    }
}
