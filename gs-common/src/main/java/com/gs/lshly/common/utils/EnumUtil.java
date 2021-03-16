package com.gs.lshly.common.utils;

import com.gs.lshly.common.enums.EnumMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxus
 */
@Slf4j
public class EnumUtil {

    private static Map<Class, Map<Integer, EnumMessage>> enumMap = new HashMap<>();


    public static <T extends EnumMessage> String getText(Integer code, Class<T> clazz) {
        EnumMessage enums = getEnumByCode(code, clazz);
        return enums != null ? enums.getRemark() : null;
    }

    /**
     * 通过枚举code 获取枚举值
     *
     * @param code  枚举类的code属性值
     * @param clazz 实现EnumMessage接口的枚举类
     * @param <T>
     * @return 枚举值
     */
    public static <T extends EnumMessage> T getEnumByCode(Integer code, Class<T> clazz) {
        Map<Integer, EnumMessage> map = enumMap.get(clazz);
        if (map == null) {
            try {
                map = new HashMap<>(16);
                Method method = clazz.getMethod("values");
                EnumMessage[] enums = (EnumMessage[]) method.invoke(null);
                for (EnumMessage message : enums) {
                    map.put(message.getCode(), message);
                }
                enumMap.put(clazz, map);
            } catch (Exception e) {
                log.error("Getting enum by code unsuccessfully, error:{}", e);
            }
        }
        return map == null ? null : (T) map.get(code);
    }

    public static <T extends EnumMessage> boolean checkEnumCode(Integer code, Class<T> clazz) {
        boolean bool = false;
        if (null != code) {
            try {
                Object[] objects = clazz.getEnumConstants();
                Method funCode = clazz.getMethod("getCode");
                for (Object obj : objects) {
                    if (funCode.invoke(obj) == code) {
                        bool = true;
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }
        return bool;
    }

    public static <T extends EnumMessage> boolean checkEnumCodeWithExcludes(Integer code, Class<T> clazz,Integer... excludeCode) {
        boolean bool = false;
        if (null != code) {
            if(excludeCode.length >  0){
                boolean excludeCodeBoll = true;
                for(int i = 0;i<excludeCode.length;i++){
                    if(code.equals(excludeCode[i])){
                        excludeCodeBoll = false;
                    }
                }
                if(excludeCodeBoll==false){
                    return excludeCodeBoll;
                }
            }
            try {
                Object[] objects = clazz.getEnumConstants();
                Method funCode = clazz.getMethod("getCode");
                for (Object obj : objects) {
                    if (funCode.invoke(obj) == code) {
                        bool = true;
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }
        return bool;
    }
}

   