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
    List<BbcInUserCouponVO.ListVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto);

    void getCouponByBuy(BbcInUserCouponQTO.BuyCouponQTO qto);

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

    /**
     * 查看当前商品可领取的优惠券
     * @param goodsId
     * @return
     */
    List<BbcInUserCouponVO.GoodsCouponListVO> getGoodsCoupon(String goodsId);
}
