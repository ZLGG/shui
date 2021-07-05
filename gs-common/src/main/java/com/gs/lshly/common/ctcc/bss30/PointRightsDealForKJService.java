package com.gs.lshly.common.ctcc.bss30;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.gs.lshly.common.struct.ctcc.dto.BSS30DTO;
import com.gs.lshly.common.struct.ctcc.vo.BSS30VO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.HttpClientUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 积分扣减服务
 *
 * 
 * @author yingjun
 * @date 2021年7月5日 上午9:45:53
 */
@Slf4j
@SuppressWarnings("unchecked")
public class PointRightsDealForKJService {
    
	public static BSS30VO.PointRightsDealForKJVO pointRightsDealForKJ(BSS30DTO.PointRightsDealForKJDTO dto) {
		BSS30VO.PointRightsDealForKJVO pointRightsDealForKJVO = new BSS30VO.PointRightsDealForKJVO();
		try {
			Map<String, Object> retMap = new HashMap<String,Object>();
			String url = "";
    		String str = HttpClientUtils.get(url);
    		log.info("请求BSS30返回信息==>"+str);
    		Gson gson = new Gson();
    		retMap = gson.fromJson(str, retMap.getClass());
    		pointRightsDealForKJVO = BeanUtils.copy(BSS30VO.PointRightsDealForKJVO.class, retMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return pointRightsDealForKJVO;
    }
}
