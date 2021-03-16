package com.gs.lshly.biz.support.user.service.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午7:25
 */
public class FupinUtil {

    public static final String DATE_FORMAT_PATTEN = "yyyyMMdd";

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 获取当前日期 yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTEN);
        return dateFormat.format(new Date());
    }
}
