package com.gs.lshly.biz.support.commodity.service.bbc;

import java.util.List;

import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.DetailVO;

public interface IBbcCtccCategoryGoodsService {

	/**
	 * 
	 * @param dto
	 * @return
	 */
    BbcCtccCategoryGoodsVO.CtccInternationalHomeVO ctccInternationalHome(BbcCtccCategoryGoodsDTO.DTO dto);
    
    
    List<DetailVO> listGoodsInfo();
    
    /**
     * 跟据分类查询商品
     * @param categoryId
     * @return
     */
    List<BbcCtccCategoryGoodsVO.GoodsListVO> listGoodsByCategoryId(String categoryId);
    
}