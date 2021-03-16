package com.gs.lshly.biz.support.trade.repository;


import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSku;
import com.gs.lshly.biz.support.trade.mapper.MarketPtActivityGoodsSkuMapper;

/**
 * <p>
 * 商家报名商品(sku) 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-02
 */
public interface IMarketPtActivityGoodsSkuRepository extends IService<MarketPtActivityGoodsSku> {

    MarketPtActivityGoodsSkuMapper baseMapper();
}
