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
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.EnjoyQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.ListByTopicNameQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.QTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO.SearchmoreQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.CategoryDetailVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.CategoryListVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.ListByTopicNameVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO.TopicVO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcMarketActivityQTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.DetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
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
    
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    @DubboReference
    private IBbcGoodsCategoryRpc bbcGoodsCategoryRpc;

    /**
     * 获取电信大类下面的四个随机产品
     * 
     */
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
			model1.setId("miaosha");
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
        /**
        String category = qto.getCategory();	//1级分类
        if(StringUtils.isNotEmpty(category)){
        	BbcGoodsCategoryQTO.ListQTO listQTO = new BbcGoodsCategoryQTO.ListQTO();
        	listQTO.setParentId(category);
        	listQTO.setShowAll(true);
        	listQTO.setUseFiled(GoodsUsePlatformEnums.B商城和C商城.getCode());
        	List<BbcGoodsCategoryVO.CategoryTreeVO> categoryTreeList = bbcGoodsCategoryRpc.listGoodsCategory(listQTO);
        	String categorys = "'";
        	if(CollectionUtils.isNotEmpty(categoryTreeList)){
        		for(BbcGoodsCategoryVO.CategoryTreeVO categoryTreeVO:categoryTreeList){
        			categorys = categorys+categoryTreeVO.getId()+"',";
        		}
        		categorys = categorys.substring(0, categorys.length()-1);
        		qto.setCategoryIds(categorys);
        	}
        }
        **/
        IPage<SiteTopicGoods> page = MybatisPlusUtil.pager(qto);
        goodsRepository.page(page,wrapper);
        //siteTopicGoodsMapper.pageByCodeCategoryIds(page, new QueryWrapper(qto));
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
        
        //IN会员专区
        SiteTopic siteTopic = new SiteTopic();
        siteTopic.setCategory(SiteTopicCategoryEnum.电信甄选.getRemark());
        siteTopic.setId("inmembergift");//IN会员专区
        siteTopic.setIdx(1);
        siteTopic.setFlag(false);
        siteTopic.setImageUrl(null);
        siteTopic.setName("IN会员精口专区");
        siteTopic.setRemark("最高抵扣200元");
        list10.add(siteTopic);
        
        
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

	@Override
	public BbcSiteTopicVO.GoodsVO pageMore(SearchmoreQTO qto) {
		BbcSiteTopicVO.GoodsVO goodvo = new BbcSiteTopicVO.GoodsVO();
		if(qto.getId().equals("miaosha")){
			BbcMarketActivityQTO.QTO qto1 = new BbcMarketActivityQTO.QTO();
			BeanCopyUtils.copyProperties(qto, qto1);
			goodvo.setList(bbcMarketActivityRpc.pageFlashsale(qto1));
		}else{
			QueryWrapper<SiteTopicGoods> wrapper =  MybatisPlusUtil.query();
	        wrapper.eq("topic_id",qto.getId());
	        IPage<SiteTopicGoods> page = MybatisPlusUtil.pager(qto);
	        goodsRepository.page(page, wrapper);
	        PageData<SiteTopicGoods> pageData = MybatisPlusUtil.toPageData(qto, SiteTopicGoods.class, page);
	        List<SiteTopicGoods> goodsList = pageData.getContent();
	        List<BbcGoodsInfoVO.DetailVO> retList = new ArrayList<BbcGoodsInfoVO.DetailVO>();
	        if(CollectionUtils.isNotEmpty(goodsList)){
	        	for(SiteTopicGoods goodsId:goodsList){
	        		BbcGoodsInfoVO.DetailVO detailVO = bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId.getGoodsId()));
	        		retList.add(detailVO);
	        	}
	        }
	        goodvo.setList(new PageData<>(retList,qto.getPageNum(),qto.getPageSize(),page.getTotal()));
	        SiteTopic topic = repository.getById(qto.getId());
	        goodvo.setImageUrl(topic.getImageUrl());
	        goodvo.setId(topic.getId());
	        goodvo.setName(topic.getName());
	        goodvo.setTelecomsIntegral(0);
		}
		return goodvo;
		
	}

	@Override
	public ListByTopicNameVO listByTopicName(ListByTopicNameQTO qto) {
		ListByTopicNameVO listByTopicNameVO = new ListByTopicNameVO();
		QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
		wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("is_default", TrueFalseEnum.是.getCode());
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        wrapper.eq("name", qto.getName());
        SiteTopic siteTopic = repository.getOne(wrapper);
				
        listByTopicNameVO.setId(siteTopic.getId());
        listByTopicNameVO.setName(siteTopic.getName());
        listByTopicNameVO.setImageUrl(siteTopic.getImageUrl());

        QueryWrapper<SiteTopicGoods> goodsWrapper =  MybatisPlusUtil.query();
		goodsWrapper.eq("topic_id",siteTopic.getId());
		List<SiteTopicGoods> goodslist =goodsRepository.list(goodsWrapper);
		
		if(CollectionUtils.isNotEmpty(goodslist)){
			List<BbcGoodsInfoVO.DetailVO> goodsInfoList =new ArrayList<BbcGoodsInfoVO.DetailVO>();
			for(SiteTopicGoods siteTopicGoods:goodslist){
				
				String goodsId = siteTopicGoods.getGoodsId();
				BbcGoodsInfoVO.DetailVO goodsInfoDetailVO = bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
        		goodsInfoList.add(goodsInfoDetailVO);
				
			}
			listByTopicNameVO.setList(goodsInfoList);
		}
		
		return listByTopicNameVO;
	}

	@Override
	public List<CategoryDetailVO> listTopicByCategory(Integer category) {
		List<CategoryDetailVO> retList = new ArrayList<CategoryDetailVO>();
		QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
		wrapper.eq("terminal",TerminalEnum.BBC.getCode());
        wrapper.eq("subject",SubjectEnum.积分商城.getCode());
        wrapper.eq("is_default", TrueFalseEnum.是.getCode());
        wrapper.eq("onoff", TrueFalseEnum.是.getCode());
        wrapper.eq("category", category);
        List<SiteTopic> listDefault = repository.list(wrapper);
        if(CollectionUtils.isNotEmpty(listDefault))
        	retList = BeanUtils.copyList(CategoryDetailVO.class, listDefault);
        return retList;
	}

    

}
