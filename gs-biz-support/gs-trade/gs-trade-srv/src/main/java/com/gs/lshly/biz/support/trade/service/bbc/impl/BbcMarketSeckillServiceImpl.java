package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSpu;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillGoodsSpuMapper;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSeckillService;
import com.gs.lshly.common.enums.GoodsPointTypeEnum;
import com.gs.lshly.common.enums.MarketPtSeckillStatusEnum;
import com.gs.lshly.common.enums.MarketPtSeckillTimeQuantumEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.SeckillDetailVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillTimeQuantum;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.HomePageSeckill;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

import cn.hutool.core.collection.CollectionUtil;

@Component
public class BbcMarketSeckillServiceImpl implements IBbcMarketSeckillService {
    
    @Autowired
    private MarketPtSeckillMapper marketPtSeckillMapper;
    
    @Autowired
    private MarketPtSeckillGoodsSpuMapper marketPtSeckillGoodsSpuMapper;
    

    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;
    @DubboReference
    private IBbcShopRpc iBbcShopRpc;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;
    
    @Autowired
    private IMarketPtSeckillRepository marketPtSeckillRepository;
    
    @Autowired
    private IMarketPtSeckillGoodsSpuRepository marketPtSeckillGoodsSpuRepository;
    
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

        //查询昨天秒杀数据
        String before = DateUtils.getBeforeDay(1);
        wrapper = new QueryWrapper<>();
        wrapper.ge("seckill_start_time", before+" 00:00:00");
        wrapper.le("seckill_start_time", before+" 23:59:59");
        wrapper.orderByDesc("time_quantum");
        wrapper.last("limit 1");
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
            	
            	if(seckill.getTimeQuantum().equals(fromTimeQuantumEnum)||seckill.getTimeQuantum().equals(10)){
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
        qto.setId(id);
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
				return from;
			}
			from = marketPtSeckill.getTimeQuantum();
		}
		return from;
	}
	 
	 public static void main(String args[]){
		 
		 List<Integer> list = new ArrayList<Integer>();
		 list.add(10);
		 list.add(12);
		 list.add(18);
		 list.add(20);
		 list.add(22);
		 
		 Integer minute = 10;
			Integer from = 0;
			for(Integer i:list){
				boolean flag = Math.max(from, minute) == Math.min(minute, i);
				if(flag){
					System.out.println(from);
					break;
				}
				from = i;
				System.out.println(from+"<><>");
			}
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
        				seckillGoodsVO.setSeckillId(id);
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.IN会员商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getInMemberPointPrice());
        				seckillGoodsVO.setSeckillPrice(seckillGoodsVO.getSeckillInMemberPointPrice());
        				seckillGoodsVO.setSalePrice(seckillGoodsVO.getSeckillInMemberPointPrice());
        			}else if(goodsDetail.getIsPointGood()){
						seckillGoodsVO.setSeckillId(id);
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.积分商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getPointPrice());
        				seckillGoodsVO.setSeckillPrice(seckillGoodsVO.getSeckillPointPrice());
        				seckillGoodsVO.setSalePrice(seckillGoodsVO.getSeckillPointPrice());
        			}else{
						seckillGoodsVO.setSeckillId(id);
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.普通商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getSalePrice());
        				seckillGoodsVO.setSeckillPrice(seckillGoodsVO.getSalePrice());
        				seckillGoodsVO.setSalePrice(seckillGoodsVO.getSalePrice());
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

	@Override
	public SeckillDetailVO detailGoodsInfo(BbcMarketSeckillQTO.DetailQTO qto) {
		BbcGoodsInfoVO.DetailVO detailVO = iBbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(qto.getGoodsId()));
		BbcGoodsInfoVO.SeckillDetailVO seckillDetailVO = new BbcGoodsInfoVO.SeckillDetailVO();
		
		BeanCopyUtils.copyProperties(detailVO, seckillDetailVO);
		
		//查询对应的秒杀状态
		//跟据产品id查询对应的
		QueryWrapper<MarketPtSeckillGoodsSpu> marketPtSeckillGoodsSpuQueryWrapper = MybatisPlusUtil.query();	//查询条件
		marketPtSeckillGoodsSpuQueryWrapper.eq("goods_id",qto.getGoodsId());
        marketPtSeckillGoodsSpuQueryWrapper.eq("seckill_id",qto.getActivityId());
		MarketPtSeckillGoodsSpu marketPtSeckillGoodsSpu = marketPtSeckillGoodsSpuMapper.selectOne(marketPtSeckillGoodsSpuQueryWrapper);
		seckillDetailVO.setSeckillPrice(marketPtSeckillGoodsSpu.getSeckillPointPrice());
		if(seckillDetailVO.getIsInMemberGift()){
			seckillDetailVO.setGoodsType(GoodsPointTypeEnum.IN会员商品.getCode());
			seckillDetailVO.setOldPrice(seckillDetailVO.getInMemberPointPrice());
			seckillDetailVO.setSeckillPrice(marketPtSeckillGoodsSpu.getSeckillInMemberPointPrice());
		}else if(seckillDetailVO.getIsPointGood()){
			seckillDetailVO.setGoodsType(GoodsPointTypeEnum.积分商品.getCode());
			seckillDetailVO.setOldPrice(seckillDetailVO.getPointPrice());
			seckillDetailVO.setSeckillPrice(marketPtSeckillGoodsSpu.getSeckillPointPrice());
		}else{
			seckillDetailVO.setGoodsType(GoodsPointTypeEnum.普通商品.getCode());
			seckillDetailVO.setOldPrice(seckillDetailVO.getSalePrice());
			seckillDetailVO.setSeckillPrice(marketPtSeckillGoodsSpu.getSeckillSalePrice());
		}
		String seckillId = marketPtSeckillGoodsSpu.getSeckillId();
		MarketPtSeckill marketPtSeckill = marketPtSeckillMapper.selectById(seckillId);
		
		LocalDateTime seckillStartTime = marketPtSeckill.getSeckillStartTime();
		LocalDateTime seckillEndTime = marketPtSeckill.getSeckillEndTime();
		LocalDateTime now = LocalDateTime.now();
		Integer status = 0;
		if(now.compareTo(seckillStartTime)<0){
			status = MarketPtSeckillStatusEnum.即将开抢.getCode();
		}else if(now.compareTo(seckillEndTime)>0){
			status = MarketPtSeckillStatusEnum.已结束.getCode();
		}else if(now.compareTo(seckillStartTime)>=0&&now.compareTo(seckillEndTime)<=0){
			status = MarketPtSeckillStatusEnum.抢购中.getCode();
		}
		seckillDetailVO.setStatus(status);
		seckillDetailVO.setSeckillEndTime(marketPtSeckill.getSeckillEndTime());
		return seckillDetailVO;
	}

	@Override
	public List<HomePageSeckill> homePageSeckill(BaseDTO dto) {
		
		List<HomePageSeckill> retList = new ArrayList<HomePageSeckill>();
		//查询今天所有开售数据
		String now = DateUtils.fomatDate(new Date(), DateUtils.dateFormatStr);
		QueryWrapper<MarketPtSeckill> wrapper = new QueryWrapper<>();
		wrapper.ge("seckill_start_time", now + " 00:00:00");
		wrapper.le("seckill_start_time", now + " 23:59:59");
		wrapper.orderByAsc("time_quantum");
		List<MarketPtSeckill> nowList = marketPtSeckillRepository.list(wrapper);

		// 查询昨天秒杀数据
		String before = DateUtils.getBeforeDay(1);
		wrapper = new QueryWrapper<>();
		wrapper.ge("seckill_start_time", before + " 00:00:00");
		wrapper.le("seckill_start_time", before + " 23:59:59");
		wrapper.orderByDesc("time_quantum");
		wrapper.last("limit 1");
		MarketPtSeckill beforeSeckill = marketPtSeckillRepository.getOne(wrapper);

		SeckillHome seckillHome = new SeckillHome();
		SeckillTimeQuantum seckillTimeQuantum = null;
		HomePageSeckill homePageSeckill = null;
		if (seckillHome != null && beforeSeckill != null) {
			
			homePageSeckill = new HomePageSeckill();
			
			homePageSeckill.setId(beforeSeckill.getId());
			homePageSeckill.setName(beforeSeckill.getLabel());
			homePageSeckill.setStatus(MarketPtSeckillStatusEnum.昨日已开抢.getCode());
			homePageSeckill.setStatusText(MarketPtSeckillStatusEnum.昨日已开抢.getRemark());
			homePageSeckill.setGoodsList(this.listGoodsBySeckillId(beforeSeckill.getId()));

			retList.add(homePageSeckill);
		}

		if (CollectionUtil.isNotEmpty(nowList)) {
			for (MarketPtSeckill seckill : nowList) {
				seckillTimeQuantum = new SeckillTimeQuantum();
				seckillTimeQuantum.setId(seckill.getId());
				seckillTimeQuantum.setName(beforeSeckill.getLabel());

				Integer fromTimeQuantumEnum = this.rangeInDefined(nowList);

				if (seckill.getTimeQuantum().equals(fromTimeQuantumEnum) || seckill.getTimeQuantum().equals(10)) {
					seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.抢购中.getCode());
					seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.抢购中.getRemark());
				} else if (seckill.getTimeQuantum() > fromTimeQuantumEnum) {
					seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.即将开抢.getCode());
					seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.即将开抢.getRemark());
				} else if (seckill.getTimeQuantum() < fromTimeQuantumEnum) {
					seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.已开抢.getCode());
					seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.已开抢.getRemark());
				}
				
				retList.add(homePageSeckill);
			}
		}
		return retList;
	}

	private List<BbcMarketSeckillVO.HomePageSeckillGoods> listGoodsBySeckillId(String seckillId){
		
		QueryWrapper<MarketPtSeckillGoodsSpu> wrapper = new QueryWrapper<>();
		wrapper.eq("seckill_id", seckillId);
		wrapper.orderByAsc("idx");
		wrapper.last("LIMIT 0,4");
		List<MarketPtSeckillGoodsSpu> nowList =marketPtSeckillGoodsSpuRepository.list(wrapper);
		List<BbcMarketSeckillVO.HomePageSeckillGoods> retList = new ArrayList<BbcMarketSeckillVO.HomePageSeckillGoods>();
        if(CollectionUtil.isNotEmpty(nowList)){
        	BbcMarketSeckillVO.HomePageSeckillGoods homePageSeckillGoods = null;
        	BbcGoodsInfoVO.DetailVO goodsDetail = null;
        	for(MarketPtSeckillGoodsSpu spu:nowList){
        		homePageSeckillGoods = new BbcMarketSeckillVO.HomePageSeckillGoods();
        		
        		String goodsId = spu.getGoodsId();
        		goodsDetail = iBbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
        		
        		BeanCopyUtils.copyProperties(goodsDetail, homePageSeckillGoods);
        		homePageSeckillGoods.setGoodsId(goodsDetail.getId());
        		
        		if(goodsDetail.getIsPointGood()){
    				homePageSeckillGoods.setPointPrice(spu.getSeckillPointPrice());
    				homePageSeckillGoods.setOldPointPrice(goodsDetail.getPointPrice());
    			}else{
    				homePageSeckillGoods.setOldPrice(goodsDetail.getSalePrice());
    				homePageSeckillGoods.setSalePrice(spu.getSeckillSalePrice());
    			}
        		
        		retList.add(homePageSeckillGoods);
        	}
        }
        return retList;
	}
}
