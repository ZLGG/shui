package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBBBInUserCouponDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author yangxi
 * @create 2021/3/25 15:38
 */
@Repository
public interface UserCouponDTOMapper extends BaseMapper<InUserCoupon> {
    @Select("select count(1) from gs_coupon_goods_relation where coupon_id = #{couponId} and level_id = #{levelId}")
    Integer getMyCouponByGoodsId(@Param("levelId") String levelId, @Param("couponId") Long couponId);

    @Select("select count(1) from gs_goods_info where is_in_member_gift = 1 and id = #{goodsId}")
    Integer isInGoods(@Param("goodsId") String goodsId);
}
