package com.gs.lshly.biz.support.trade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.Coupon;

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

    /**
     * 查询当前商品可以用到的优惠劵
     * @param goodsId
     * @return
     */
    @Select("SELECT	* FROM gs_coupon WHERE coupon_id in (SELECT	coupon_id FROM gs_coupon_goods_relation	WHERE level_id = '${goodsId}') and coupon_status != 2 and audit_status = 1 and flag = 0")
    List<Coupon> listByGoodsId(@Param("goodsId")String goodsId);
    
    /**
     * 跟据类型查询IN会员优惠券列表
     * @param inCouponType
     * @return
     */
    @Select("select * from gs_coupon where coupon_type = 1 and flag = 0 and coupon_status = 0 and audit_status = 1 and level = 1 and in_coupon_type = ${inCouponType}")
    List<Coupon> listInCouponByType(@Param("inCouponType")Integer inCouponType);
    
}
