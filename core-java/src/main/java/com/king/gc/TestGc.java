package com.king.gc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TestGc {


    public static void createMap() throws InterruptedException {


        for(int i=0;i<100000;i++) {
            Map<String,User> map = new HashMap<>(8);

            User user = new User();
            String name = "zhangsan_"+i;
            user.setName(name);
            user.setAge(i);
            user.setSalary(new BigDecimal(30000));
            map.put(name,user);
            Thread.sleep(100);
            System.out.println("put:"+name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        createMap();
    }


}

class User{
    private String name;
    private int age;
    private BigDecimal salary;

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}