package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.mapper.InUserCouponMapper;
import com.gs.lshly.biz.support.user.mapper.UserCouponMapper;
import com.gs.lshly.biz.support.user.service.bbc.IBbcInUserCouponService;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
@Component
public class BbcInUserCouponServiceImpl implements IBbcInUserCouponService {

    @Autowired
    private UserCouponMapper userCouponMapper;

    @Override
    public List<BbcInUserCouponVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto) {
        List<BbcInUserCouponVO> userCouponList = userCouponMapper.selectList(new QueryWrapper<BbcInUserCouponVO>()
        .eq("user_id",qto.getUserId())
        .eq("coupon_status",0));
        // 判断优惠券是否过期
//        userCouponList.stream().forEach(coupon -> {
//            Boolean result = DateUtils.isLessNowDate(coupon.getEndTime());
//            if (result) {
//                // 修改过期优惠券状态为已过期
//                inUserCouponMapper.updateCouponStatus(coupon.getCouponId());
//                userCouponList.remove(coupon);
//            }
//        });
        return userCouponList;
    }
}
