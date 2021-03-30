package com.gs.lshly.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: bmsp-web
 * @Package com.lakala.boss.service.impl
 * @Description: 时间工具类
 * @date Date : 2019年09月16日 15:21
 */
public  class DateUtils {


    private final static String dateFormatStr = "yyyy-MM-dd";

    public final static String timeFormatStr = "yyyy-MM-dd HH:mm:ss";


    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String fomatDate(Date date) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormatStr);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String fomatDate(Date date,String pattern) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取月份第一天起始时间
     * @return
     */
    public static LocalDateTime firstDayOfMonth(Date date){
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            //将小时至0
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            calendar.set(Calendar.MINUTE, 0);
            //将秒至0
            calendar.set(Calendar.SECOND,0);
            //将毫秒至0
            calendar.set(Calendar.MILLISECOND, 0);
            LocalDateTime ldt = Instant.ofEpochMilli( calendar.getTime().getTime() )
                    .atZone( ZoneId.systemDefault() )
                    .toLocalDateTime();
            return ldt;
        }
    }

    /**
     * 获取月份最后一天结束时间
     * @return
     */
    public static LocalDateTime  lastDayOfMonth(Date date){
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            //将小时至0
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至0
            calendar.set(Calendar.MINUTE, 59);
            //将秒至0
            calendar.set(Calendar.SECOND,59);
            //将毫秒至0
            calendar.set(Calendar.MILLISECOND, 999);
            LocalDateTime ldt = Instant.ofEpochMilli( calendar.getTime().getTime() )
                    .atZone( ZoneId.systemDefault() )
                    .toLocalDateTime();
            return ldt;
        }
    }


    /**
     * 获取月份第一天此刻的时间
     * @return
     */
    public static Date addMonth(Date date,int amount){
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(2, amount);
            return c.getTime();
        }
    }

    /**
     * 获取传入日期明年的日期
     * 格式：yyyy-mm-dd
     */
    public static LocalDate getNextYearDate(LocalDate date) {
        return date.minusYears(-1);
    }

    public static LocalDate getNextMonthDate(LocalDate date) {
        return date.minusMonths(-1);
    }
    /**
     * 判断某一日期是否大于当前日期
     * @param date
     * @return
     */
//    public static Boolean isLessNowDate(Date date) {
//        Calendar c = Calendar.getInstance();
//        Date now = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
//        if (date.after(now)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}
