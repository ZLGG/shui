package com.gs.lshly.biz.support.user.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
public interface IPCBBBInUserCouponService {
    /**
     * 查询in会员优惠券列表
     * @param qto
     * @return
     */
    List<PCBBBInUserCouponVO> queryInUserCouponList(BbbInUserCouponQTO.QTO qto);

    /**
     * 购买in会员获得优惠券
     * @param qto
     */
    void getCouponByBuy(BbbInUserCouponQTO.BuyCouponQTO qto);

    /**
     * in会员分享小程序获得优惠券
     * @param qto
     */
    void getCouponByShare(BbbInUserCouponQTO.ShareCouponQTO qto);
}
