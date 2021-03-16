package com.gs.lshly.biz.support.trade.repository;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantDiscountGoodsMapper;

/**
 * <p>
 * 商家满折促销关联商品 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface IMarketMerchantDiscountGoodsRepository extends IService<MarketMerchantDiscountGoods> {

    MarketMerchantDiscountGoodsMapper baseMapper();
}
