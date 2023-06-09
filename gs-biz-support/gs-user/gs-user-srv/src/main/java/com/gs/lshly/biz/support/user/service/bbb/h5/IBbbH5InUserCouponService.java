package com.gs.lshly.biz.support.user.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
public interface IBbbH5InUserCouponService {
    /**
     * 查询in会员优惠券列表
     * @param qto
     * @return
     */
    List<PCBBBInUserCouponVO> queryInUserCouponList(BbbInUserCouponQTO.QTO qto);
}
