package com.king.newfeatures.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/***
 * Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 * Stream API可以极大提高Java程序员的生产力，让程序员写出高效率、干净、简洁的代码。
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 * 元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 *
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 * 在 Java 8 中, 集合接口有两个方法来生成流：
 * stream() − 为集合创建串行流。
 * parallelStream() − 为集合创建并行流。
 *
 */
public class StreamTester {

    //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
    private void testMap(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());

        squaresList.forEach(a-> System.out.println(a));
    }

    private void testFilter(){
        //filter 方法用于通过设置的条件过滤出元素。
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(a-> System.out.println(a));

        System.out.println("---------------------------------");
        List<String> strings2 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        strings.stream().filter(string -> !string.isEmpty());
        strings2.forEach(a-> System.out.println(a));

        //统计为空的字符串有几个
        System.out.println(strings.stream().filter(string -> string.isEmpty()).count());
    }

    private void testSorted(){
        //sorted 方法用于对流进行排序。
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    /****
     * Collectors
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
     */
    private void testCollect(){
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    private void testLimit(){
        //limit 方法用于获取指定数量的流。
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    private void testParallel(){
        //parallelStream 是流并行处理程序的代替方法。
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        int count = (int) strings.parallelStream().filter(string -> string.isEmpty()).count();

        System.out.println(count);
    }

    private void testIntSummaryStatistics(){
        //一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。

        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
    public static void main(String[] args) {
        StreamTester streamTester = new StreamTester();
        streamTester.testIntSummaryStatistics();

    }

}
