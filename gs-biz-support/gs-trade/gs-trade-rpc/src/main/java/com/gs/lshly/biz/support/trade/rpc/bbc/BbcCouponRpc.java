package com.gs.lshly.biz.support.trade.rpc.bbc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcCouponService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcCtccPtActivityGoodsService;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO.DTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalHomeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO.InCouponVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcCouponRpc;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午11:51:49
 */
@DubboService
public class BbcCouponRpc implements IBbcCouponRpc {
    @Autowired
    private IBbcCouponService bbcCouponService;

	@Override
	public List<InCouponVO> listInCouponByType(Integer inCouponType) {
		return bbcCouponService.listInCouponByType(inCouponType);
	}

    
}
