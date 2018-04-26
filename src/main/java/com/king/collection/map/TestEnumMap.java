package com.king.collection.map;

import java.util.EnumMap;

public class TestEnumMap {
    public static void main(String[] args) {
        //创建一个EnumMap对象，该EnumMap的所有key
        //必须是Season枚举类的枚举值
        EnumMap enumMap = new EnumMap(Season.class);
        enumMap.put(Season.SUMMER , "夏日炎炎");
        enumMap.put(Season.SPRING , "春暖花开");
        System.out.println(enumMap);
    }
}
enum Season
{
    SPRING,SUMMER,FALL,WINTER
}

/**
 * 与创建普通Map有所区别的是，创建EnumMap是必须指定一个枚举类，从而将该EnumMap和指定枚举类关联起来

 以上就是Map集合类的编程应用场景。我们来梳理一下思路

 1) HashMap和Hashtable的效率大致相同，因为它们的实现机制几乎完全一样。但HashMap通常比Hashtable要快一点，因为Hashtable需要额外的线程同步控制
 2) TreeMap通常比HashMap、Hashtable要慢(尤其是在插入、删除key-value对时更慢)，因为TreeMap底层采用红黑树来管理key-value对
 3) 使用TreeMap的一个好处就是： TreeMap中的key-value对总是处于有序状态，无须专门进行排序操作
 * */