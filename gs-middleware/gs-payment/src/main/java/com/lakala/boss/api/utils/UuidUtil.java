package com.lakala.boss.api.utils;

import java.util.UUID;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: boss-sdk
 * @Package com.lakala.boss.api.utils
 * @Description:  UUID
 * @date Date : 2019年10月24日 14:06
 */
public class UuidUtil {

    /**
     * 获取32位UUID
     *
     * @return UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
