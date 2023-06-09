package com.gs.lshly.rpc.api.bbc.user;

import java.math.BigDecimal;

import com.gs.lshly.common.struct.bbc.user.dto.BbcUserCtccPointDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午11:57:52
 */
public interface IBbcUserCtccPointRpc {
    
	
	BbcUserCtccPointVO.DetailVO getCtccPointByUserId(String userId);
	
	/**
	 * 扣减积分
	 * @param dto
	 */
	void subCtccPoint(BbcUserCtccPointDTO.SubCtccPointDTO dto);
	
	/**
	 * 添加积分
	 * @param dto
	 */
	void addCtccPoint(String userId,BigDecimal point);
	
	/**
	 * 创建订单
	 * @param dto
	 */
	void createCtccPoint(BbcUserCtccPointDTO.CreateCtccPointDTO dto);
}
