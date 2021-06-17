package com.gs.lshly.biz.support.commodity.mapper;

import com.gs.lshly.biz.support.commodity.entity.GoodsTempalteTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 商品仓储物流配置临时表 Mapper 接口
 * </p>
 *
 * @author chenyang
 * @since 2021-06-09
 */
public interface GoodsTempalteTempMapper extends BaseMapper<GoodsTempalteTemp> {

    @Select("select template_name from gs_goods_tempalte_temp t " +
            "left join gs_stock_template st on t.template_id = st.id " +
            "where t.flag=0 and t.goods_id = #{goodsId}"
    )
    String getTemplateName(@Param("goodsId") String goodsId);
}
