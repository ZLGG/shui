package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
import com.gs.lshly.biz.support.user.mapper.InUserCouponMapper;
import com.gs.lshly.biz.support.user.mapper.UserCouponMapper;
import com.gs.lshly.biz.support.user.repository.InUserCouponRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcInUserCouponService;
import com.gs.lshly.common.enums.InUserCouponPriceEnum;
import com.gs.lshly.common.enums.InUserCouponStatusEnum;
import com.gs.lshly.common.enums.user.InUserCouponTypeEnum;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
@Component
public class BbcInUserCouponServiceImpl implements IBbcInUserCouponService {

    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private InUserCouponRepository couponRepository;

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

    @Override
    public void getCouponByBuy(BbbInUserCouponQTO.BuyCouponQTO qto) {
        List<InUserCoupon> dtoList = new ArrayList<>();
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.三十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.五十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.九十九抵扣券.getCode())));
        couponRepository.saveBatch(dtoList);
    }

    private InUserCoupon generateCoupon(BbbInUserCouponQTO.BuyCouponQTO qto, BigDecimal money) {
        // 判断in会员类型 0-年 1-月
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getNextYearDate(startTime);
        if (1 == qto.getVipType()) {
            endTime = DateUtils.getNextMonthDate(startTime);
        }
        InUserCoupon dto = new InUserCoupon()
                .setCouponPrice(money)
                .setCouponDesc("仅限in会员精品专区使用")
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setUserId(qto.getUserId())
                .setCouponStatus(InUserCouponStatusEnum.未使用.getCode())
                .setCouponType(InUserCouponTypeEnum.购买赠送.getCode())
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        dto.setUserId(qto.getUserId());
        return dto;
    }
}
