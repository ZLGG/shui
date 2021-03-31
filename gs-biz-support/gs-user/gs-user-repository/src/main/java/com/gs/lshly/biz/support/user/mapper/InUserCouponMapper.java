package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBBBInUserCouponDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import org.apache.ibatis.annotations.Insert;
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
public interface InUserCouponMapper extends BaseMapper<PCBBBInUserCouponVO> {

    @Update("update gs_in_vip_coupon set coupon_status = 2 where coupon_id = #{couponId}")
    void updateCouponStatus(@Param("couponId") Long couponId);

    @Select("SELECT count(coupon_id) FROM gs_in_vip_coupon WHERE user_id = #{userId} and DATE_FORMAT(start_time,'%Y-%m')=date_format(CURDATE(),'%Y-%m') and coupon_type = 1")
    Integer selectIsShare(@Param("userId") String userId);
}

