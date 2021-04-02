package com.pubinfo.uop.config.service.api;

import java.util.HashMap;

/**
 * 统一平台
 *
 * 
 * @author yingjun
 * @date 2021年3月22日 下午7:31:00
 */
public interface UopCustBaseService {
	
	/**
	 * 查询积分值 
	 * @param map
	 * @return
	 */
	HashMap<String,Object> qryPointBalance(HashMap<String,Object> map);
}
