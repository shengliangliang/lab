package com.king.test.jdk17.copy;

import java.util.ArrayList;
import java.util.List;

public class ShallowCopy {


    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(16);
        list.add(1);
        list.add(2);

        List<Integer> listCopy = (ArrayList<Integer>)list.clone();

        list.add(3);

        System.out.println(list);
        System.out.println(listCopy);
        System.out.println("----------------------------");

        A a = new A();
        a.setName("a");
        a.setB(new B("zhangsan"));

        A a1;
        try {
            a1 = (A) a.clone();
            a1.setName("a1");
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(a1.toString());
        a.getB().setName("lisi");
        System.out.println(a.toString());
        System.out.println(a1.toString());

    }
}

class A implements Cloneable{
    private String name;
    private B b;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", b=" + b +
                '}';
    }
}

class B{
    private String name;

    B(String name){
        this.name  = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                '}';
    }
}