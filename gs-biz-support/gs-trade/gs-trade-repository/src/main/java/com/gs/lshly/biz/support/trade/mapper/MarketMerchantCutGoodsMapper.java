package com.gs.lshly.biz.support.trade.mapper;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家满减促销关联商品 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-08
 */
public interface MarketMerchantCutGoodsMapper extends BaseMapper<MarketMerchantCutGoods> {

    @Select("select mc.cut_rule def_rule_str,mpu.goods_id spu_id from gs_market_merchant_cut mc\n" +
            " inner join gs_market_merchant_cut_goods mpu on mc.id=mpu.`cut_id`\n" +
            " where mc.state = 20 and mc.flag=0 and mpu.flag=0 and terminal like '%${terminal}%'\n" +
            "  and mc.valid_start_time <= sysdate() and mc.valid_end_time >= sysdate()\n" +
            "  and mpu.goods_id in ('${spuIds}')")
    List<CommonMarketDTO.SkuCutRule> activeCutSpuRule(@Param("spuIds") String spuIds, @Param("terminal") Integer terminal);
}
