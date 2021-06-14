package com.gs.lshly.biz.support.commodity.mapper;

import com.gs.lshly.biz.support.commodity.entity.GoodsTempalte;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-10-13
 */
public interface GoodsTempalteMapper extends BaseMapper<GoodsTempalte> {

    @Select("select template_id from gs_goods_tempalte t" +
            " inner join gs_sku_good_info s on t.goods_id=s.good_id" +
            " where t.flag=0 and s.flag=0 and s.id=#{skuId}")
    String getTemplateId(@Param("skuId") String skuId);

    @Select("select count(1) from gs_goods_tempalte t" +
            " inner join gs_sku_good_info s on t.goods_id=s.good_id" +
            " where t.flag=0 and s.flag=0 and t.template_id=#{templateId}")
    Integer getUsedCount(@Param("templateId") String id);

    @Select("select CONCAT(city,county,reals) from gs_stock_address where id=#{id}")
    String queryStockAddress(@Param("id") String id);

    @Select("select template_name from gs_goods_tempalte t " +
           "left join gs_stock_template st on t.template_id = st.id " +
            "where t.flag=0 and t.goods_id = #{goodsId}"
    )
    String getTemplateName(@Param("goodsId") String goodsId);
}
