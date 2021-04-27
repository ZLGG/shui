package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.foundation.service.bbb.pc.IBbbSiteNavigationService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcPointHomeService;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteTopicService;
import com.gs.lshly.common.enums.PointHomeTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.InMemberGoodsQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.ListByTopicNameQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO.ListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO.SeckillListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketSeckillDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketSeckillVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketActivityRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketSeckillRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 积分商城首页
 *
 * 
 * @author yingjun
 * @date 2021年4月12日 上午12:17:28
 */
@Component
public class BbcPointHomeServiceImpl implements IBbcPointHomeService {

    @DubboReference
    private IGoodsCategoryRpc goodsCategoryRpc;
    
    @Autowired
    private IBbbSiteNavigationService bbbSiteNavigationService;
    
    @Autowired
    private IBbcSiteTopicService bbcSiteTopicService;
    
    @DubboReference
    private IBbcMarketActivityRpc bbcMarketActivityRpc;
    
    @DubboReference
    private IBbcMarketSeckillRpc bbcMarketSeckillRpc;
    
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
	@Override
	public List<ListVO> getHome(QTO qto) {
		
		List<ListVO> retList = new ArrayList<ListVO>();
		/**
		 * 1、category = 分类
		 * 2、banner = 广告位
		 * 3、menu = 菜单
		 * 4、ctccProduct = 电信产品
		 * 5、seckill = 秒杀
		 * 6、ctccInternational = 电信国际
		 * 7、inMember = IN会员
		 * 8、chooseRight = 心选好礼
		 * 9、localLife = 本地生活
		 * 10、careful = 精打细算
		 */
		ListVO listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.分类.getCode());
		listVO.setCode(PointHomeTypeEnum.分类.getCode());
		listVO.setIdx(PointHomeTypeEnum.分类.getIdx());
		listVO.setName(PointHomeTypeEnum.分类.getRemark());
		listVO.setList(goodsCategoryRpc.selectCategoryTree());
		retList.add(listVO);
		
		PCBbbGoodsCategoryQTO.QTO qto1 = new PCBbbGoodsCategoryQTO.QTO();
		BeanCopyUtils.copyProperties(qto, qto1);
		BbbSiteNavigationVO.HomeVO homeVO = bbbSiteNavigationService.homeDetail(qto1);
		
		listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.广告位.getCode());
		listVO.setCode(PointHomeTypeEnum.广告位.getCode());
		listVO.setIdx(PointHomeTypeEnum.广告位.getIdx());
		listVO.setName(PointHomeTypeEnum.广告位.getRemark());
		listVO.setList(homeVO.getBannerList());
		retList.add(listVO);
		
		listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.菜单.getCode());
		listVO.setCode(PointHomeTypeEnum.菜单.getCode());
		listVO.setIdx(PointHomeTypeEnum.菜单.getIdx());
		listVO.setName(PointHomeTypeEnum.菜单.getRemark());
		listVO.setList(homeVO.getMenuList());
		retList.add(listVO);
		
		ListByTopicNameQTO qto2 = new ListByTopicNameQTO();
		BeanCopyUtils.copyProperties(qto, qto2);
		
//		BbcGoodsInfoQTO.GoodsListByCategoryQTO goodsListByCategoryQTO = new BbcGoodsInfoQTO.GoodsListByCategoryQTO();
//		goodsListByCategoryQTO.setCategoryLevel("1377448881003933697");
//		goodsListByCategoryQTO.setPageNum(1);
//		goodsListByCategoryQTO.setPageSize(6);
//		PageData<BbcGoodsInfoVO.GoodsListVO> goodsListVO= bbcGoodsInfoRpc.pageGoodsListVO(goodsListByCategoryQTO);
//		
//		bbcSiteTopicService.listTopicByCategory(10);
		
		listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.电信产品.getCode());
		listVO.setCode(PointHomeTypeEnum.电信产品.getCode());
		listVO.setIdx(PointHomeTypeEnum.电信产品.getIdx());
		listVO.setName(PointHomeTypeEnum.电信产品.getRemark());
		listVO.setList(bbcSiteTopicService.listTopicByCategory(10));
		retList.add(listVO);
		
		BbcMarketActivityVO.SeckillHome seckill = bbcMarketSeckillRpc.seckillHome(new BbcMarketSeckillDTO.DTO());
		
		listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.秒杀.getCode());
		listVO.setCode(PointHomeTypeEnum.秒杀.getCode());
		listVO.setIdx(PointHomeTypeEnum.秒杀.getIdx());
		listVO.setName(PointHomeTypeEnum.秒杀.getRemark());
		if(seckill!=null){
			listVO.setRemark(seckill.getSeckillEndTime().toString());
			PageData<BbcMarketSeckillVO.SeckillGoodsVO> page = seckill.getList();
			if(page!=null){
				List<BbcMarketSeckillVO.SeckillGoodsVO> list = page.getContent();
				
				if(CollectionUtil.isNotEmpty(list)&&list.size()>3){
					listVO.setList(list.subList(0, 3));
				}else{
					listVO.setList(list);
				}
			}
			
		}
		retList.add(listVO);
		
		qto2.setName(PointHomeTypeEnum.电信国际.getRemark());
		BbcSiteTopicVO.ListByTopicNameVO listByTopicNameVO = bbcSiteTopicService.listByTopicName(qto2);
		
		listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.电信国际.getCode());
		listVO.setCode(PointHomeTypeEnum.电信国际.getCode());
		listVO.setIdx(PointHomeTypeEnum.电信国际.getIdx());
		listVO.setName(PointHomeTypeEnum.电信国际.getRemark());
		listVO.setList(listByTopicNameVO.getList());
		retList.add(listVO);
		
		listVO = new ListVO();
		listVO.setId(PointHomeTypeEnum.IN会员专区.getCode());
		listVO.setCode(PointHomeTypeEnum.IN会员专区.getCode());
		listVO.setIdx(PointHomeTypeEnum.IN会员专区.getIdx());
		listVO.setName(PointHomeTypeEnum.IN会员专区.getRemark());
		InMemberGoodsQTO inMemberGoodsQTO = new InMemberGoodsQTO();
		inMemberGoodsQTO.setPageNum(1);
		inMemberGoodsQTO.setPageSize(6);
		bbcGoodsInfoRpc.inMemberGoodsHome(inMemberGoodsQTO).getList().getContent();
		listVO.setList(bbcGoodsInfoRpc.inMemberGoodsHome(inMemberGoodsQTO).getList().getContent());
		retList.add(listVO);
		
		qto2.setName(PointHomeTypeEnum.心选好礼.getRemark());
		listByTopicNameVO = bbcSiteTopicService.listByTopicName(qto2);
		
		listVO = new ListVO();
		listVO.setId(listByTopicNameVO.getId());
		listVO.setCode(PointHomeTypeEnum.心选好礼.getCode());
		listVO.setIdx(PointHomeTypeEnum.心选好礼.getIdx());
		listVO.setName(PointHomeTypeEnum.心选好礼.getRemark());
		listVO.setList(listByTopicNameVO.getList());
		retList.add(listVO);
		
		qto2.setName(PointHomeTypeEnum.本地生活.getRemark());
		listByTopicNameVO = bbcSiteTopicService.listByTopicName(qto2);
		
		listVO = new ListVO();
		listVO.setId(listByTopicNameVO.getId());
		listVO.setCode(PointHomeTypeEnum.本地生活.getCode());
		listVO.setIdx(PointHomeTypeEnum.本地生活.getIdx());
		listVO.setName(PointHomeTypeEnum.本地生活.getRemark());
		listVO.setList(listByTopicNameVO.getList());
		retList.add(listVO);
		
		qto2.setName(PointHomeTypeEnum.精打细算.getRemark());
		listByTopicNameVO = bbcSiteTopicService.listByTopicName(qto2);
		
		listVO = new ListVO();
		listVO.setId(listByTopicNameVO.getId());
		listVO.setCode(PointHomeTypeEnum.精打细算.getCode());
		listVO.setIdx(PointHomeTypeEnum.精打细算.getIdx());
		listVO.setName(PointHomeTypeEnum.精打细算.getRemark());
		listVO.setList(listByTopicNameVO.getList());
		retList.add(listVO);
		
		return retList;
	}

}
