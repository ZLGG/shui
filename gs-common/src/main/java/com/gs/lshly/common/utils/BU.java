package com.gs.lshly.common.utils;

import com.gs.lshly.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lxus
 * @since 2020/09/14
 */
@Slf4j
public class BU {

    public static Object getValue(Object source, String propertyName) {
        try {
            Method method = source.getClass().getMethod("get" + StringUtils.capitalize(propertyName));
            if (method != null) {
                return method.invoke(source);
            }
            return null;
        } catch (NoSuchMethodException e) {
            log.warn(source.getClass() + "no property[" + propertyName + "] value can get");
            return null;
        } catch (Exception e) {
            throw new BusinessException(source.getClass() + "during get property["+propertyName+"] value.");
        }
    }

    public static void setValue(Object target, String propertyName, Object propertyValue) {
        if(null == propertyValue){
            return;
        }
        try {
            Method method = target.getClass().getMethod("set" + StringUtils.capitalize(propertyName), propertyValue.getClass());
            if (method != null) {
                method.invoke(target, propertyValue);
            }
        } catch (NoSuchMethodException e) {
            log.warn(target.getClass() + "no property[" + propertyName + "] can set value");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(target.getClass() + "during set property["+propertyName+"] value.");
        }
    }

    public static <V,T> List<V> getPropertys(Collection<T> list, String propertyName){
        List<V> ids = new ArrayList<>();
        for (Object obj : list) {
            ids.add((V) BU.getValue(obj, propertyName));
        }
        return ids;
    }

}
