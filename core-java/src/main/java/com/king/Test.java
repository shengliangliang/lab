package com.king;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Test {
    private static final long  DAY_MILL_SECONDS= 1000 * 3600 * 24;
    private static final int  OFFLINE_DAYS = 30;
    public static int offlineIntervalDays(Date publishTime){
        if(publishTime==null){
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(publishTime);
        calendar.add(Calendar.DAY_OF_YEAR,OFFLINE_DAYS);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        long realOfflineTime = calendar.getTimeInMillis();

        //只有在下线当天的时候在通过实际下线时间计算
        long now = System.currentTimeMillis();
        if(now<realOfflineTime){
            long difference = realOfflineTime - now;
            long intervalDays = difference / DAY_MILL_SECONDS;
            System.out.println("intervalDays:"+intervalDays);
            return (int)intervalDays;
        }
        return -1;
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = "2019-04-29 14:35:07";
        Date date1 = null;
        Date date2 = new Date();
        int[] a = new int[]{1,2,5,5,6,7,8};
        int max = Integer.MIN_VALUE;

        Integer[] k = new Integer[]{1,2,5,5,6,7,8};
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //offlineIntervalDays(date1);
        System.out.println(offlineIntervalDays(date1));
    }
}
