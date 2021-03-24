package com.gs.lshly.biz.support.trade.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.mapper.TradeGoodsMapper;
import com.gs.lshly.biz.support.trade.service.common.ICommonTradeGoodsService;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jdt
 * @date 2021/3/24 10:15
 */
@Component
public class ICommonTradeGoodsServiceImpl implements ICommonTradeGoodsService {

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;

    @Override
    public Integer sumQuantity(String goodsId) {
        QueryWrapper<Trade> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id",goodsId);
        return tradeGoodsMapper.sumQuantity(wrapper);
    }
}