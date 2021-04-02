package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家优惠卷 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-04
 */
public interface MarketMerchantCardMapper extends BaseMapper<MarketMerchantCard> {

    @Select("SELECT c.* FROM `gs_market_merchant_card` c LEFT JOIN `gs_market_merchant_card_goods` g on c.`id`=g.`card_id` where c.`flag`=0 and g.`flag`=0 AND ${ew.sqlSegment}")
    List<MarketMerchantCard> getByGoodsId(@Param(Constants.WRAPPER) QueryWrapper<MarketMerchantCard> qw);
    @Select("select c.* from gs_market_merchant_card  c LEFT JOIN gs_market_merchant_card_goods g ON c.id=g.card_id where c.flag=0 AND g.flag=0 AND ${ew.sqlSegment}")
    List<MarketMerchantCard> selectCard(@Param(Constants.WRAPPER)QueryWrapper<MarketMerchantCard> query);

}
