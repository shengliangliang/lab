package com.king.test.jdk17;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {

    int[] recursiveArray;
    //划分开始位置
    int form;
    //划分结束位置
    int to;

    public SumTask(int[] recursiveArray, int form, int to) {
        this.recursiveArray = recursiveArray;
        this.form = form;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        //判断当前数组最小长度阈值,没有达到继续拆分
        //System.out.println("form:"+form+",to:"+to+",arraySize:"+recursiveArray.length+",MakeArray.splitMixThreshold:"+MakeArray.splitMixThreshold);
        if((to-form)<= MakeArray.splitMixThreshold){
            //满足阈值的数组处理,累加
            int count= 0;
            for(int i=form;i<=to;++i){
                count+=recursiveArray[i];
            }
            return count;
        }else{
            //数组长度划半
            int half=(form+to)/2;
            //递归拆分
            //System.out.println("half:"+half);
            SumTask left=new SumTask(recursiveArray,form,half);
            SumTask right=new SumTask(recursiveArray,half+1,to);
            invokeAll(left,right);
            //等待最小阈值求和完毕在累加返回给上一层Join任务
            return left.join()+right.join();
        }
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] array=MakeArray.make();
        SumTask sumTask=new SumTask(array,0,array.length-1);
        Long recordTime=System.currentTimeMillis();
        forkJoinPool.submit(sumTask);
        System.out.println("数组累加最终结果:"+sumTask.join()+",耗时时长:"+(System.currentTimeMillis()-recordTime)+"ms");

    }
}
