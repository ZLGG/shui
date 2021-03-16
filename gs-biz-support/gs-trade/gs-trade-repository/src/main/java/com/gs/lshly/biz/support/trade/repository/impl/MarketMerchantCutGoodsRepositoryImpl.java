package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCutGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutGoodsRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-12-08
*/
@Service
public class MarketMerchantCutGoodsRepositoryImpl extends ServiceImpl<MarketMerchantCutGoodsMapper, MarketMerchantCutGoods> implements IMarketMerchantCutGoodsRepository {

    @Override
    public MarketMerchantCutGoodsMapper baseMapper() {
        return getBaseMapper();
    }
}