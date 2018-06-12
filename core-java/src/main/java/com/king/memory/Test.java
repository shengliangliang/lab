package com.king.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


///-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
public class Test {

    static TestList testList2 = new TestList();

    public static void main(String[] args) {

        List<OOMClass> list3 = new ArrayList<>();

        Integer i = 0;
        while (true){
            /*try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            i++;
            if(i<10000){
                if(i%2==1){
                    TestList.put(i++);
                }else{
                    testList2.put2(i++);
                }
            }
            System.out.println("+++++++++++++++++===="+i);

            list3.add(new OOMClass("zhangshan"+i,i));
        }
    }


    static class OOMClass{
        private String name;
        private Integer age;
        OOMClass(String name,Integer age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}

class TestList{
    static List<Integer> list = new ArrayList<>();
    static List<Integer> list2 = new ArrayList<>();
    public static void put(Integer i){
        list.add(i);
    }
    public void put2(Integer i){
        list2.add(i);
    }
}
