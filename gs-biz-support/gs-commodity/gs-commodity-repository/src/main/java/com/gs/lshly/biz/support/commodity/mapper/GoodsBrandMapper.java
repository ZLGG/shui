package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsBrand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-09-22
 */
public interface GoodsBrandMapper extends BaseMapper<GoodsBrand> {
    @Select("select distinct(ggb.id), ggb.brand_name, ggb.brand_alias, ggb.brand_logo, ggb.idx, ggb.operator, ggb.cdate, ggb.udate, ggb.flag" +
            " from gs_goods_info ggi left join gs_goods_brand ggb on ggi.brand_id = ggb.id\n" +
            "where ggi.flag = 0 and ggb.flag = 0 and ${ew.sqlSegment}")
    IPage<GoodsBrand> listByCategory(IPage<GoodsBrand> page, @Param(value = "ew") QueryWrapper<GoodsBrand> qw);
}
