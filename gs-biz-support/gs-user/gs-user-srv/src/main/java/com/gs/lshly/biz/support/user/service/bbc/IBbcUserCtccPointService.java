package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO;

import java.util.List;

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
}
