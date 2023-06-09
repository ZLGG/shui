package com.gs.lshly.biz.support.trade.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.entity.CtccCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 电信专题分类 Mapper 接口
 * </p>
 *
 * @author yingjun
 * @since 2021-04-20
 */
public interface CtccCategoryMapper extends BaseMapper<CtccCategory> {

    @Select("select name from gs_ctcc_category where id = (select category_id from gs_ctcc_pt_activity_goods where flag = 0 and goods_id = #{goodsId} limit 1)")
    String getCtccCategoryName(@Param("goodsId") String goodsId);

    @Select("select goods_state from gs_ctcc_pt_activity_goods where goods_id = #{id}")
    Integer getGoodsState(@Param("id") String id);
}
