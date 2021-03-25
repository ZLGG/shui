package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.mapper.InUserCouponMapper;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5InUserCouponService;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBBBInUserCouponService;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
@Component
public class BbbH5InUserCouponServiceImpl implements IBbbH5InUserCouponService {

    @Autowired
    private InUserCouponMapper inUserCouponMapper;

    @Override
    public List<PCBBBInUserCouponVO> queryInUserCouponList(BbbInUserCouponQTO.QTO qto) {
        List<PCBBBInUserCouponVO> userCouponList = inUserCouponMapper.selectList(new QueryWrapper<PCBBBInUserCouponVO>()
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
