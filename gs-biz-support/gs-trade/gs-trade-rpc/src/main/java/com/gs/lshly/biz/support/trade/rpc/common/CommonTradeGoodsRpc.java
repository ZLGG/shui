package com.gs.lshly.biz.support.trade.rpc.common;

import com.gs.lshly.biz.support.trade.service.common.ICommonTradeGoodsService;
import com.gs.lshly.rpc.api.common.ICommonTradeGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jdt
 * @date 2021/3/24 10:24
 */
@DubboService
public class CommonTradeGoodsRpc implements ICommonTradeGoodsRpc {

    @Autowired
    private ICommonTradeGoodsService iCommonTradeGoodsService;

    @Override
    public Integer sumQuantity(String goodsId) {
        return iCommonTradeGoodsService.sumQuantity(goodsId);
    }
}