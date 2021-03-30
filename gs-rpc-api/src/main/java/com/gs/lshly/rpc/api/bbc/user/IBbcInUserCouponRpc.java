package com.gs.lshly.rpc.api.bbc.user;

import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:14
 */
public interface IBbcInUserCouponRpc {
    List<BbcInUserCouponVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto);

    void getCouponByBuy(BbbInUserCouponQTO.BuyCouponQTO qto);

    void getCouponByShare(BbbInUserCouponQTO.ShareCouponQTO qto);
}
