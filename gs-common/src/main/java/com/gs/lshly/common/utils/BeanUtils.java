package com.gs.lshly.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午11:07:43
 */
public class BeanUtils {
    public static <P, S extends P> S p2son(P p,Class<S> clazz  ) {
        S s = get(clazz);
        copyProperties(p,s);
        return s;
    }
    public static <P, S extends P> List<S> pList2sonList(final List<P> pList, final Class<S> clazz) {
        return pList.stream().map(p->p2son(p,clazz )).collect(Collectors.toList());
    }
    public static <T> T copy(Class<T> clazz,Object obj){
        T t;
        if(obj!=null){
            if(obj instanceof String){
                return jsonStr2Obj(clazz,(String) obj);
            }
            if(obj instanceof Map){
                return map2Obj(clazz,(Map)obj);
            }
            t = get(clazz);
            copyProperties(obj,t);
        }else{
            t = get(clazz);
        }
        return t;
    }
    private static <T> T map2Obj(Class<T> clazz,Map dataMap){
        return jsonStr2Obj(clazz, JSONObject.toJSONString(dataMap));
    }
    private static <T> T jsonStr2Obj(Class<T> clazz,String jsonStr){
        return JSONObject.parseObject(jsonStr,clazz);
    }

    public static void copyProperties(Object source, Object target){
        org.springframework.beans.BeanUtils.copyProperties(source,target);
    }
    public static <T> List<T> copyList(Class<T> clazz,List<?> list){
        return list.stream().map(l->{
            T t = get(clazz);
            copyProperties(l, t);
            return t;
        }).collect(Collectors.toList());

    }

    /**
     * 反射获取对象
     * @author ChenZC
     * @date 2020/1/8 11:08 AM
     * @param clazz :
     * @return : T
     */
    private static <T> T get(Class<T> clazz)  {
        try {
           return  clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 构建一个随机参数的对象
     * 只支持简单参数类型的
     * 1。基础数据类型
     * 2。单层List
     * 3。单层Set
     * 4。Date
     * 5。BigDecimal
     * 6。支持数组对象及枚举对象 2020-01-15 13:34:00
     * @author ChenZC
     * @date 2020/1/8 10:50 AM
     * @param clazz :
     * @return : T
     */
    public static <T> T builderTemp(Class<T> clazz)  {
        try {
            return builderByIntrospector(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static <T> T builderByIntrospector(Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (Modifier.isFinal(field.getModifiers())){
                continue;
            }
            Object randDataByType = getRandDataByType(field);
            field.set(t, randDataByType);
        }
        return t;

    }

    /**
     * 根据字段 获取 参数的随机值
     * 1。支持List和Set
     * 2。支持基础数据类型 Date BigDecimal
     * Todo 怎么确定字段类型的 List<List<String>>   测试当前方法获取的泛型为String
     * @author ChenZC
     * @date 2020/1/8 11:01 AM
     * @param field :
     * @return : java.lang.Object
     */
    private static  Object getRandDataByType(Field field) throws Exception {
        Class<?> propertyType = field.getType();

        if(List.class.equals(propertyType)){
            List list = new ArrayList<>();
            ParameterizedType listGenericType =(ParameterizedType) field.getGenericType();
            Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
            Type type = listActualTypeArguments[listActualTypeArguments.length - 1];
            Class<?> aClass = BeanUtils.class.getClassLoader().loadClass(type.getTypeName());
            for (int i = 0; i < 2; i++) {
                list.add(getObject(aClass));
            }
            return list;
        }else if(Set.class.equals(propertyType)){
            Set set = new HashSet<>();
            ParameterizedType listGenericType =(ParameterizedType) field.getGenericType();
            Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
            Type type = listActualTypeArguments[listActualTypeArguments.length - 1];
            Class<?> aClass = BeanUtils.class.getClassLoader().loadClass(type.getTypeName());
            for (int i = 0; i < 2; i++) {
                set.add(getObject(aClass));
            }
            return set;
        } else if(propertyType.isEnum()){
            Object[] enumConstants = propertyType.getEnumConstants();
            if(enumConstants!= null && enumConstants.length>0){
                return enumConstants[0];
            }else {
                return null;
            }
        }else if(propertyType.isArray()){
            //数组对象
            Class<?> componentType = propertyType.getComponentType();
            Object[] objs = (Object[])Array.newInstance(componentType, 1);
            objs[0]=getObject(componentType);
            return objs;
        }else {
            return getObject(propertyType);
        }

    }


    /**
     * 根据字段 获取 默认的随机值
     * 1。支持List和Set
     * 2。支持基础数据类型 Date BigDecimal
     * Todo 怎么确定字段类型的 List<List<String>>   测试当前方法获取的泛型为String
     * @author ChenZC
     * @date 2020/1/8 11:01 AM
     * @param field :
     * @return : java.lang.Object
     */
    private static  Object getDefaultDataByType(Field field) throws Exception {
        Class<?> propertyType = field.getType();

        if(List.class.equals(propertyType)){
            List list = new ArrayList<>();
            ParameterizedType listGenericType =(ParameterizedType) field.getGenericType();
            Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
            Type type = listActualTypeArguments[listActualTypeArguments.length - 1];
            Class<?> aClass = BeanUtils.class.getClassLoader().loadClass(type.getTypeName());
            for (int i = 0; i < 2; i++) {
                list.add(getObjectDefault(aClass));
            }
            return list;
        }else if(Set.class.equals(propertyType)){
            Set set = new HashSet<>();
            ParameterizedType listGenericType =(ParameterizedType) field.getGenericType();
            Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
            Type type = listActualTypeArguments[listActualTypeArguments.length - 1];
            Class<?> aClass = BeanUtils.class.getClassLoader().loadClass(type.getTypeName());
            for (int i = 0; i < 2; i++) {
                set.add(getObjectDefault(aClass));
            }
            return set;
        } else if(propertyType.isEnum()){
            Object[] enumConstants = propertyType.getEnumConstants();
            if(enumConstants!= null && enumConstants.length>0){
                return enumConstants[0];
            }else {
                return null;
            }
        }else if(propertyType.isArray()){
            //数组对象
            Class<?> componentType = propertyType.getComponentType();
            Object[] objs = (Object[])Array.newInstance(componentType, 1);
            objs[0]=getObjectDefault(componentType);
            return objs;
        }else {
            return getObjectDefault(propertyType);
        }

    }

    /**
     * 根据类型返回Object 对象的随机值
     * @author ChenZC
     * @date 2020/1/8 11:00 AM
     * @param propertyType :
     * @return : java.lang.Object
     */
    private static Object getObject(Class<?> propertyType) throws Exception {
        if(int.class.equals(propertyType)||Integer.class.equals(propertyType)){
            return new Random().nextInt( 10000);
        }else if(long.class.equals(propertyType)||Long.class.equals(propertyType)){
            return new Random().nextLong();
        }else if(String.class.equals(propertyType)){
            return UUID.randomUUID().toString();
        }else if(char.class.equals(propertyType)||Character.class.equals(propertyType)){
            return 'a';
        }else if(Date.class.equals(propertyType)){
            return new Date();
        }else if(Boolean.class.equals(propertyType)||boolean.class.equals(propertyType)){
            return System.currentTimeMillis() % 2 ==0?Boolean.TRUE:Boolean.FALSE;
        }else if(BigDecimal.class.equals(propertyType)){
            return BigDecimal.valueOf(new Random().nextDouble());
        }else if(Double.class.equals(propertyType)||double.class.equals(propertyType)){
            return new Random().nextDouble();
        }else if(float.class.equals(propertyType)||Float.class.equals(propertyType)){
            return new Random().nextFloat();
        }else {
            return builderByIntrospector(propertyType);
        }
    }
    /**
     * 根据类型返回Object 对象的随机值
     * @author ChenZC
     * @date 2020/1/8 11:00 AM
     * @param propertyType :
     * @return : java.lang.Object
     */
    private static Object getObjectDefault(Class<?> propertyType) throws Exception {
        if(int.class.equals(propertyType)||Integer.class.equals(propertyType)){
            return 0;
        }else if(long.class.equals(propertyType)||Long.class.equals(propertyType)){
            return 0L;
        }else if(String.class.equals(propertyType)){
            return "";
        }else if(char.class.equals(propertyType)||Character.class.equals(propertyType)){
            return '\0';
        }else if(Date.class.equals(propertyType)){
            return new Date();
        }else if(Boolean.class.equals(propertyType)||boolean.class.equals(propertyType)){
            return Boolean.FALSE;
        }else if(BigDecimal.class.equals(propertyType)){
            return BigDecimal.ZERO;
        }else if(Double.class.equals(propertyType)||double.class.equals(propertyType)){
            return 0.0D;
        }else if(float.class.equals(propertyType)||Float.class.equals(propertyType)){
            return 0.0F;
        }else {
            return builderByIntrospector(propertyType);
        }
    }

    public static boolean isNotEmpty(Object val) {
        if(val==null){
            return false;
        }
        if(val instanceof String){
            return !((String) val).isEmpty();
        }else if(val instanceof Collection){
            Collection collection = (Collection) val;
            return !collection.isEmpty();
        }else if(val instanceof Map){
            Map map = (Map) val;
            return !map.isEmpty();
        }else{
            return true;
        }
    }
    public static boolean isEmpty(Object val) {
        return !isNotEmpty(val);
    }


}
