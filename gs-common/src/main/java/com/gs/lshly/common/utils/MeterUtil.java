package com.gs.lshly.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MeterUtil {

    static final String CN_M = "米";
    static final String CN_KM = "千米";
    static final String EN_M = "m";
    static final String EN_KM = "km";

    public static String treat(Integer meters) {
        return treat(meters, true);
    }
    public static String treat(Integer meters, boolean english) {
        if (meters == null) {
            return "";
        }
        if (meters <= 1000) {
            return meters + (english ? EN_M : CN_M);
        }
        // 精确到小数点1位
        if (meters > 1000 && meters < 10000) {
            return new BigDecimal(meters + "").divide(new BigDecimal("1000"), 1, RoundingMode.HALF_UP) + (english ? EN_KM : CN_KM);
        }
        // 精确到整数
        return (meters / 1000) + (english ? EN_KM : CN_KM);
    }

    public static void main(String[] args) {
        System.out.println(treat(50));
        System.out.println(treat(150));
        System.out.println(treat(1140));
        System.out.println(treat(1150));
        System.out.println(treat(11500));
    }

}
