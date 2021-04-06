package com.gs.lshly.biz.support.trade.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.lakala.boss.api.common.Common;

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
        char[] str = { '0','1','2','3','4','5','6','7','8','9'};
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
