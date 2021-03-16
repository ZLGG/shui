package com.gs.lshly.common.utils;


import java.util.UUID;

/**
 * @Author Starry
 * @Date 18:07 2020/10/20
 */
public class GoodsNoUtil {
    public static String getGoodsNo(){
        String date = Long.toString(System.currentTimeMillis());
        String str = UUID.randomUUID().toString().replace("-","");
        String goodsNO = "GS"+date+str.substring(9,12);
        return goodsNO;
    }
}
