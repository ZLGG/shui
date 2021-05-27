package com.gs.lshly.biz.support.stock.rpc.bbc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.stock.service.bbc.IBbcStockService;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDTO.SkuIdDTO;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockRpc;

/**
 * 库存数据
 *
 * 
 * @author yingjun
 * @date 2021年5月27日 下午8:01:52
 */
@DubboService
public class BbcStockRpc implements IBbcStockRpc {

    @Autowired
    private IBbcStockService  bbcStockService;

	@Override
	public Integer getQuantity(String skuId) {
		return bbcStockService.getQuantity(skuId);
	}

    
}