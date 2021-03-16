package com.lakala.boss.api.utils;

import com.lakala.boss.api.common.Common;
import com.lakala.boss.api.security.RSASignUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.utils
 * @Description: caps加签验签工具类
 * @date Date : 2019年12月28日 11:32
 */
@Slf4j
public class CapsSignAndVerifyUtils {

    public static String capsSign(Map<String,Object> mapReq,String certPath){
        RSASignUtil rsaSignUtil = null;
        try {
            rsaSignUtil = new RSASignUtil(certPath,
                    "606258");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        // 剔空排序
        String sf = rsaSignUtil.coverMap2String(mapReq);
        System.out.println("加签原文: " + sf);
        // 签名
        return rsaSignUtil.sign(sf, Common.CHAR_SET_UTF8);
    }

}
