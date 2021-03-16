package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantDiscountGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
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
public class MarketMerchantDiscountGoodsRepositoryImpl extends ServiceImpl<MarketMerchantDiscountGoodsMapper, MarketMerchantDiscountGoods> implements IMarketMerchantDiscountGoodsRepository {


    @Override
    public MarketMerchantDiscountGoodsMapper baseMapper() {
        return getBaseMapper();
    }
}