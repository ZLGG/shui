package com.gs.lshly.biz.support.trade.repository;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCutGoodsMapper;

/**
 * <p>
 * 商家满减促销关联商品 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-08
 */
public interface IMarketMerchantCutGoodsRepository extends IService<MarketMerchantCutGoods> {

    MarketMerchantCutGoodsMapper baseMapper();
}
