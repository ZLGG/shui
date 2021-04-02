package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商家优惠卷 服务类
 * </p>
 *
 * @author zdf
 * @since 2020-12-04
 */
public interface IMarketMerchantCardRepository extends IService<MarketMerchantCard> {

    List<MarketMerchantCard> getByGoodsId( @Param(Constants.WRAPPER)QueryWrapper<MarketMerchantCard> qw);

    List<MarketMerchantCard> selectCard( @Param(Constants.WRAPPER) QueryWrapper<MarketMerchantCard> query);
}
