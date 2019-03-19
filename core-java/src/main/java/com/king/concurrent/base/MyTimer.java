package com.king.concurrent.base;

import com.king.concurrent.base.test019.MyPhaser;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {




    public static void main(String[] args){
        Map<Integer,Student> map = new HashMap<Integer,Student>();

        Student s1 = new Student();
        s1.setName("JACKY");
        s1.setAge(18);
        Student s2 = new Student();
        s2.setName("Micrale");
        s2.setAge(19);
        map.put(1,s1);
        map.put(2,s2);

        for(Map.Entry<Integer,Student> entry:map.entrySet()){
            Student student = entry.getValue();
            System.out.println(student.getAge()+":"+student.getName());
        }
        for(Map.Entry<Integer,Student> entry:map.entrySet()){
            Student student = entry.getValue();
            student.setAge(entry.getKey());
        }

        System.out.println("--------------");
        for(Map.Entry<Integer,Student> entry:map.entrySet()){
            Student student = entry.getValue();
            System.out.println(student.getAge()+":"+student.getName());
        }


    }




}

class Student{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}