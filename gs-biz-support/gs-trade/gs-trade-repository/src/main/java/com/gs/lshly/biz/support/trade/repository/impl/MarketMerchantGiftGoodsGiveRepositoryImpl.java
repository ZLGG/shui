package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoodsGive;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGiftGoodsGiveMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsGiveRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-12-09
*/
@Service
public class MarketMerchantGiftGoodsGiveRepositoryImpl extends ServiceImpl<MarketMerchantGiftGoodsGiveMapper, MarketMerchantGiftGoodsGive> implements IMarketMerchantGiftGoodsGiveRepository {

    @Override
    public MarketMerchantGiftGoodsGiveMapper baseMapper() {
        return getBaseMapper();
    }
}