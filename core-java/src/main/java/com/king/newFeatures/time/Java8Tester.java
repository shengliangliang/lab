package com.king.newFeatures.time;

import java.time.*;

/***
 * Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。
 *
 * 在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：
 *
 * 非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
 *
 * 设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
 *
 * 时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
 *
 * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
 *
 * Local(本地) − 简化了日期时间的处理，没有时区的问题。
 *
 * Zoned(时区) − 通过制定的时区处理日期时间。
 *
 * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作
 *
 */
public class Java8Tester {
    public static void main(String[] args) {
        Java8Tester java8Tester = new Java8Tester();
        java8Tester.testLocalDateTime();
        java8Tester.testZonedDateTime();
    }

    private void testLocalDateTime(){
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        System.out.println(localTime);
        System.out.println(localDate);


        Month month = localDate.getMonth();
        System.out.println(month.getValue());


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime3 = localDateTime.withDayOfMonth(10).withYear(2011);
        System.out.println(localDateTime3);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);


        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        LocalDateTime localDateTime1 = LocalDateTime.of(2012,12,23,23,23,23);
        System.out.println(localDateTime1);


        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        //不能用空格,日期和时间需要用T隔开
        LocalDateTime localDateTime2 = LocalDateTime.parse("2015-12-12T14:56:32");
        System.out.println(localDateTime2.toString());
        System.out.println(localDateTime2.getYear());
    }

    public void testZonedDateTime(){

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
