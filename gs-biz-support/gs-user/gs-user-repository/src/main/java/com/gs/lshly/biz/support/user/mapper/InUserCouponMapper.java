package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.user.entity.UserCoupon;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author yangxi
 * @create 2021/3/22 15:12
 */
@Repository
public interface InUserCouponMapper extends BaseMapper<UserCoupon> {

    @Update("update gs_user_coupon set coupon_status = 2 where coupon_id = #{couponId}")
    void updateCouponStatus(@Param("couponId") Long couponId);

    @Select("SELECT count(id) FROM gs_user_coupon WHERE user_id = #{userId} and DATE_FORMAT(start_time,'%Y-%m')=date_format(CURDATE(),'%Y-%m') and coupon_type = 1")
    Integer selectIsShare(@Param("userId") String userId);

    @Select("select end_time, type from gs_user_ctcc_in where user_id = #{userId} and status = 1")
    List<BbcInUserCouponVO.CardList> getCardList(@Param("userId") String userId);

    @Select("select coupon_id, coupon_desc, after_date, effective_date, deduction_amount, use_threshold where coupon_type = 1 and audit_status = 1 and coupon_label = 1")
    List<BbcInUserCouponVO.Coupon> queryCouponByBought();

    @Select("select coupon_id, coupon_desc, after_date, effective_date, deduction_amount, use_threshold where coupon_type = 1 and audit_status = 1 and coupon_label = 2")
    List<BbcInUserCouponVO.Coupon> queryCouponByShare();

    @Select("select c.coupon_id, c.start_time, c.end_time, c.deduction_amount, c.deduction_points, c.use_threshold as minPrice, c.coupon_desc, c.coupon_name from gs_coupon c where ${ew.sqlSegment}")
    List<BbcInUserCouponVO.GoodsCouponListVO> getGoodsCoupon(@Param(Constants.WRAPPER) QueryWrapper<BbcInUserCouponVO.Coupon> boost);

    @Select("select c.coupon_id, c.start_time, c.end_time, c.deduction_amount, c.deduction_points, c.use_threshold as minPrice, c.coupon_desc, c.coupon_name from gs_coupon c left join gs_coupon_goods_relation r on r.coupon_id = c.coupon_id where ${ew.sqlSegment}")
    List<BbcInUserCouponVO.GoodsCouponListVO> getAllGoodsCoupon(@Param(Constants.WRAPPER) QueryWrapper<BbcInUserCouponVO.Coupon> boost);
}

