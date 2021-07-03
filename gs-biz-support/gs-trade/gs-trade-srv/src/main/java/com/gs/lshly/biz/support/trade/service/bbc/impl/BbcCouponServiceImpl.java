package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.gs.lshly.biz.support.trade.mapper.CouponMapper;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcCouponService;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO.InCouponVO;
import com.gs.lshly.common.utils.BeanCopyUtils;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午11:53:11
 */
@Component
public class BbcCouponServiceImpl implements IBbcCouponService{
	
	@Autowired
	private CouponMapper couponMapper;

	@Override
	public List<InCouponVO> listInCouponByType(Integer inCouponType) {
		List<Coupon> list = couponMapper.listInCouponByType(inCouponType);
		List<InCouponVO> retList = new ArrayList<InCouponVO>();
		if(CollectionUtil.isNotEmpty(list)){
			InCouponVO inCouponVO= null;
			for(Coupon coupon:list){
				inCouponVO = new InCouponVO();
				BeanCopyUtils.copyProperties(coupon, inCouponVO);
				inCouponVO.setStartTime(LocalDate.now());
				inCouponVO.setEndTime(LocalDate.now().plusDays(coupon.getEffectiveDate()));
				inCouponVO.setMinPrice(coupon.getUseThreshold());
				inCouponVO.setCouponPrice(coupon.getDeductionAmount());
				inCouponVO.setCouponStatus(0);
				retList.add(inCouponVO);
			}
		}
		return retList;
	}
    
    
}
