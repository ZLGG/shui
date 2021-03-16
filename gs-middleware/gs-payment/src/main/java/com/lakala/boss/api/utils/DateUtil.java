package com.lakala.boss.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: bmsp-web
 * @Package com.lakala.boss.service.impl
 * @Description: 时间工具类
 * @date Date : 2019年09月16日 15:21
 */
public class DateUtil {

    public final static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");

    public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat sdf_date_format = new SimpleDateFormat("yyyy-MM-dd");

    public final static SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取当前时间的YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     *
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
//        return fomatDate(s).getTime() >=fomatDate(e).getTime();
        return s.compareTo(e) > 0;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间的后i天
     *
     * @param i
     * @return
     */
    public static String getAddDay(int i) {
        String currentTime = DateUtil.getTime();
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return sdf_date_format.format(gCal.getTime());
    }

    /**
     * 获取当前时间的后i天
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDayTime(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000);
        return sdfTime.format(date);
    }

    /**
     * 获取当前时间的+多少秒
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDaySecond(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 1000);
        return sdfTime.format(date);
    }

    public static String getDate() {
        return sdfDate.format(new Date());
    }

    /**
     * 获取若干天前日期（格式为yyyyMMdd）
     * @param day
     * @return
     */
    public static String getBeforDate(int day){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, - day);

        Date dayOfBefore = c.getTime();

        String dayOfBeforeStr = sdf.format(dayOfBefore);

        return dayOfBeforeStr;

    }

    public static String getNowTime() {
        return nowTime.format(new Date());
    }
}
