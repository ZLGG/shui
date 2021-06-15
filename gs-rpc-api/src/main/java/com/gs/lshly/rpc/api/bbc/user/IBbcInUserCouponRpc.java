package com.gs.lshly.rpc.api.bbc.user;

import java.util.List;

import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCouponVO.ListVO;

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
    List<BbcInUserCouponVO.CardList> getCardList(BbcInUserCouponQTO.CardQTO qto);

    /**
     * 获取当前可用优惠券列表
     * @param qto
     * @return
     */
    List<BbcInUserCouponVO.MyCouponListVO> getMyCouponToUse(BbcInUserCouponQTO.MyCouponQTO qto);

    /**
     * 查看当前商品可领取的优惠券
     * @return
     */
    List<BbcInUserCouponVO.GoodsCouponListVO> getGoodsCoupon(BbcInUserCouponQTO.GoodsCouponQTO qto);
    
    /**
     * 跟据优惠券ID查询
     * @param couponId
     * @return
     */
    List<ListVO> listByCouponId(BbcUserCouponQTO.ListByCouponIdQTO qto);
}
