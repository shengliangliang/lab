package com.king.test.jdk17;

import java.util.HashSet;
import java.util.Set;

public class Main {

    private int a = 10;
    private boolean c = true;
    private String b = "shengliangliang";

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();

        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            short a = 129 ;
            int b = 9;
            System.out.println(a==b);

            User userA = new User();
            userA.setAge(18);
            userA.setName("Anderson");
            User userB = userA;
            userB.setAge(18);
            userB.setName("Anderson");
            Integer c = 129;
            System.out.println(c.equals(a));
            System.out.println(c==a);
            //System.out.println(userA==userB);
            //System.out.println(userA.equals(userB));

            Set<Integer> set2 = new HashSet<>();
            set2.add(129);
            set2.add(119);
            set2.add((int)a);

            System.out.println(set.toString());
        }

    }
}


class User{
    int age;
    String name ;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}