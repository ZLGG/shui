package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBBBInUserCouponDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
}

