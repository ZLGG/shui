package com.gs.lshly.biz.support.trade.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSku;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商家报名商品(sku) Mapper 接口
 * </p>
 *
 * @author zdf
 * @since 2020-12-02
 */
public interface MarketPtActivityGoodsSkuMapper extends BaseMapper<MarketPtActivityGoodsSku> {

    @Select("select sku.goods_id spu_id, sku.sku_id sku_tmp_id, min(sku.`activity_sale_sku_price`) sku_tmp_price from gs_market_pt_activity pa\n" +
            " inner join gs_market_pt_activity_merchant am on pa.id=am.activity_id\n" +
            " inner join gs_market_pt_activity_goods_sku sku on sku.`activity_id`=pa.id\n" +
            " where sku.flag=0 and pa.flag=0 and am.flag=0\n" +
            "  and pa.activity_start_time <= sysdate() and pa.activity_end_time >= sysdate()\n" + //活动已开售
            "  and am.state = 10\n" + //已审核
            "  and sku.`activity_sale_sku_price` > 0\n" +
            "  and sku.sku_id in ('${skuIds}')\n" +
            "  group by sku.goods_id, sku.sku_id")
    List<CommonMarketDTO.MarketSku> activeSkuPrice(@Param("skuIds") String ids);
}
