package com.gs.lshly.rpc.api.bbc.trade;

import java.util.List;

import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO;

/**
 * IN会员优惠券
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午11:46:14
 */
public interface IBbcCouponRpc {

	/**
	 * 查询IN会员列表
	 * @param inCouponType
	 * @return
	 */
	List<BbcCouponVO.InCouponVO> listInCouponByType(Integer inCouponType);
	
}
