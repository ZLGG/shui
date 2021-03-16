package com.lakala.boss.api.utils;

import java.util.Random;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: boss-sdk
 * @Package com.lakala.boss.api.utils
 * @Description: 随机密钥生成工具类
 * @date Date : 2019年10月22日 15:44
 */
public class EncryptedRandomKey {

    private EncryptedRandomKey(){}

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    public static String getEncryptedRandomKey() {
        return generateString(16);
    }


}
