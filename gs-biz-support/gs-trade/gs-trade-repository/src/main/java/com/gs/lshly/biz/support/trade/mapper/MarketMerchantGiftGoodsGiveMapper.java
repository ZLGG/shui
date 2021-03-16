package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoodsGive;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家满赠促销关联赠品 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-09
 */
public interface MarketMerchantGiftGoodsGiveMapper extends BaseMapper<MarketMerchantGiftGoodsGive> {

    @Select("select mg.`scount_rule`,mpu.goods_id spu_id,mpg.`sku_id` give_sku_id, mpg.total give_quantity from gs_market_merchant_gift mg\n" +
            " inner join gs_market_merchant_gift_goods mpu on mg.id=mpu.`gift_id`\n" +
            " inner join gs_market_merchant_gift_goods_give mpg on mg.id=mpg.`gift_id`\n" +
            " where mg.state = 20 and mg.flag=0 and mpu.flag=0 and terminal like '%${terminal}%'\n" +
            "  and mg.valid_start_time <= sysdate() and mg.valid_end_time >= sysdate()\n" +
            "  and mpu.goods_id in ('${spuIds}') order by rand() limit 1")
    List<CommonMarketDTO.SkuGive> activeSpuGift(@Param("spuIds") String spuIds, @Param("terminal") Integer terminal);
}
