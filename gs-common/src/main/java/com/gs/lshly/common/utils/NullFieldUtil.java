package com.gs.lshly.common.utils;

import java.util.Arrays;

/**
 * 获取对象空字段
 */
public final class NullFieldUtil {
    /**返回对象中的null对象*/
    public static <T> String[] getNullFieldName(T obj) {
        Class<?> aClass = obj.getClass();
        Object[] fieldNames =
                Arrays.stream(aClass.getDeclaredFields())
                .filter(e -> {
                    try {
                        e.setAccessible(true);
                        return e.get(obj) == null;
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                    return false;
                })
                .map(e -> e.getName())
                .toArray();

        String[] names = new String[fieldNames.length];
        for (int i=0;i<fieldNames.length;i++) {
            names[i] = (String)fieldNames[i] ;
        }
        return names;
    }
}
