package com.citydo.appraisal.utils;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.MapMaker;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.util.*;

public class BeanCopierUtils {

  public static Map<String, BeanCopier> beanCopierMap = new MapMaker().initialCapacity(32).concurrencyLevel(32).makeMap();

  /**
   * bean 对象copy
   *
   * @param source
   * @param targetClass
   */
  public static  <T> T copyProperties(Object source, Class<T> targetClass) {
    if(source == null){
      return null;
    }

    String beanKey = generateKey(source.getClass(), targetClass);
    BeanCopier copier;
    if (!beanCopierMap.containsKey(beanKey)) {
      copier = BeanCopier.create(source.getClass(), targetClass, false);
      beanCopierMap.put(beanKey, copier);
    } else {
      copier = beanCopierMap.get(beanKey);
    }

    T targetObject;
    try {
      targetObject = targetClass.newInstance();
      copier.copy(source, targetObject, null);
    } catch (Exception e) {
      throw new RuntimeException("拷贝异常");
    }
    return targetObject;
  }

  /**
   * @param source
   * @param targetClass
   * @throws Exception
   */
  public static <T> List<T> copyListProperties(List<?> source, Class<T> targetClass) {

    if (CollectionUtils.isEmpty(source)) {
      return new ArrayList<>();
    }

    List<T> target = new ArrayList<T>();
    for (Object obj : source) {

      T targetObject;
      try {
        targetObject = targetClass.newInstance();
        copyProperties(obj, targetObject);
      } catch (Exception e) {
        throw new RuntimeException("拷贝异常");
      }
      target.add(targetObject);
    }
    return target;
  }

  /**
   * 深度copy 对象内置对象会被copy
   *
   * @param source
   * @param targetClass
   */
  public static <T> T copyDeepPros(Object source, Class<T> targetClass) {
    if(source == null){
      return null;
    }
    return JSON.parseObject(JSON.toJSONString(source), targetClass);
  }

  /**
   * 深度copy 对象内置对象会被copy
   *
   * @param source
   * @param targetClass
   */
  public static <T> List<T> copyDeepListPros(List<?> source, Class<T> targetClass) {
    if (CollectionUtils.isEmpty(source)) {
      return new ArrayList<>();
    }
    return JSON.parseArray(JSON.toJSONString(source), targetClass);
  }

  /**
   * 转换成map
   * @param args
   * @return
   */
  public static Map<String, Object> parseToMap(Object args) {
    return Arrays.stream(BeanUtils.getPropertyDescriptors(args.getClass()))
            .filter(pd -> !"class".equals(pd.getName()))
            .collect(HashMap::new,
                    (map, pd) -> map.put(pd.getName(), ReflectionUtils.invokeMethod(pd.getReadMethod(), args)),
                    HashMap::putAll);
  }

  /**
   * bean 对象copy
   *
   * @param source
   * @param target
   */
  private static void copyProperties(Object source, Object target) {
    if(source == null){
      return;
    }
    String beanKey = generateKey(source.getClass(), target.getClass());
    BeanCopier copier;
    if (!beanCopierMap.containsKey(beanKey)) {
      copier = BeanCopier.create(source.getClass(), target.getClass(), false);
      beanCopierMap.put(beanKey, copier);
    } else {
      copier = beanCopierMap.get(beanKey);
    }
    copier.copy(source, target, null);

  }

  private static String generateKey(Class<?> class1, Class<?> class2) {
    return class1.toString() + class2.toString();
  }

}
