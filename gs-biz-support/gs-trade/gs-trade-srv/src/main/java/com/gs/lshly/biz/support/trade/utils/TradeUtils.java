package com.gs.lshly.biz.support.trade.utils;

import com.lakala.boss.api.common.Common;
import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class TradeUtils implements Serializable {

    public  static String getTradeCode(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return "T" + sf.format(new Date()) + RandomStringUtils.random(6, false, true);
    }

    /**
     * 根据长度生成字母和数字的随机码
     * @return
     */
    public static String getTakeGoodsCode(){
        int  maxNum = 32;
        int i;
        int count = 0;
        char[] str = { 'A','2','B','3','C','4','D','5','F','6','G','7','H','8', 'I','9','J', 'K',
                'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y', 'Z'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < 6){
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }
        return pwd.toString();
    }

    /**
     * 返回当前时间与支付截止时间比较的结果
     * @param createTime
     * @return
     */
    public static boolean checkPayTime(LocalDateTime createTime){
        return LocalDateTime.now().compareTo(createTime.plusMinutes(Common.PAYMENT_TIME_OUT)) >= 0;
    }
}
