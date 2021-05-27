package com.gs.lshly.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.xmlgraphics.util.DateFormatUtil;

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


    public final static String dateFormatStr = "yyyy-MM-dd";

    public final static String timeFormatStr = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_YYYY_MM_DD_PATTERN = "yyyy-MM-dd";


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
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String fomatLocalDateTime(LocalDateTime date,String pattern) {
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
    public static LocalDate getHalfNextYearDate(LocalDate date) {
        date.plusMonths(6L);
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

    public static Date parseDate(String pattern, String dateStr) {

        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * 获取当前时间的前一天时间
     *
     * @param cl
     * @return
     */
    public static String getBeforeDay(int num) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        
        c.add(Calendar.DATE, -num);
        Date start = c.getTime();
        String qyt= format.format(start);//前一天
        return qyt;
    }

    /**
     * 获取当前日期后
     */

    /**
     * 计算两个日期之差
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer betweenStartAndEnd(Date startTime, Date endTime) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(startTime);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endTime);
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        // 不同年
        if (year1 != year2) {
            int timeDistance = 0;
            // 闰年
            for (int i = year1 ; i < year2 ;i++) {
                if (i%4==0 && i%100!=0||i%400==0) {
                    timeDistance += 366;
                // 不是闰年
                }else {
                    timeDistance += 365;
                }
            }
            return  timeDistance + (day2-day1);
        // 同年
        }else{
            return day2-day1;
        }
    }
    
    public static void main(String args[]){
    	System.out.println(getBeforeDay(1));
    	Date startTime = parseDate("yyyy-MM-dd","2021-05-26");
        Date endTime = parseDate("yyyy-MM-dd","2021-05-27");
        System.out.println(betweenStartAndEnd(startTime,endTime));
    }
}
