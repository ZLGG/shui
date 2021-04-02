package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCardMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-12-04
*/
@Service
public class MarketMerchantCardRepositoryImpl extends ServiceImpl<MarketMerchantCardMapper, MarketMerchantCard> implements IMarketMerchantCardRepository {
    @Autowired
    private MarketMerchantCardMapper marketMerchantCardMapper;

    @Override
    public List<MarketMerchantCard> getByGoodsId(QueryWrapper<MarketMerchantCard> qw) {
        return marketMerchantCardMapper.getByGoodsId(qw);
    }

    @Override
    public List<MarketMerchantCard> selectCard(QueryWrapper<MarketMerchantCard> query) {
        return marketMerchantCardMapper.selectCard(query);
    }
}