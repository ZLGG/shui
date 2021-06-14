package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.commodity.entity.CtccCategory;
import com.gs.lshly.biz.support.commodity.entity.CtccCategoryGoods;
import com.gs.lshly.biz.support.commodity.mapper.CtccCategoryMapper;
import com.gs.lshly.biz.support.commodity.repository.ICtccCategoryGoodsRepository;
import com.gs.lshly.biz.support.commodity.repository.ICtccCategoryRepository;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcCtccCategoryGoodsService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsLabelService;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalAdvertVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.GoodsListVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.DetailVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcCtccPtActivityGoodsRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BbcCtccCategoryGoodsServiceImpl implements IBbcCtccCategoryGoodsService{
	
	@Autowired
    private ICtccCategoryRepository repository;
	
	@Autowired
	private ICtccCategoryGoodsRepository goodsRepository;
	
    @DubboReference
    private IBbcSiteAdvertRpc siteAdvertRpc;
    
    @Autowired
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    
    @DubboReference
    private IBbcUserRpc userRpc;
	/**
	 * 
	 * @param dto
	 * @return
	 */
    public BbcCtccCategoryGoodsVO.CtccInternationalHomeVO ctccInternationalHome(BbcCtccCategoryGoodsDTO.DTO dto){
    	BbcSiteAdvertQTO.SubjectQTO subjectQTO = new BbcSiteAdvertQTO.SubjectQTO();
    	subjectQTO.setSubject(SubjectEnum.电信国际.getCode());
    	List<BbcSiteAdvertVO.AdvertDetailVO> advertVO = siteAdvertRpc.listBySubject(subjectQTO);
    	BbcCtccCategoryGoodsVO.CtccInternationalHomeVO ctccInternationalHomeVO = new BbcCtccCategoryGoodsVO.CtccInternationalHomeVO();
    	
    	List<CtccInternationalAdvertVO> adverts = BeanUtils.copyList(CtccInternationalAdvertVO.class, advertVO);
    	ctccInternationalHomeVO.setAdverts(adverts);//广告位
    	
    	//查询分类
    	QueryWrapper<CtccCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal", TerminalEnum.BBC.getCode());
        wrapper.eq("subject", SubjectEnum.电信国际.getCode());
        wrapper.orderByAsc("idx");
        List<CtccCategory> ctccCategoryList = repository.list( wrapper);
        
        
        BbcUserVO.DetailVO userDetail = userRpc.getUserInfoNoLogin(dto);
        
        if(CollectionUtil.isNotEmpty(ctccCategoryList)){
        	List<CtccInternationalCategoryVO> categorys = new ArrayList<CtccInternationalCategoryVO>();
        	
        	CtccInternationalCategoryVO ctccInternationalCategoryVO = null;
        	
        	List<BbcCtccCategoryGoodsVO.GoodsListVO> goodsList = new ArrayList<BbcCtccCategoryGoodsVO.GoodsListVO>();
        	
        	
        	for(CtccCategory ctccCategory:ctccCategoryList){
            	QueryWrapper<CtccCategoryGoods> wrapperGoods = MybatisPlusUtil.query();

        		ctccInternationalCategoryVO = new CtccInternationalCategoryVO();
        		BeanCopyUtils.copyProperties(ctccCategory, ctccInternationalCategoryVO);
        		
        		//
        		wrapperGoods.eq("category_id", ctccCategory.getId());
        		wrapperGoods.eq("goods_state", 20);
        		wrapperGoods.eq("flag", 0);
        		wrapperGoods.orderByAsc("idx");
        		List<CtccCategoryGoods> ctccCategoryGoods = goodsRepository.list(wrapperGoods);
        		
        		if(CollectionUtil.isNotEmpty(ctccCategoryGoods)){
        			BbcCtccCategoryGoodsVO.GoodsListVO goodsListVO = new BbcCtccCategoryGoodsVO.GoodsListVO();
	        		for(CtccCategoryGoods goods:ctccCategoryGoods){
	        			goodsListVO = new BbcCtccCategoryGoodsVO.GoodsListVO();
	        			
	        			String goodsId = goods.getGoodsId();
	        			BbcGoodsInfoVO.SimpleListVO simpleListVO = bbcGoodsInfoRpc.simpleListVO(new BbcGoodsInfoDTO.IdDTO(goodsId));
	        			BeanCopyUtils.copyProperties(simpleListVO, goodsListVO);
//	        			goodsListVO.setTags(bbcGoodsLabelService.listGoodsLabelByGoodsId(goodsListVO.getGoodsId()));
//	        			goodsListVO.setGoodsImage(ObjectUtils.isEmpty(StringManageUtil.getImage(goodsListVO.getGoodsImage())) ? "" : StringManageUtil.getImage(goodsListVO.getGoodsImage()));
//	        			goodsListVO.setGoodsId(goodsListVO.getId());
	        			goodsListVO.setIsInUser(userDetail.getIsInUser());
	        			goodsListVO.setMemberType(userDetail.getMemberType());
	        			
	        			goodsList.add(goodsListVO);
	        		}
        		}
        		
        		ctccInternationalCategoryVO.setGoodsList(goodsList);
        		categorys.add(ctccInternationalCategoryVO);
        	}
        	ctccInternationalHomeVO.setCategorys(categorys);
        }
        
    	return ctccInternationalHomeVO;
    }
	@Override
	public List<DetailVO> listGoodsInfo() {
		QueryWrapper<CtccCategoryGoods> wrapperGoods = MybatisPlusUtil.query();
		wrapperGoods.last("LIMIT 0,6");
		List<CtccCategoryGoods> ctccCategoryGoods = goodsRepository.list(wrapperGoods);
		
		List<BbcGoodsInfoVO.DetailVO> detailVOList = new ArrayList<BbcGoodsInfoVO.DetailVO>();
		if(CollectionUtil.isNotEmpty(ctccCategoryGoods)){
    		for(CtccCategoryGoods ctccCategoryGood:ctccCategoryGoods){
				
				String goodsId = ctccCategoryGood.getGoodsId();
				BbcGoodsInfoVO.DetailVO detailVO= bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId));
				detailVOList.add(detailVO);
				
			}
		}
		return detailVOList;
	}
	@Override
	public List<GoodsListVO> listGoodsByCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
    
}