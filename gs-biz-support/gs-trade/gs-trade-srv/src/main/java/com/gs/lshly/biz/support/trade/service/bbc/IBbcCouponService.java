package com.gs.lshly.biz.support.trade.service.bbc;

import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.ListCouponVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO.InCouponVO;

/**]
 * 
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午11:54:21
 */
public interface IBbcCouponService {
    
	List<InCouponVO> listInCouponByType(Integer inCouponType);
	
	
	List<ListCouponVO> listCouponByGoodsId(String goodsId);
    
}
