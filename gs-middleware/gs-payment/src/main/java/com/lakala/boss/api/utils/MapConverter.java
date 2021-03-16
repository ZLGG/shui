package com.lakala.boss.api.utils;

import com.alibaba.fastjson.JSONArray;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean、Map互转
 * @author: LXF
 * @version: V1.0.0
 * @Project: lkl_boss_sdk
 * @Package: com.lakala.boss.api.utils.converter
 * @Description: Bean、Map互转
 * @date: 2019-10-11 下午6:09:25
 */
public class MapConverter {

	private MapConverter(){}

	/**
	 * Bean转成Map
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> beanToMap(Object bean) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		BeanInfo b = Introspector.getBeanInfo(bean.getClass(), Object.class);
		PropertyDescriptor[] pds = b.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			Method m = pd.getReadMethod();
			Object properValue = m.invoke(bean);
			if(properValue == null){
				continue;
			}
			if(properValue instanceof List){
//				map.put(propertyName, JSONArray.fromObject(properValue).toString());
				map.put(propertyName, JSONArray.toJSON(properValue).toString());
			} else {
				map.put(propertyName, properValue);
			}
		}
		return map;
	}

	/**
	 * Map转成Bean
	 * 
	 * @param map
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> clz)
			throws Exception {
		T obj = clz.newInstance();

		BeanInfo b = Introspector.getBeanInfo(clz, Object.class);
		PropertyDescriptor[] pds = b.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			Method setter = pd.getWriteMethod();
			setter.invoke(obj, map.get(pd.getName()));
		}
		return obj;
	}
}
