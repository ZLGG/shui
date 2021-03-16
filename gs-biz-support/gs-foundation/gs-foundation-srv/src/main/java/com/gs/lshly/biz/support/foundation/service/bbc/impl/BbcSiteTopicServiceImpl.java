package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteTopic;
import com.gs.lshly.biz.support.foundation.entity.SiteTopicGoods;
import com.gs.lshly.biz.support.foundation.mapper.SiteTopicGoodsMapper;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcSiteTopicService;
import com.gs.lshly.common.enums.SiteTopicCategoryEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.EnjoyQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.CategoryListVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.DetailVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketActivityRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-03
*/
@Component
public class BbcSiteTopicServiceImpl implements IBbcSiteTopicService {

    @Autowired
    private ISiteTopicRepository repository;
    
    @Autowired
    private ISiteTopicGoodsRepository goodsRepository ;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;
    
    @DubboReference
    IBbcMarketActivityRpc bbcMarketActivityRpc;
    
    @Autowired
    private SiteTopicGoodsMapper siteTopicGoodsMapper;

	@Override
	public List<CategoryListVO> list(QTO qto) {
		QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
		wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("is_default", TrueFalseEnum.是.getCode());
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        
        List<SiteTopic> listDefault = repository.list(wrapper);
        
        List<BbcSiteTopicVO.CategoryListVO> retListDefault = new ArrayList<BbcSiteTopicVO.CategoryListVO>();
		if(CollectionUtils.isNotEmpty(listDefault))
			retListDefault = BeanUtils.copyList(BbcSiteTopicVO.CategoryListVO.class, listDefault);
		Integer idx = 1;
		if(CollectionUtils.isNotEmpty(retListDefault)){
			for(BbcSiteTopicVO.CategoryListVO model:retListDefault){
				
				model.setIdx(idx);
				QueryWrapper<SiteTopicGoods> goodsWrapper =  MybatisPlusUtil.query();
				goodsWrapper.eq("topic_id",model.getId());
				List<SiteTopicGoods> goodslist =goodsRepository.list(goodsWrapper);
				
				if(CollectionUtils.isNotEmpty(goodslist)){
					List<GoodsInfoVO.DetailVO> goodsInfoList =new ArrayList<GoodsInfoVO.DetailVO>();
					for(SiteTopicGoods siteTopicGoods:goodslist){
						
						String goodsId = siteTopicGoods.getGoodsId();
		        		GoodsInfoVO.DetailVO goodsInfoDetailVO= goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId));
		        		goodsInfoList.add(goodsInfoDetailVO);
						
					}
					model.setList(goodsInfoList);
				}
				idx ++;
			}
		}
		
		//查询秒杀
		BbcSiteTopicVO.TopicVO topicVO = bbcMarketActivityRpc.listFlashsale(new BaseDTO());
		if(topicVO!=null&&CollectionUtils.isNotEmpty(topicVO.getGoodsList())){
			BbcSiteTopicVO.CategoryListVO model1 = new BbcSiteTopicVO.CategoryListVO();
			model1.setId("1");
			model1.setIdx(idx);
			model1.setImageUrl("");
			model1.setName(topicVO.getName());
			model1.setList(topicVO.getGoodsList());
			retListDefault.add(model1);
		}
        return retListDefault;
	}

	@Override
	public PageData<DetailVO> pageEnjoy(EnjoyQTO qto) {
		
		QueryWrapper<SiteTopicGoods> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("topic_id",2);
        IPage<SiteTopicGoods> page = MybatisPlusUtil.pager(qto);
        goodsRepository.page(page, wrapper);
        PageData<SiteTopicGoods> pageData = MybatisPlusUtil.toPageData(qto, SiteTopicGoods.class, page);
        List<SiteTopicGoods> goodsList = pageData.getContent();
        List<GoodsInfoVO.DetailVO> retList = new ArrayList<GoodsInfoVO.DetailVO>();
        if(CollectionUtils.isNotEmpty(goodsList)){
        	for(SiteTopicGoods goodsId:goodsList){
        		GoodsInfoVO.DetailVO detailVO = goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId.getGoodsId()));
        		retList.add(detailVO);
        	}
        }
        PageData<DetailVO> retPage = new PageData<DetailVO>();
        retPage.setContent(retList);
        retPage.setPageNum(pageData.getPageNum());
        retPage.setPageSize(pageData.getPageSize());
        retPage.setTotal(pageData.getTotal());
        return retPage;
		
        /**
		QueryWrapper<SiteTopicGoods> wrapper = MybatisPlusUtil.query();
        IPage<String> page = MybatisPlusUtil.pager(qto);
        
        siteTopicGoodsMapper.pageGoods(page,wrapper);
        PageData<String> pageData = MybatisPlusUtil.toPageData(qto, String.class, page);
        PageData<DetailVO> retPage = new PageData<DetailVO>();
        List<String> goodsList = pageData.getContent();
        List<GoodsInfoVO.DetailVO> retList = new ArrayList<GoodsInfoVO.DetailVO>();
        if(CollectionUtils.isNotEmpty(goodsList)){
        	for(String goodsId:goodsList){
        		GoodsInfoVO.DetailVO detailVO = goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId));
        		retList.add(detailVO);
        	}
        }
        retPage.setContent(retList);
        retPage.setPageNum(pageData.getPageNum());
        retPage.setPageSize(pageData.getPageSize());
        retPage.setTotal(pageData.getTotal());
        return retPage;
        **/
	}

	@Override
	public List<CategoryListVO> listPointHome(BbcSiteTopicQTO.QTO qto) {
		List<CategoryListVO> list = new ArrayList<CategoryListVO>();
		Integer idx = 1;
		//电信甄选/////
		CategoryListVO categoryListVO = new CategoryListVO();
		categoryListVO.setIdx(idx);
		categoryListVO.setName(SiteTopicCategoryEnum.电信甄选.getRemark());
		categoryListVO.setId(SiteTopicCategoryEnum.电信甄选.getCode().toString());
		
		QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
        wrapper.like("category",categoryListVO.getId());
        wrapper.eq("terminal", qto.getTerminal());
        wrapper.eq("subject", qto.getSubject());
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        
        List<SiteTopic> list10 = repository.list(wrapper);
        List<BbcSiteTopicVO.CategoryDetailVO> retList10 = new ArrayList<BbcSiteTopicVO.CategoryDetailVO>();
		if(CollectionUtils.isNotEmpty(list10))
			retList10 = BeanUtils.copyList(BbcSiteTopicVO.CategoryDetailVO.class, list10);
		categoryListVO.setList(retList10);
		list.add(categoryListVO);
		
		//心选好礼/////
		categoryListVO = new CategoryListVO();
		categoryListVO.setIdx(1);
		categoryListVO.setName(SiteTopicCategoryEnum.电信甄选.getRemark());
		categoryListVO.setId(SiteTopicCategoryEnum.电信甄选.getCode().toString());
		
		wrapper =  MybatisPlusUtil.query();
        wrapper.like("is_default",TrueFalseEnum.是.getCode());
        wrapper.eq("terminal", qto.getTerminal());
        wrapper.eq("subject", qto.getSubject());
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        
        List<SiteTopic> listDefault = repository.list(wrapper);
        
        List<BbcSiteTopicVO.CategoryListVO> retListDefault = new ArrayList<BbcSiteTopicVO.CategoryListVO>();
		if(CollectionUtils.isNotEmpty(listDefault))
			retListDefault = BeanUtils.copyList(BbcSiteTopicVO.CategoryListVO.class, listDefault);
		
		if(CollectionUtils.isNotEmpty(retListDefault)){
			for(BbcSiteTopicVO.CategoryListVO model:retListDefault){
				
				idx ++;
				model.setIdx(idx);
				QueryWrapper<SiteTopicGoods> goodsWrapper =  MybatisPlusUtil.query();
				goodsWrapper.eq("topic_id",model.getId());
				List<SiteTopicGoods> goodslist =goodsRepository.list(goodsWrapper);
				
				if(CollectionUtils.isNotEmpty(goodslist)){
					List<GoodsInfoVO.DetailVO> goodsInfoList =new ArrayList<GoodsInfoVO.DetailVO>();
					for(SiteTopicGoods siteTopicGoods:goodslist){
						
						String goodsId = siteTopicGoods.getGoodsId();
		        		GoodsInfoVO.DetailVO goodsInfoDetailVO= goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId));
		        		goodsInfoList.add(goodsInfoDetailVO);
						
					}
					model.setList(goodsInfoList);
				}
			}
			list.addAll(retListDefault);
		}
		
		if(qto.getTerminal().equals(TerminalEnum.BBC.getCode())){
			//电信甄选/////
			categoryListVO = new CategoryListVO();
			categoryListVO.setIdx(idx++);
			categoryListVO.setName(SiteTopicCategoryEnum.名品集市.getRemark());
			categoryListVO.setId(SiteTopicCategoryEnum.名品集市.getCode().toString());
			
			wrapper =  MybatisPlusUtil.query();
	        wrapper.like("category",categoryListVO.getId());
	        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
	        wrapper.eq("subject", SubjectEnum.积分商城.getCode());
	        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
	        
	        List<SiteTopic> list20 = repository.list(wrapper);
	        List<BbcSiteTopicVO.CategoryDetailVO> retList20 = new ArrayList<BbcSiteTopicVO.CategoryDetailVO>();
			if(CollectionUtils.isNotEmpty(list20))
				retList20 = BeanUtils.copyList(BbcSiteTopicVO.CategoryDetailVO.class, list20);
			categoryListVO.setList(retList20);
			list.add(categoryListVO);
		}
		return list;
	}

	@Override
	public CategoryListVO topicGoods(String topicId) {
		CategoryListVO categoryListVO = new CategoryListVO();
		SiteTopic siteTopic = repository.getById(topicId);
		if(siteTopic==null)
			throw new BusinessException("未查询到数据");
		categoryListVO.setId(siteTopic.getId());
		categoryListVO.setIdx(1);
		categoryListVO.setImageUrl(siteTopic.getImageUrl());
		categoryListVO.setName(siteTopic.getName());
		
		QueryWrapper<SiteTopicGoods> goodsWrapper =  MybatisPlusUtil.query();
		goodsWrapper.eq("topic_id",topicId);
		List<SiteTopicGoods> goodslist =goodsRepository.list(goodsWrapper);
		
		if(CollectionUtils.isNotEmpty(goodslist)){
			List<GoodsInfoVO.DetailVO> goodsInfoList =new ArrayList<GoodsInfoVO.DetailVO>();
			for(SiteTopicGoods siteTopicGoods:goodslist){
				
				String goodsId = siteTopicGoods.getGoodsId();
        		GoodsInfoVO.DetailVO goodsInfoDetailVO= goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId));
        		goodsInfoList.add(goodsInfoDetailVO);
				
			}
			categoryListVO.setList(goodsInfoList);
		}
		return categoryListVO;
	}

    

}
