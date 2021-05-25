package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.CtccPtActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author yangxi
 * @create 2021/5/8 10:28
 */
@Mapper
public interface CtccPtActivityMapper extends BaseMapper<CtccPtActivity> {

    @Select("select id, name, start_time, end_time, deduction_type, limit_type, limit_count from gs_ctcc_pt_activity where ${ew.sqlSegment}")
    IPage<CtccPtActivity> queryList(IPage<CtccPtActivity> page, @Param(Constants.WRAPPER) QueryWrapper<CtccPtActivity> queryWrapper);

    @Select("select brand_name from gs_goods_brand where id = (select brand_id from gs_goods_info where id = #{goodsId})")
    String getBrandNameByGoodsId(@Param("goodsId") String goodsId);

}
