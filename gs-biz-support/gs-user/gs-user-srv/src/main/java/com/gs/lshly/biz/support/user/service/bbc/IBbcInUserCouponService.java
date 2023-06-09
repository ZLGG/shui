package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.struct.bbc.user.dto.BbcInUserCouponDTO.CreateDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO.DetailVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCouponVO.ListVO;

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
     * 跟据优惠券查询用户有没有对应的券
     * @param couponId
     * @return
     */
    List<ListVO> listByCouponId(BbcUserCouponQTO.ListByCouponIdQTO qto);
    
    /**
     * 添加IN会员优惠券
     * @param dto
     */
    void createInUserCoupon(CreateDTO dto);
    
    /**
     * 
     * @param couponIds
     * @param userId
     * @param status
     */
    List<String> modifyUserCoupon(List<String> couponIds, String userId, Integer status) ;
    
    DetailVO detailCoupon(String couponId);
}
