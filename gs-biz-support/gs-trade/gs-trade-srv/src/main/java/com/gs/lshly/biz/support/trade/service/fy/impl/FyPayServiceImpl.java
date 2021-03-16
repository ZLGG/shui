package com.gs.lshly.biz.support.trade.service.fy.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Trade;
import com.gs.lshly.biz.support.trade.entity.TradePay;
import com.gs.lshly.biz.support.trade.repository.ITradePayRepository;
import com.gs.lshly.biz.support.trade.repository.ITradeRepository;
import com.gs.lshly.biz.support.trade.service.fy.IFyPayService;
import com.gs.lshly.common.enums.TradePayStateEnum;
import com.gs.lshly.common.enums.TradeStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FyPayServiceImpl implements IFyPayService {
    @Autowired
    private ITradeRepository iTradeRepository;
    @Autowired
    private ITradePayRepository iTradePayRepository;
    @Override
    @Transactional
    public void payEditState(String tradeId) {
        if (StringUtils.isBlank(tradeId)){
            throw new BusinessException("请传订单ID");
        }
        Trade trade = iTradeRepository.getById(tradeId);
        if (ObjectUtils.isEmpty(trade)){
            throw new BusinessException("查询不到订单");
        }
        trade.setTradeState(TradeStateEnum.待发货.getCode());
        iTradeRepository.saveOrUpdate(trade);
        QueryWrapper<TradePay> query = MybatisPlusUtil.query();
        query.and(i->i.eq("trade_id",tradeId));
        TradePay one = iTradePayRepository.getOne(query);
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("查询不到支付单");
        }
        if (ObjectUtils.isNotEmpty(one)){
            one.setPayState(TradePayStateEnum.已支付.getCode());
            iTradePayRepository.saveOrUpdate(one);
        }

    }
}
