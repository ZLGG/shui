package com.gs.lshly.rpc.api.bbc.stock;

/**
 * 库存数据
 *
 * 
 * @author yingjun
 * @date 2021年5月27日 下午8:01:17
 */
public interface IBbcStockRpc {

	/**
	 * 获取sku对应的库存
	 * @param skuId
	 * @return
	 */
    Integer getQuantity(String skuId);
}