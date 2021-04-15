package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsGiveRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityJurisdictionRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityMerchantRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtActivityRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSeckillService;
import com.gs.lshly.common.enums.GoodsPointTypeEnum;
import com.gs.lshly.common.enums.MarketPtSeckillStatusEnum;
import com.gs.lshly.common.enums.MarketPtSeckillTimeQuantumEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillTimeQuantum;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.ListVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.twelvemonkeys.lang.StringUtil;

import cn.hutool.core.collection.CollectionUtil;

@Component
public class BbcMarketSeckillServiceImpl implements IBbcMarketSeckillService {
    
    @Autowired
    private MarketPtSeckillMapper marketPtSeckillMapper;

    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;
    @DubboReference
    private IBbcShopRpc iBbcShopRpc;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;
    
    @Autowired
    private IMarketPtSeckillRepository marketPtSeckillRepository;
    
    @Value("${activity.pc.cut}")
    private String pcCut;
    @Value("${activity.pc.gift}")
    private String pcGift;
    @Value("${activity.pc.groupbuy}")
    private String pcGroupbuy;
    @Value("${activity.pc.discount}")
    private String pcDiscount;

    @Value("${activity.h5.cut}")
    private String h5Cut;
    @Value("${activity.h5.gift}")
    private String h5Gift;
    @Value("${activity.h5.groupbuy}")
    private String h5Groupbuy;
    @Value("${activity.h5.discount}")
    private String h5Discount;
    
	@Override
	public SeckillHome seckillHome(BbcMarketSeckillDTO.DTO dto) {
		//查询今天所有开售数据
		String id = dto.getId();
		String now = DateUtils.fomatDate(new Date(), DateUtils.dateFormatStr);
		QueryWrapper<MarketPtSeckill> wrapper = new QueryWrapper<>();
        wrapper.ge("seckill_start_time", now+" 00:00:00");
        wrapper.le("seckill_start_time", now+" 23:59:59");
        wrapper.orderByAsc("time_quantum");
        List<MarketPtSeckill> nowList = marketPtSeckillRepository.list(wrapper);
        
        String before = DateUtils.getBeforeDay(1);
        wrapper = new QueryWrapper<>();
        wrapper.ge("seckill_start_time", before+" 00:00:00");
        wrapper.le("seckill_start_time", before+" 23:59:59");
        wrapper.orderByDesc("time_quantum");
        MarketPtSeckill beforeSeckill = marketPtSeckillRepository.getOne(wrapper);
        
        
        List<SeckillTimeQuantum> timeQuantum = new ArrayList<SeckillTimeQuantum>();
        SeckillHome seckillHome = new SeckillHome();
        SeckillTimeQuantum seckillTimeQuantum = null;
        if(seckillHome!=null&&beforeSeckill!=null){
        	seckillTimeQuantum = new SeckillTimeQuantum();
        	seckillTimeQuantum.setId(beforeSeckill.getId());
        	seckillTimeQuantum.setName(beforeSeckill.getName());
        	seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.昨日已开抢.getCode());
        	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.昨日已开抢.getRemark());
        	
        	seckillTimeQuantum.setTimeQuantum(EnumUtil.getEnumByCode(beforeSeckill.getTimeQuantum(), MarketPtSeckillTimeQuantumEnum.class).getRemark());
        
        	timeQuantum.add(seckillTimeQuantum);
        }
        /**
         * 10   11
         * 12
         * 18
         * 20
         * 22
         */
        if(CollectionUtil.isNotEmpty(nowList)){
        	for(MarketPtSeckill seckill:nowList){
        		seckillTimeQuantum = new SeckillTimeQuantum();
            	seckillTimeQuantum.setId(seckill.getId());
            	seckillTimeQuantum.setName(seckill.getName());
            	
            	Integer fromTimeQuantumEnum = this.rangeInDefined(nowList);
            	
            	if(seckill.getTimeQuantum().equals(fromTimeQuantumEnum)){
            		seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.抢购中.getCode());
                	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.抢购中.getRemark());
            	}else if(seckill.getTimeQuantum()>fromTimeQuantumEnum){
            		seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.即将开抢.getCode());
                	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.即将开抢.getRemark());
            	}else if(seckill.getTimeQuantum()<fromTimeQuantumEnum){
            		seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.已开抢.getCode());
                	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.已开抢.getRemark());
            	}
            	seckillTimeQuantum.setTimeQuantum(EnumUtil.getEnumByCode(seckill.getTimeQuantum(), MarketPtSeckillTimeQuantumEnum.class).getRemark());
            
            	timeQuantum.add(seckillTimeQuantum);
            	
            	if(seckillTimeQuantum.getStatus().equals(MarketPtSeckillStatusEnum.抢购中.getCode())){
            		seckillHome.setImageUrl(seckill.getImageUrl());
            		seckillHome.setJumpUrl(seckill.getJumpUrl());
            		seckillHome.setName(seckill.getName());
            		seckillHome.setSeckillEndTime(seckill.getSeckillEndTime());
            		 //跟据秒杀id查询
                    if(StringUtils.isEmpty(dto.getId())){
                    	id = seckill.getId();
                    }
            		
            	}
        	}
        }
        seckillHome.setTimeQuantum(timeQuantum);
        
        BbcMarketSeckillQTO.QTO qto = new BbcMarketSeckillQTO.QTO();
        //跟据秒杀id查询
        BeanCopyUtils.copyProperties(dto, qto);
        qto.setPageNum(1);
        qto.setPageSize(20);
        PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods = this.pageSeckillGoods(qto);
        seckillHome.setList(pageSeckillGoods);
		return seckillHome;
	}

	/**
	 * 10抢购中 20 已开抢 30
	 * @param current
	 * @param min
	 * @param max
	 * @return
	 */
	public static Integer rangeInDefined(List<MarketPtSeckill> nowList) {
		Integer minute = Integer.valueOf(DateUtils.fomatDate(new Date(),"HH"));
		Integer from = 0;
		for(MarketPtSeckill marketPtSeckill:nowList){
			boolean flag = Math.max(from, minute) == Math.min(minute, marketPtSeckill.getTimeQuantum());
			if(flag){
				return marketPtSeckill.getTimeQuantum();
			}
			from = marketPtSeckill.getTimeQuantum();
		}
		return from;
	}
	 
	 public static void main(String args[]){
		//System.out.println(rangeInDefined(10,12,18)); 
	 }

	@Override
	public PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods(BbcMarketSeckillQTO.QTO qto) {
		String id = qto.getId();
		
		QueryWrapper<BbcMarketSeckillQTO.QTO> query = MybatisPlusUtil.query();	//查询条件
        query.eq("goods.seckill_id",id);
        IPage<BbcMarketSeckillVO.SeckillGoodsVO> pager = MybatisPlusUtil.pager(qto);//返回结果定义
        IPage<BbcMarketSeckillVO.SeckillGoodsVO> page = marketPtSeckillMapper.pageSeckillGoods(pager, query);
        if(page!=null){
        	List<BbcMarketSeckillVO.SeckillGoodsVO> retList = page.getRecords();
        	if(CollectionUtil.isNotEmpty(retList)){
        		BbcGoodsInfoVO.DetailVO goodsDetail = null;
        		for(BbcMarketSeckillVO.SeckillGoodsVO seckillGoodsVO:retList){
        			String goodsId = seckillGoodsVO.getGoodsId();
        			goodsDetail = iBbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
        			seckillGoodsVO.setGoodsName(goodsDetail.getGoodsName());
        			seckillGoodsVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsDetail.getGoodsImage())) ? "" : getImage(goodsDetail.getGoodsImage()));
        			if(goodsDetail.getIsInMemberGift()){
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.IN会员商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getInMemberPointPrice());
        				seckillGoodsVO.setSeckillPrice(goodsDetail.getInMemberPointPrice());
        			}else if(goodsDetail.getIsPointGood()){
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.积分商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getPointPrice());
        				seckillGoodsVO.setSeckillPrice(goodsDetail.getPointPrice());
        			}else{
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.普通商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getSalePrice());
        				seckillGoodsVO.setSeckillPrice(goodsDetail.getSalePrice());
        			}
        				
        			//TODO yingjun
        			BbcMarketSeckillVO.CouponVO couponVO = new BbcMarketSeckillVO.CouponVO();
        			couponVO.setType(10);
        			couponVO.setName("满100减50");
        			List<BbcMarketSeckillVO.CouponVO> coupons = new ArrayList<BbcMarketSeckillVO.CouponVO>();
        			coupons.add(couponVO);
        			seckillGoodsVO.setCoupons(coupons);
        			seckillGoodsVO.setSaleRate(new BigDecimal("0.9"));
        		}
        	}
        }
        return MybatisPlusUtil.toPageData(qto,BbcMarketSeckillVO.SeckillGoodsVO.class,pager);
	}
	
    private String getImage(String images) {
        if (images != null) {
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)) {
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }
}
