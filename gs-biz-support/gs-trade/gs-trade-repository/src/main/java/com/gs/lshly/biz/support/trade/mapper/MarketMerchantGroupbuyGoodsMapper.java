package com.gs.lshly.biz.support.trade.mapper;

import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuyGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家团购促销关联商品 Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-10
 */
public interface MarketMerchantGroupbuyGoodsMapper extends BaseMapper<MarketMerchantGroupbuyGoods> {

    @Select("select sku.`goods_id` spu_id, sku.sku_id sku_tmp_id, min(sku.`groupbuy_sale_sku_price`) sku_tmp_price from gs_market_merchant_groupbuy mg\n" +
            " inner join gs_market_merchant_groupbuy_goods_sku sku on mg.id=sku.`groupbuy_id`\n" +
            " where mg.state = 20 and mg.flag=0 and sku.flag=0 and terminal like '%${terminal}%'\n" +
            "  and mg.valid_start_time <= sysdate() and mg.valid_end_time >= sysdate()\n" +
            "  and sku.goods_id in ('${spuIds}')\n" +
            " group by sku.goods_id, sku.sku_id")
    List<CommonMarketDTO.MarketSku> activeSpuPrice(@Param("spuIds") String spuIds, @Param("terminal") Integer terminal);
}
