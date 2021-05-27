package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
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
public interface InUserCouponMapper extends BaseMapper<InUserCoupon> {

    @Update("update gs_in_vip_coupon set coupon_status = 2 where coupon_id = #{couponId}")
    void updateCouponStatus(@Param("couponId") Long couponId);

    @Select("SELECT count(id) FROM gs_in_vip_coupon WHERE user_id = #{userId} and DATE_FORMAT(start_time,'%Y-%m')=date_format(CURDATE(),'%Y-%m') and coupon_type = 1")
    Integer selectIsShare(@Param("userId") String userId);

    @Select("select end_time, type from gs_user_ctcc_in where user_id = #{userId} and status = 1")
    List<BbcInUserCouponVO.CardList> getCardList(@Param("userId") String userId);

    @Select("select coupon_id, coupon_desc, after_date, effective_date, deduction_amount, use_threshold where coupon_type = 1 and audit_status = 1 and type = '购买'")
    List<BbcInUserCouponVO.Coupon> queryCouponByBought();

    @Select("select coupon_id, coupon_desc, after_date, effective_date, deduction_amount, use_threshold where coupon_type = 1 and audit_status = 1 and type = '分享'")
    List<BbcInUserCouponVO.Coupon> queryCouponByShare();
}

