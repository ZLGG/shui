package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckill;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSku;
import com.gs.lshly.biz.support.trade.entity.MarketPtSeckillGoodsSpu;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillGoodsSpuMapper;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillMapper;
import com.gs.lshly.biz.support.trade.mapper.MarketPtSeckillTimeQuantumMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSkuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillGoodsSpuRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketPtSeckillRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSeckillService;
import com.gs.lshly.common.enums.GoodsPointTypeEnum;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.MarketPtSeckillStatusEnum;
import com.gs.lshly.common.enums.MarketPtSeckillTimeQuantumEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.SeckillDetailVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO.DTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO.SeckillSkuDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO.DetailQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketSeckillQTO.QTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillHome;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO.SeckillTimeQuantum;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.HomePageSeckill;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.ListSkuVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO.SeckillGoodsVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

import cn.hutool.core.collection.CollectionUtil;

@SuppressWarnings({"static-access","unchecked"})
@Component
public class BbcMarketSeckillServiceImpl implements IBbcMarketSeckillService {
    
    @Autowired
    private MarketPtSeckillMapper marketPtSeckillMapper;
    
    @Autowired
    private MarketPtSeckillGoodsSpuMapper marketPtSeckillGoodsSpuMapper;
    
    @Autowired
    private MarketPtSeckillTimeQuantumMapper marketPtSeckillTimeQuantumMapper;
    
    @Autowired
    private IMarketPtSeckillGoodsSkuRepository marketPtSeckillGoodsSkuRepository;
    
    @Autowired
    private IMarketPtSeckillRepository marketPtSeckillRepository;
    
    @Autowired
    private IMarketPtSeckillGoodsSpuRepository marketPtSeckillGoodsSpuRepository;
    
    @DubboReference
    private IBbcGoodsInfoRpc iBbcGoodsInfoRpc;
    @DubboReference
    private IBbcShopRpc iBbcShopRpc;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;
    
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    
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
//        			BbcMarketSeckillVO.CouponVO couponVO = new BbcMarketSeckillVO.CouponVO();
//        			couponVO.setType(10);
//        			couponVO.setName("满100减50");
//        			List<BbcMarketSeckillVO.CouponVO> coupons = new ArrayList<BbcMarketSeckillVO.CouponVO>();
//        			coupons.add(couponVO);
//        			seckillGoodsVO.setCoupons(coupons);
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
        marketPtSeckillGoodsSpuQueryWrapper.eq("time_quantum_id",qto.getActivityId());
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
				homePageSeckill = new HomePageSeckill();
				homePageSeckill.setId(seckill.getId());
				homePageSeckill.setName(seckill.getLabel());

				Integer fromTimeQuantumEnum = this.rangeInDefined(nowList);

				if (seckill.getTimeQuantum().equals(fromTimeQuantumEnum) || seckill.getTimeQuantum().equals(10)) {
					homePageSeckill.setStatus(MarketPtSeckillStatusEnum.抢购中.getCode());
					homePageSeckill.setStatusText(MarketPtSeckillStatusEnum.抢购中.getRemark());
					homePageSeckill.setChecked(true);
				}  if (seckill.getTimeQuantum() > fromTimeQuantumEnum) {
					homePageSeckill.setStatus(MarketPtSeckillStatusEnum.即将开抢.getCode());
					homePageSeckill.setStatusText(MarketPtSeckillStatusEnum.即将开抢.getRemark());
					homePageSeckill.setChecked(false);
				}  if (seckill.getTimeQuantum() < fromTimeQuantumEnum) {
					homePageSeckill.setStatus(MarketPtSeckillStatusEnum.已开抢.getCode());
					homePageSeckill.setStatusText(MarketPtSeckillStatusEnum.已开抢.getRemark());
					homePageSeckill.setChecked(false);
				}
				homePageSeckill.setGoodsList(this.listGoodsBySeckillId(seckill.getId()));
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
        		if(!goodsDetail.getGoodsState().equals(20))
        			continue;
        		
        		BeanCopyUtils.copyProperties(goodsDetail, homePageSeckillGoods);
        		homePageSeckillGoods.setGoodsId(goodsDetail.getId());
        		
        		if(goodsDetail.getIsPointGood()){
    				homePageSeckillGoods.setPointPrice(spu.getSeckillPointPrice());
    				homePageSeckillGoods.setOldPointPrice(goodsDetail.getPointPrice());
    			}else{
    				homePageSeckillGoods.setOldPrice(goodsDetail.getSalePrice());
    				homePageSeckillGoods.setSalePrice(spu.getSeckillSalePrice());
    			}
        		homePageSeckillGoods.setGoodsImage(ObjectUtils.isEmpty(getImage(homePageSeckillGoods.getGoodsImage())) ?"": getImage(homePageSeckillGoods.getGoodsImage()));
        		homePageSeckillGoods.setSaleRate(random());
        		retList.add(homePageSeckillGoods);
        	}
        }
        return retList;
	}
	
	
	private static BigDecimal random(){
		BigDecimal max = new BigDecimal("100");
		BigDecimal min = new BigDecimal("0");
		float minF = min.floatValue();
		float maxF = max.floatValue();
		BigDecimal db = new BigDecimal(Math.random() * (maxF - minF) + minF);
		return db.divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 
	 */
	@Override
	public BbcMarketSeckillVO.SeckillIngVO seckillIng() {
		//查询今天所有开售数据
//		String id = dto.getId();
		BbcMarketSeckillVO.SeckillIngVO seckillIngVO = new BbcMarketSeckillVO.SeckillIngVO();
		List<BbcMarketSeckillVO.SeckillGoodsVO> list = new ArrayList<BbcMarketSeckillVO.SeckillGoodsVO>();
		String now = DateUtils.fomatDate(new Date(), DateUtils.dateFormatStr);
		List<BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO> nowList = marketPtSeckillTimeQuantumMapper.list(now+" 00:00:00", now+" 23:59:59");
		if (CollectionUtil.isNotEmpty(nowList)) {	//今天有秒杀活动
			//当前秒杀活动
			Integer hh = nowSeckill(nowList);
			if(hh!=null){
				for (BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO marketPtSeckillTimeQuantumVO: nowList) {
					//判断当前时间有哪个秒杀活动正在进行中
					if(Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh()).equals(hh)){
						
						seckillIngVO.setSeckillEndTime(marketPtSeckillTimeQuantumVO.getEndTime());
						
						String seckillId = marketPtSeckillTimeQuantumVO.getSeckillId();
						String timeQuantumId = marketPtSeckillTimeQuantumVO.getId();
						
						//查询哪些商品参于了秒杀
						QueryWrapper<MarketPtSeckillGoodsSpu> wrapper = new QueryWrapper<>();
						wrapper.eq("seckill_id", seckillId);
						wrapper.eq("time_quantum_id", timeQuantumId);
						wrapper.eq("choose", 10);
//						wrapper.last("limit 0,4");
						List<MarketPtSeckillGoodsSpu> spuList = marketPtSeckillGoodsSpuRepository.list(wrapper);
						if(CollectionUtils.isNotEmpty(spuList)){
							BbcGoodsInfoVO.SimpleListVO simpleGooods=null;
							
							BbcMarketSeckillVO.SeckillGoodsVO sckillGoodsVO = null;
							MarketPtSeckillGoodsSku sku = null;
							for(MarketPtSeckillGoodsSpu spu:spuList){
								
								sckillGoodsVO = new BbcMarketSeckillVO.SeckillGoodsVO();
								String goodsId = spu.getGoodsId();
								
								//查询SKU
								QueryWrapper<MarketPtSeckillGoodsSku> skuQuery = MybatisPlusUtil.query();
			        			skuQuery.eq("goods_spu_item_id",spu.getId());
			        			skuQuery.eq("goods_id",goodsId);
			        			skuQuery.last("limit 0,1");
			        			sku = marketPtSeckillGoodsSkuRepository.getOne(skuQuery);
								
								simpleGooods = iBbcGoodsInfoRpc.simpleListVO(new BbcGoodsInfoDTO.IdDTO(goodsId));
								if(!simpleGooods.getGoodsState().equals(GoodsStateEnum.已上架.getCode()))
									continue;
								sckillGoodsVO.setSeckillId(timeQuantumId);
								if(simpleGooods.getIsPointGood()){//是不是积分
									sckillGoodsVO.setSalePrice(simpleGooods.getPointPrice());
									sckillGoodsVO.setSeckillPointPrice(sku.getSeckillSaleSkuPrice());
									sckillGoodsVO.setSeckillInMemberPointPrice(sku.getSeckillSaleSkuPrice());
									
									sckillGoodsVO.setGoodsType(20);
								}else{
									sckillGoodsVO.setGoodsType(10);
									sckillGoodsVO.setSalePrice(simpleGooods.getSalePrice());
									sckillGoodsVO.setSeckillPrice(sku.getSeckillSaleSkuPrice());
									sckillGoodsVO.setSeckillInMemberPointPrice(sku.getSeckillSaleSkuPrice());
								}
								
								sckillGoodsVO.setGoodsId(simpleGooods.getId());
								sckillGoodsVO.setGoodsImage(ObjectUtils.isEmpty(getImage(simpleGooods.getGoodsImage())) ? "{}" : getImage(simpleGooods.getGoodsImage()));
								sckillGoodsVO.setOldPrice(simpleGooods.getOldPrice());
								
								sckillGoodsVO.setGoodsName(simpleGooods.getGoodsName());
								
								
								//跟据商品ID+秒杀ID查询对应的秒杀数量
								sckillGoodsVO.setSaleRate(random());
								
								list.add(sckillGoodsVO);
							}
						}
						
						
					}
					
				}
			}
			
			
		}
		seckillIngVO.setList(list);
		return seckillIngVO;
	}
	
	public static Integer nowSeckill(List<BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO> list) {
		Integer minute = Integer.valueOf(DateUtils.fomatDate(new Date(),"HH"));
		Integer from = 0;
		if(CollectionUtils.isNotEmpty(list)){
			for (BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO marketPtSeckillTimeQuantumVO: list) {
				if(minute.equals(Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh()))){
					return minute;
				}
				boolean flag = Math.max(from, minute) == Math.min(minute, Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh()));
				if(flag){
					return from;
				}
				from = Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh());
			}
		}
		return from;
	}
	
	 public static void main(String args[]){
		 System.out.println(Integer.valueOf(DateUtils.fomatDate(new Date(),"HH")));
		 
		 List<Integer> list = new ArrayList<Integer>();
		 list.add(10);
		 list.add(12);
		 list.add(18);
		 list.add(20);
		 list.add(22);
		 
		 Integer minute = 11;
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
	public SeckillHome seckillHomeNew(DTO dto) {
		String now = DateUtils.fomatDate(new Date(), DateUtils.dateFormatStr);
		List<BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO> nowList = marketPtSeckillTimeQuantumMapper.list(now+" 00:00:00", now+" 23:59:59");
		List<SeckillTimeQuantum> timeQuantum = new ArrayList<SeckillTimeQuantum>();	//秒杀时间段
		SeckillHome seckillHome = new SeckillHome();
        SeckillTimeQuantum seckillTimeQuantum = null;
        String id = dto.getId();
		if (CollectionUtil.isNotEmpty(nowList)) {	//今天有秒杀活动
			//当前秒杀活动
			Integer hh = nowSeckill(nowList);
			for (BbcMarketSeckillVO.MarketPtSeckillTimeQuantumVO marketPtSeckillTimeQuantumVO: nowList) {
				seckillTimeQuantum = new SeckillTimeQuantum();
        		
				seckillTimeQuantum.setId(marketPtSeckillTimeQuantumVO.getId());
				seckillTimeQuantum.setTimeQuantum(marketPtSeckillTimeQuantumVO.getHh()+":00");
				//判断当前时间有哪个秒杀活动正在进行中
				
				if(Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh())<hh){
					seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.已开抢.getCode());
	            	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.已开抢.getRemark());
				}else if(Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh()).equals(hh)){
					seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.抢购中.getCode());
	            	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.抢购中.getRemark());
	            	
	            	//查询对应的图片
	            	String seckillId = marketPtSeckillTimeQuantumVO.getSeckillId();
	            	MarketPtSeckill marketPtSeckill = marketPtSeckillMapper.selectById(seckillId);
	            	if(marketPtSeckill!=null){
	            		seckillHome.setName(marketPtSeckill.getName());
	            		
	            		seckillHome.setImageUrl(marketPtSeckill.getImageUrl());
	            		seckillHome.setJumpUrl(marketPtSeckill.getJumpUrl());
	            		seckillHome.setName(marketPtSeckill.getName());
	            		seckillHome.setSeckillEndTime(marketPtSeckill.getSeckillEndTime());
	            		 //跟据秒杀id查询
	                    if(StringUtils.isEmpty(dto.getId())){
	                    	id = marketPtSeckillTimeQuantumVO.getId();
	                    }
	            	}
	            	
				}else if(Integer.valueOf(marketPtSeckillTimeQuantumVO.getHh())>hh){
					seckillTimeQuantum.setStatus(MarketPtSeckillStatusEnum.即将开抢.getCode());
	            	seckillTimeQuantum.setStatusDesc(MarketPtSeckillStatusEnum.即将开抢.getRemark());
				}
				
				timeQuantum.add(seckillTimeQuantum);
			}
		}
		seckillHome.setTimeQuantum(timeQuantum);
        
        BbcMarketSeckillQTO.QTO qto = new BbcMarketSeckillQTO.QTO();
        //跟据秒杀id查询
        BeanCopyUtils.copyProperties(dto, qto);
        qto.setPageNum(1);
        qto.setPageSize(20);
        qto.setId(id);
        PageData<BbcMarketSeckillVO.SeckillGoodsVO> pageSeckillGoods = this.pageSeckillGoodsNew(qto);
        seckillHome.setList(pageSeckillGoods);
		return seckillHome;
	}

	@Override
	public PageData<SeckillGoodsVO> pageSeckillGoodsNew(QTO qto) {
		String id = qto.getId();
		
		QueryWrapper<BbcMarketSeckillQTO.QTO> query = MybatisPlusUtil.query();	//查询条件
        query.eq("goods.time_quantum_id",id);
        IPage<BbcMarketSeckillVO.SeckillGoodsVO> pager = MybatisPlusUtil.pager(qto);//返回结果定义
        IPage<BbcMarketSeckillVO.SeckillGoodsVO> page = marketPtSeckillMapper.pageSeckillGoodsNew(pager, query);
        if(page!=null){
        	List<BbcMarketSeckillVO.SeckillGoodsVO> retList = page.getRecords();
        	if(CollectionUtil.isNotEmpty(retList)){
        		BbcGoodsInfoVO.DetailVO goodsDetail = null;
        			//查询条件
        		MarketPtSeckillGoodsSku sku = null;
        		for(BbcMarketSeckillVO.SeckillGoodsVO seckillGoodsVO:retList){
        			String itemId = seckillGoodsVO.getItemId();
        			String goodsId = seckillGoodsVO.getGoodsId();
        			QueryWrapper<MarketPtSeckillGoodsSku> skuQuery = MybatisPlusUtil.query();
        			skuQuery.eq("goods_spu_item_id",itemId);
        			skuQuery.eq("goods_id",goodsId);
        			skuQuery.last("limit 0,1");
        			sku = marketPtSeckillGoodsSkuRepository.getOne(skuQuery);
        			
        			
        			goodsDetail = iBbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
        			seckillGoodsVO.setGoodsName(goodsDetail.getGoodsName());
        			seckillGoodsVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsDetail.getGoodsImage())) ? "" : getImage(goodsDetail.getGoodsImage()));
        			if(goodsDetail.getIsInMemberGift()){
        				seckillGoodsVO.setSeckillId(id);
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.IN会员商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getInMemberPointPrice());//原价
        				if(sku!=null){
        					seckillGoodsVO.setSalePrice(sku.getSeckillSaleSkuPrice());//
        				}else{
        					seckillGoodsVO.setSalePrice(BigDecimal.ZERO);
        				}
        			}else if(goodsDetail.getIsPointGood()){
						seckillGoodsVO.setSeckillId(id);
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.积分商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getPointPrice());
        				if(sku!=null){
        					seckillGoodsVO.setSalePrice(sku.getSeckillSaleSkuPrice());
        				}else{
        					seckillGoodsVO.setSalePrice(BigDecimal.ZERO);
        				}
        			}else{
						seckillGoodsVO.setSeckillId(id);
        				seckillGoodsVO.setGoodsType(GoodsPointTypeEnum.普通商品.getCode());
        				seckillGoodsVO.setOldPrice(goodsDetail.getSalePrice());
        				if(sku!=null){
        					seckillGoodsVO.setSalePrice(sku.getSeckillSaleSkuPrice());
        				}else{
        					seckillGoodsVO.setSalePrice(BigDecimal.ZERO);
        				}
        			}
        				
        			/***
        			 * TODO yingjun
        			BbcMarketSeckillVO.CouponVO couponVO = new BbcMarketSeckillVO.CouponVO();
        			couponVO.setType(10);
        			couponVO.setName("满100减50");
        			List<BbcMarketSeckillVO.CouponVO> coupons = new ArrayList<BbcMarketSeckillVO.CouponVO>();
        			coupons.add(couponVO);
        			seckillGoodsVO.setCoupons(coupons);
					 */
        			seckillGoodsVO.setTags(goodsDetail.getTags());
        			seckillGoodsVO.setSaleRate(new BigDecimal("0.9"));
        		}
        	}
        }
        return MybatisPlusUtil.toPageData(qto,BbcMarketSeckillVO.SeckillGoodsVO.class,pager);
	}

	@Override
	public SeckillDetailVO detailGoodsInfoNew(DetailQTO qto) {
		
		
		
		//查询对应的秒杀状态
		//跟据产品id查询对应的
		QueryWrapper<MarketPtSeckillGoodsSpu> marketPtSeckillGoodsSpuQueryWrapper = MybatisPlusUtil.query();	//查询条件
		marketPtSeckillGoodsSpuQueryWrapper.eq("goods_id",qto.getGoodsId());
        marketPtSeckillGoodsSpuQueryWrapper.eq("time_quantum_id",qto.getActivityId());
		MarketPtSeckillGoodsSpu marketPtSeckillGoodsSpu = marketPtSeckillGoodsSpuMapper.selectOne(marketPtSeckillGoodsSpuQueryWrapper);
		
		BbcGoodsInfoVO.DetailVO detailVO = iBbcGoodsInfoRpc.detailSeckillGoodsInfo(new BbcGoodsInfoDTO.SeckillIdDTO(qto.getGoodsId(),marketPtSeckillGoodsSpu.getId()));
		BbcGoodsInfoVO.SeckillDetailVO seckillDetailVO = new BbcGoodsInfoVO.SeckillDetailVO();
		
		BeanCopyUtils.copyProperties(detailVO, seckillDetailVO);
		
		QueryWrapper<MarketPtSeckillGoodsSku> skuQuery = MybatisPlusUtil.query();
		skuQuery.eq("goods_spu_item_id",marketPtSeckillGoodsSpu.getId());
		skuQuery.eq("goods_id",qto.getGoodsId());
		skuQuery.last("limit 0,1");
		MarketPtSeckillGoodsSku sku = marketPtSeckillGoodsSkuRepository.getOne(skuQuery);
		
		seckillDetailVO.setSeckillPrice(sku.getSeckillSaleSkuPrice());
		seckillDetailVO.setSalePrice(sku.getSeckillSaleSkuPrice());
		if(seckillDetailVO.getIsInMemberGift()){
			seckillDetailVO.setGoodsType(GoodsPointTypeEnum.IN会员商品.getCode());
			seckillDetailVO.setOldPrice(seckillDetailVO.getInMemberPointPrice());
		}else if(seckillDetailVO.getIsPointGood()){
			seckillDetailVO.setGoodsType(GoodsPointTypeEnum.积分商品.getCode());
			seckillDetailVO.setOldPrice(seckillDetailVO.getPointPrice());
		}else{
			seckillDetailVO.setGoodsType(GoodsPointTypeEnum.普通商品.getCode());
			seckillDetailVO.setOldPrice(seckillDetailVO.getSalePrice());
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
		seckillDetailVO.setStatusText(MarketPtSeckillStatusEnum.getRemarkByCode(status));
		seckillDetailVO.setSeckillEndTime(marketPtSeckill.getSeckillEndTime());
		return seckillDetailVO;
	}

	@Override
	public List<ListSkuVO> listSkuBySpuId(String spuId) {
		List<ListSkuVO> retList = new ArrayList<ListSkuVO>();
		QueryWrapper<MarketPtSeckillGoodsSku> query = MybatisPlusUtil.query();	//查询条件
        query.eq("goods_spu_item_id",spuId);
		List<MarketPtSeckillGoodsSku> list = marketPtSeckillGoodsSkuRepository.list(query);
		retList = BeanUtils.copyList(ListSkuVO.class, list);
		return retList;
	}

	@Override
	public ListSkuVO getSeckillSku(SeckillSkuDTO dto) {
		ListSkuVO vo = new ListSkuVO();
		QueryWrapper<MarketPtSeckillGoodsSpu> query = MybatisPlusUtil.query();	//查询条件
        query.eq("time_quantum_id",dto.getTimeQuantumId());
		query.eq("goods_id", dto.getGoodsId());
        MarketPtSeckillGoodsSpu marketPtSeckillGoodsSpu = marketPtSeckillGoodsSpuRepository.getOne(query);
        if(marketPtSeckillGoodsSpu!=null){
        	QueryWrapper<MarketPtSeckillGoodsSku> query1 = MybatisPlusUtil.query();	//查询条件
        	query1.eq("goods_spu_item_id",marketPtSeckillGoodsSpu.getId());
        	query1.eq("sku_id", dto.getSkuId());
        	query1.last("limit 0,1");
            MarketPtSeckillGoodsSku marketPtSeckillGoodsSku = marketPtSeckillGoodsSkuRepository.getOne(query1);
            BeanCopyUtils.copyProperties(marketPtSeckillGoodsSku, vo);
        }
		return vo;
	}

}
