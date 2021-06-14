package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.CtccActivityGoods;
import com.gs.lshly.biz.support.trade.entity.CtccCategory;
import com.gs.lshly.biz.support.trade.repository.ICtccActivityGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.ICtccCategoryRepository;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcCtccPtActivityGoodsService;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO.DTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalAdvertVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO.CtccInternationalHomeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.StringManageUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 下午3:18:45
 */
@Component
public class BbcCtccPtActivityGoodsServiceImpl implements IBbcCtccPtActivityGoodsService{
	
	@Autowired
	private ICtccActivityGoodsRepository ctccActivityGoodsRepository;
	
    @DubboReference
	private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    @Autowired
    private ICtccCategoryRepository repository;
	
	@Autowired
	private ICtccCategoryGoodsRepository goodsRepository;
    
    @DubboReference
    private IBbcSiteAdvertRpc siteAdvertRpc;
    
    @DubboReference
    private IBbcUserRpc userRpc;

	@Override
	public List<BbcGoodsInfoVO.DetailVO> listCtccPtActivityGoods(Integer limit) {
		QueryWrapper<CtccActivityGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_state",GoodsStateEnum.已上架.getCode());
        queryWrapper.orderByAsc("idx");
        queryWrapper.last("limit 0,6");
        List<CtccActivityGoods> list = ctccActivityGoodsRepository.list(queryWrapper);
        List<BbcGoodsInfoVO.DetailVO> retList = new ArrayList<BbcGoodsInfoVO.DetailVO>();
        if(CollectionUtil.isNotEmpty(list)){
        	for(CtccActivityGoods ctccPtActivity:list){
        		String goodsId = ctccPtActivity.getGoodsId();
        		retList.add(bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(goodsId)));
        	}
        }
        
        return retList;
	}



	@Override
	public CtccInternationalHomeVO ctccInternationalHomeVO(DTO dto) {
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
            	QueryWrapper<CtccActivityGoods> wrapperGoods = MybatisPlusUtil.query();

        		ctccInternationalCategoryVO = new CtccInternationalCategoryVO();
        		BeanCopyUtils.copyProperties(ctccCategory, ctccInternationalCategoryVO);
        		
        		//
        		wrapperGoods.eq("category_id", ctccCategory.getId());
        		wrapperGoods.eq("goods_state", 20);
        		wrapperGoods.eq("flag", 0);
        		wrapperGoods.orderByAsc("idx");
        		List<CtccActivityGoods> ctccCategoryGoods = ctccActivityGoodsRepository.list(wrapperGoods);
        		
        		if(CollectionUtil.isNotEmpty(ctccCategoryGoods)){
        			BbcCtccCategoryGoodsVO.GoodsListVO goodsListVO = new BbcCtccCategoryGoodsVO.GoodsListVO();
	        		for(CtccActivityGoods goods:ctccCategoryGoods){
	        			goodsListVO = new BbcCtccCategoryGoodsVO.GoodsListVO();
	        			
	        			String goodsId = goods.getGoodsId();
	        			BbcGoodsInfoVO.SimpleListVO simpleListVO = bbcGoodsInfoRpc.simpleListVO(new BbcGoodsInfoDTO.IdDTO(goodsId));
	        			BeanCopyUtils.copyProperties(simpleListVO, goodsListVO);
//	        			goodsListVO.setTags(bbcGoodsLabelService.listGoodsLabelByGoodsId(goodsListVO.getGoodsId()));
	        			goodsListVO.setGoodsImage(ObjectUtils.isEmpty(StringManageUtil.getImage(goodsListVO.getGoodsImage())) ? "" : StringManageUtil.getImage(goodsListVO.getGoodsImage()));
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

	
    
    
    
}
