package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
public interface IBbcInUserCouponService {
    /**
     * 查询in会员优惠券列表
     * @param qto
     * @return
     */
    List<BbcInUserCouponVO.ListVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto);

    /**
     * 购买in会员获得优惠券
     * @param qto
     */
    void getCouponByBuy(BbcInUserCouponQTO.BuyCouponQTO qto);

    /**
     * in会员分享小程序获得优惠券
     * @param qto
     */
    void getCouponByShare(BbcInUserCouponQTO.ShareCouponQTO qto);

    /**
     * 获取会员卡列表
     * @param qto
     * @return
     */
    List<BbcInUserCouponVO.CardList> getCardList(BbcInUserCouponQTO.QTO qto);

    /**
     * 获取当前可用优惠券列表
     * @param qto
     * @return
     */
    List<BbcInUserCouponVO.MyCouponListVO> getMyCouponToUse(BbcInUserCouponQTO.MyCouponQTO qto);
}
