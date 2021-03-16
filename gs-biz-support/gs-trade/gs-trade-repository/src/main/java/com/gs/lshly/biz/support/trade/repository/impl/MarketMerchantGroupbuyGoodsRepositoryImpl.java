package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuyGoods;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGroupbuyGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyGoodsRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2020-12-10
*/
@Service
public class MarketMerchantGroupbuyGoodsRepositoryImpl extends ServiceImpl<MarketMerchantGroupbuyGoodsMapper, MarketMerchantGroupbuyGoods> implements IMarketMerchantGroupbuyGoodsRepository {

    @Override
    public MarketMerchantGroupbuyGoodsMapper baseMapper() {
        return getBaseMapper();
    }
}