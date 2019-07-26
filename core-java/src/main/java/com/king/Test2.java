package com.king;

import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();

        map.put("jacky1","hello");
        map.put("jacky2","hello");
        map.put("jacky3","hello");
        map.put("jacky4","hello");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        long end = calendar.getTimeInMillis();
        long now = System.currentTimeMillis();

        int interval = (int) ((end - now) / 1000);

        /**
         * 增加一个随机数，避免大批量key同时过期
         * */
        /*int random = (int) (Math.random()*100);
        if(interval>random){
            interval = interval - random;
        }*/

        System.out.println(interval);
        System.out.println(interval/3600);
    }
}
