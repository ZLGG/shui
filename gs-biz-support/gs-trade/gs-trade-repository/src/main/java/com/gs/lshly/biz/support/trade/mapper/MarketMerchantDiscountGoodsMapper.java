package com.gs.lshly.biz.support.trade.mapper;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家满折促销关联商品 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface MarketMerchantDiscountGoodsMapper extends BaseMapper<MarketMerchantDiscountGoods> {

    @Select("select md.scount_rule def_rule_str,mpu.goods_id spu_id from gs_market_merchant_discount md\n" +
            " inner join gs_market_merchant_discount_goods mpu on md.id=mpu.`discount_id`\n" +
            " where md.state = 20 and md.flag=0 and mpu.flag=0 and terminal like '%${terminal}%'\n" +
            "  and md.valid_start_time <= sysdate() and md.valid_end_time >= sysdate()\n" +
            "  and mpu.goods_id in ('${spuIds}')")
    List<CommonMarketDTO.SkuDiscountRule> activeDiscountSpuRule(@Param("spuIds") String spuIds, @Param("terminal") Integer terminal);
}
