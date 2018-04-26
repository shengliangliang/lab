package com.king.collection.set;

import java.util.EnumSet;

public class TestEnumSet {
    public static void main(String[] args) {
        //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
        EnumSet es1 = EnumSet.allOf(Season.class);
        //输出[SPRING,SUMMER,FALL,WINTER]
        System.out.println(es1);

        //创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。
        EnumSet es2 = EnumSet.noneOf(Season.class);
        //输出[]
        System.out.println(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        //输出[SPRING,WINTER]
        System.out.println(es2);

        //以指定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,WINTER]
        System.out.println(es3);

        EnumSet es4 = EnumSet.range(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,FALL,WINTER]
        System.out.println(es4);

        //新创建的EnumSet集合的元素和es4集合的元素有相同类型，
        //es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值
        EnumSet es5 = EnumSet.complementOf(es4);
        //输出[SPRING]
        System.out.println(es5);
    }
}
enum Season
{
    SPRING,SUMMER,FALL,WINTER
}


/**
 * 以上就是Set集合类的编程应用场景。那么应该怎样选择何时使用这些集合类呢？

 1) HashSet的性能总是比TreeSet好(特别是最常用的添加、查询元素等操作)，因为TreeSet需要额外的红黑树算法来维护集合元素的次序。
    只有当需要一个保持排序的Set时，才应该使用TreeSet，否则都应该使用HashSet
 2) 对于普通的插入、删除操作，LinkedHashSet比HashSet要略慢一点，这是由维护链表所带来的开销造成的。不过，因为有了链表的存在，
    遍历LinkedHashSet会更快
 3) EnumSet是所有Set实现类中性能最好的，但它只能保存同一个枚举类的枚举值作为集合元素
 4) HashSet、TreeSet、EnumSet都是"线程不安全"的，通常可以通过Collections工具类的synchronizedSortedSet方法来"包装"该Set集合。
 SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
 * **/