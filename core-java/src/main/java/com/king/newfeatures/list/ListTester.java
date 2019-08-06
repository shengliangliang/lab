package com.king.newfeatures.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListTester {

    public static void main(String[] args) {
        List<A> listA = new ArrayList<>();

        A a1 = new A();
        a1.setAge(18);
        a1.setName("miracle");
        listA.add(a1);

        A a2 = new A();
        a2.setAge(19);
        a2.setName("jacky");
        listA.add(a2);

        List<B> listB = listA.stream().map(a -> a2b(a)).collect(Collectors.toList());

        listB.stream().forEach(b ->{
            System.out.println(b.getName());
            System.out.println(b.getAge());
        });
    }
    private static B a2b(A a){
        B b = new B();
        b.setName(a.getName());
        b.setAge(a.getAge());
        return b;
    }
}
