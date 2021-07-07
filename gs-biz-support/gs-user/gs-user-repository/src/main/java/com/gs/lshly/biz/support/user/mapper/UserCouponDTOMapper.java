package com.gs.lshly.biz.support.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.entity.UserCoupon;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCouponVO;

/**
 * @Author yangxi
 * @create 2021/3/25 15:38
 */
@Repository
public interface UserCouponDTOMapper extends BaseMapper<UserCoupon> {
    @Select("select count(1) from gs_coupon_goods_relation where coupon_id = #{couponId} and level_id = #{levelId}")
    Integer getMyCouponByGoodsId(@Param("levelId") String levelId, @Param("couponId") String couponId);

    @Select("select count(1) from gs_goods_info where is_in_member_gift = 1 and id = #{goodsId}")
    Integer isInGoods(@Param("goodsId") String goodsId);
    
    @Select("select * from gs_user_coupon where coupon_id ='${couponId}' and user_id='${userId}' and start_time<=NOW() and end_time>=NOW() and coupon_status = 0 ")
    List<BbcUserCouponVO.ListVO> listByCouponId(@Param("couponId") String couponId,@Param("userId")String userId);
}
