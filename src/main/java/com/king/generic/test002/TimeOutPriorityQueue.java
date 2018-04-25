package com.king.generic.test002;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.PriorityBlockingQueue;

public class TimeOutPriorityQueue<T> {

    private PriorityBlockingQueue queue = new PriorityBlockingQueue();

    private Long expireTime = 2000L;

    TimeOutPriorityQueue(PriorityBlockingQueue<T> queue, Long expireTime){
            this.queue = queue;
            this.expireTime = expireTime;
    }

    TimeOutPriorityQueue(){

    }


    public <T> T put(T e) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InterruptedException {
        Class<?> clazz = e.getClass();

        Method method = clazz.getMethod("setEnQueueTime",Long.class);
        Long now = System.currentTimeMillis();
        method.invoke(e,now);

        this.queue.add(e);

        /*for(int i=0;i<queue.size();i++){
            Object x = queue.take();
            System.out.println("当前对象："+x.toString());
        }
        System.out.println("================================分割线=============================================");*/

        return e;
    }

    public <T> T take(T e){

        return e;
    }


    public T poll() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        T result = null;
        for(int i=0;i<queue.size();i++){

            Object element = queue.poll();

            Class<?> clazz = element.getClass();
            Method m = clazz.getMethod("getEnQueueTime");

            Long timestamp = (Long)m.invoke(element);

            Long now = System.currentTimeMillis();

            Method m2 = clazz.getMethod("getId");

            Integer id = (int)m2.invoke(element);

            if(now-timestamp>=expireTime){
                System.out.println("已过期："+id+"-"+(now-timestamp));
                continue;
            }else{
                result = (T)element;
                break;
            }

        }
        return result;
    }

    public T peek(){

        return null;
    }


    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
