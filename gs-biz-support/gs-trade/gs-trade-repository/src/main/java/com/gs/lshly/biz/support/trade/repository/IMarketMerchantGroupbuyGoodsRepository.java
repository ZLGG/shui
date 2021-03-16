package com.gs.lshly.biz.support.trade.repository;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuyGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCardGoodsMapper;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGroupbuyGoodsMapper;

/**
 * <p>
 * 商家团购促销关联商品 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-10
 */
public interface IMarketMerchantGroupbuyGoodsRepository extends IService<MarketMerchantGroupbuyGoods> {

    MarketMerchantGroupbuyGoodsMapper baseMapper();
}
