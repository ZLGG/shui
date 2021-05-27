package com.gs.lshly.biz.support.user.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;

/**
 * @Author yangxi
 * @create 2021/3/29 17:48
 * in会员优惠券
 */
public interface InUserCouponRepository extends IService<InUserCoupon> {
    // 查询当前商品个人可用优惠券
    Boolean getMyCouponByGoodsId(String goodsId, Long couponId);
}
