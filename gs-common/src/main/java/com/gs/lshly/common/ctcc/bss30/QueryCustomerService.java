package com.gs.lshly.common.ctcc.bss30;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.gs.lshly.common.struct.ctcc.vo.BSS30VO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.HttpClientUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 查询客户详情
 * 
 * @author yingjun
 * @date 2020年6月22日 下午2:59:05
 */
@Slf4j
@SuppressWarnings("unchecked")
public class QueryCustomerService {
    
	public static BSS30VO.QueryCustomerVO queryCustomer(String phone) {
		BSS30VO.QueryCustomerVO queryCustomerVO=new BSS30VO.QueryCustomerVO();
		try {
			Map<String, Object> retMap = new HashMap<String,Object>();
			String url = "";
    		String str = HttpClientUtils.get(url);
    		log.info("请求B2I返回信息==>"+str);
    		Gson gson = new Gson();
    		retMap = gson.fromJson(str, retMap.getClass());
    		queryCustomerVO = BeanUtils.copy(BSS30VO.QueryCustomerVO.class, retMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return queryCustomerVO;
    }
}
