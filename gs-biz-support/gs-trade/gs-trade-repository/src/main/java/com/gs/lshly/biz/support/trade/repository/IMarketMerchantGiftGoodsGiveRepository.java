package com.gs.lshly.biz.support.trade.repository;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoodsGive;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGiftGoodsGiveMapper;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantGiftGoodsMapper;

/**
 * <p>
 * 商家满赠促销关联赠品 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface IMarketMerchantGiftGoodsGiveRepository extends IService<MarketMerchantGiftGoodsGive> {

    MarketMerchantGiftGoodsGiveMapper baseMapper();
}
