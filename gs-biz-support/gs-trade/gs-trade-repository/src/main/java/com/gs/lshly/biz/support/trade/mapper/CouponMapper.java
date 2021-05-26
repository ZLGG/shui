package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.trade.entity.CtccPtActivity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 优惠券表 Mapper 接口
 * </p>
 *
 * @author chenyang
 * @since 2021-05-25
 */
public interface CouponMapper extends BaseMapper<Coupon> {

    @Select("select coupon_id, coupon_name,coupon_type,coupon_status,coupon_desc,stock_num,channel,audit_status from gs_coupon where ${ew.sqlSegment}")
    IPage<Coupon> queryList(IPage<Coupon> page, @Param(Constants.WRAPPER) QueryWrapper<Coupon> queryWrapper);

}
