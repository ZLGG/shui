package com.gs.lshly.biz.support.user.service.bbc;

import java.math.BigDecimal;

import com.gs.lshly.common.struct.bbc.user.dto.BbcUserCtccPointDTO.AddCtccPointDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserCtccPointDTO.SubCtccPointDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserIntegralDTO.SubIntergralDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午11:44:31
 */
public interface IBbcUserCtccPointService {
    
	/**
	 * 
	 * @param userId
	 * @return
	 */
	BbcUserCtccPointVO.DetailVO getCtccPointByUserId(String userId);
	
	/**
	 * 减分扣减
	 * @param dto
	 */
    void subCtccPoint(SubCtccPointDTO dto);
    
    /**
     * 添加积分
     * @param dto
     */
    void addCtccPoint(String userId,BigDecimal point);
}
