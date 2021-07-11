package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.gs.lshly.biz.support.trade.entity.CouponGoodsRelation;
import com.gs.lshly.biz.support.trade.mapper.CouponMapper;
import com.gs.lshly.biz.support.trade.repository.ICouponGoodsRelationRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcCouponService;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.ListCouponVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO.InCouponVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.DateUtils;

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
	
	@Autowired
	private ICouponGoodsRelationRepository couponGoodsRelationRepository;

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

	@Override
	public List<ListCouponVO> listCouponByGoodsId(String goodsId) {
		List<ListCouponVO> retList = new ArrayList<ListCouponVO>();
		ListCouponVO listCouponVO = null;
		QueryWrapper<CouponGoodsRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level_id",goodsId);
        List<CouponGoodsRelation> list= couponGoodsRelationRepository.list(queryWrapper);
        if(CollectionUtil.isNotEmpty(list)){
        	for(CouponGoodsRelation couponGoodsRelation:list){
        		listCouponVO = new ListCouponVO();
        		String couponId = couponGoodsRelation.getCouponId();
        		Coupon coupon = couponMapper.selectById(couponId);
        		
        		if(!coupon.getAuditStatus().equals(1)||coupon.getCouponStatus().equals(2))
        			continue;
        		
        		if(coupon!=null){
	        		BeanCopyUtils.copyProperties(coupon, listCouponVO);
	        		if(coupon.getCouponType().equals(1)){
	        			if(coupon.getAfterDate()==null||coupon.getAfterDate().equals(0)){
	        				listCouponVO.setUseTime("领取后立即生效，有效天数:"+coupon.getEffectiveDate()+"天");
	        			}else{
	        				listCouponVO.setUseTime("领取后"+coupon.getAfterDate()+"天后生效，有效天数:"+coupon.getEffectiveDate()+"天");
	        			}
	        		}else{
	        			//2021/01/01 2021/08/01
	        			if(coupon.getStartTime()!=null&&coupon.getEndTime()!=null){
	        				listCouponVO.setUseTime(DateUtils.fomatDate(coupon.getStartTime(),DateUtils.dateFormatStr_1)+" "+DateUtils.fomatDate(coupon.getEndTime(),DateUtils.dateFormatStr_1));
	        			}
	        		}
	        		listCouponVO.setDeduction(coupon.getDeductionAmount());
	                listCouponVO.setUseThreshold(coupon.getUseThreshold());
	                listCouponVO.setDeductionType(1);
	                listCouponVO.setId(coupon.getCouponId());
	        		retList.add(listCouponVO);
        		}
        	}
        }
		return retList;
	}
    
    
}
