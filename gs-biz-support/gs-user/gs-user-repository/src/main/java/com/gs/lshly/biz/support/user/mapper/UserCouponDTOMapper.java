package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBBBInUserCouponDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import org.springframework.stereotype.Repository;

/**
 * @Author yangxi
 * @create 2021/3/25 15:38
 */
@Repository
public interface UserCouponDTOMapper extends BaseMapper<InUserCoupon> {
}
