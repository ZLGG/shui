package com.gs.lshly.common.utils;


import cn.hutool.core.util.RandomUtil;
import org.springframework.boot.SpringApplication;

import java.util.UUID;

/**
 * @Author Starry
 * @Date 18:07 2020/10/20
 */
public class SettleNoUtil {
    public static String getSettleNo(){
        String date = Long.toString(System.currentTimeMillis());
        String str = UUID.randomUUID().toString().replace("-","");
        String settleNo = "JS"+date+str.substring(9,12);
        return settleNo;
    }

    public static String getShopShareCode(){
        return RandomUtil.randomString(8);
    }
}
