package com.gs.lshly.biz.support.stock.service.bbc;

import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDTO.SkuIdDTO;

public interface IBbcStockService {

	/**
	 * 获取库存数据
	 * @param dto
	 * @return
	 */
	Integer getQuantity(String skuId);
}