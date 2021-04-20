package com.gs.lshly.biz.support.commodity.service.bbc;

import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;

public interface IBbcCtccCategoryGoodsService {

	/**
	 * 
	 * @param dto
	 * @return
	 */
    BbcCtccCategoryGoodsVO.CtccInternationalHomeVO ctccInternationalHome(BbcCtccCategoryGoodsDTO.DTO dto);
    
}