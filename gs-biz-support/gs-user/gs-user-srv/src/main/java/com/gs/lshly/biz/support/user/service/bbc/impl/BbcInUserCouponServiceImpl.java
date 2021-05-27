package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.mapper.InUserCouponMapper;
import com.gs.lshly.biz.support.user.mapper.UserMapper;
import com.gs.lshly.biz.support.user.repository.InUserCouponRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcInUserCouponService;
import com.gs.lshly.common.enums.InUserCouponStatusEnum;
import com.gs.lshly.common.enums.user.InUserCouponTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.ListUtil;
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
    private InUserCouponRepository couponRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InUserCouponMapper inUserCouponMapper;

    @Override
    public List<BbcInUserCouponVO.ListVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto) {
        List<InUserCoupon> userCouponList = couponRepository.list(new QueryWrapper<InUserCoupon>()
                .eq("user_id",qto.getJwtUserId())
                .eq("coupon_status",0));
        List<BbcInUserCouponVO.ListVO> resultList = ListUtil.listCover(BbcInUserCouponVO.ListVO.class,userCouponList);
        resultList.forEach(coupon ->{
            // 计算距离过期还剩的天数
            coupon.setExpireDays(DateUtils.betweenStartAndEnd(coupon.getStartTime(),coupon.getEndTime()));
        });
        return resultList;
    }

    @Override
    public void getCouponByBuy(BbbInUserCouponQTO.BuyCouponQTO qto) {
        // 判断用户是否为in会员
        User user = userMapper.selectById(qto.getUserId());
        if (0 == user.getIsInUser()) {
            throw new BusinessException("非in会员用户不能获取in会员优惠券");
        }
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getHalfNextYearDate(startTime);
        if (1 == qto.getVipType()) {
            endTime = DateUtils.getNextMonthDate(startTime);
        }
        // 去运营平台获取可发放的优惠券
        List<BbcInUserCouponVO.Coupon> couponList = inUserCouponMapper.queryCouponByBought();
        // 根据运营平台发放规则发放优惠券
        List<InUserCoupon> list = new ArrayList<>();
        for (BbcInUserCouponVO.Coupon coupon : couponList) {
            InUserCoupon inUserCoupon = new InUserCoupon()
                    .setUserId(qto.getUserId())
                    .setCouponDesc(coupon.getCouponDesc())
                    .setCouponStatus(InUserCouponStatusEnum.未使用.getCode())
                    .setCouponType(InUserCouponTypeEnum.购买赠送.getCode())
                    .setCouponPrice(coupon.getDeductionAmount())
                    .setMinPrice(coupon.getUseThreshold())
                    .setCreateTime(new Date())
                    .setModifyTime(new Date())
                    .setStartTime(startTime)
                    .setEndTime(endTime);
            list.add(inUserCoupon);
        }
        couponRepository.saveBatch(list);
/*        // 自动发放优惠券
        List<InUserCoupon> dtoList = new ArrayList<>();
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.三十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.五十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.九十九抵扣券.getCode())));
        couponRepository.saveBatch(dtoList);*/
    }

    @Override
    public List<BbcInUserCouponVO.CardList> getCardList(BbcInUserCouponQTO.QTO qto) {
        List<BbcInUserCouponVO.CardList> cardList = inUserCouponMapper.getCardList(qto.getJwtUserId());
        return cardList;
    }

    @Override
    public void getCouponByShare(BbbInUserCouponQTO.ShareCouponQTO qto) {
        // 判断用户是否为in会员
        User user = userMapper.selectById(qto.getUserId());
        if (0 == user.getIsInUser()) {
            throw new BusinessException("非in会员用户转发不能获取in会员优惠券");
        }
        // 判断本月是否已转发获取过优惠券
        Integer num = inUserCouponMapper.selectIsShare(qto.getUserId());
        if (num != 0) {
            throw new BusinessException("本月已分享获得过优惠券，不可重复获得");
        }
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getNextMonthDate(startTime);
        // 去运营平台获取可发放的优惠券
        List<BbcInUserCouponVO.Coupon> couponList = inUserCouponMapper.queryCouponByShare();
        // 根据运营平台发放规则发放优惠券
        List<InUserCoupon> list = new ArrayList<>();
        for (BbcInUserCouponVO.Coupon coupon : couponList) {
            InUserCoupon inUserCoupon = new InUserCoupon()
                    .setUserId(qto.getUserId())
                    .setCouponDesc(coupon.getCouponDesc())
                    .setCouponStatus(InUserCouponStatusEnum.未使用.getCode())
                    .setCouponType(InUserCouponTypeEnum.购买赠送.getCode())
                    .setCouponPrice(coupon.getDeductionAmount())
                    .setMinPrice(coupon.getUseThreshold())
                    .setCreateTime(new Date())
                    .setModifyTime(new Date())
                    .setStartTime(startTime)
                    .setEndTime(endTime);
            list.add(inUserCoupon);
        }
        couponRepository.saveBatch(list);

       /* List<InUserCoupon> userCouponList = new ArrayList<>();
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二十抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.三十抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.五十抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.九十九抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二百抵扣券.getCode())));
        couponRepository.saveBatch(userCouponList);*/
    }

    private InUserCoupon getCoupon(BbbInUserCouponQTO.ShareCouponQTO qto, BigDecimal money) {
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getNextMonthDate(startTime);
        InUserCoupon dto = new InUserCoupon()
                .setCouponPrice(money)
                .setCouponDesc("仅限in会员精品专区使用")
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setUserId(qto.getUserId())
                .setCouponStatus(InUserCouponStatusEnum.未使用.getCode())
                .setCouponType(InUserCouponTypeEnum.分享赠送.getCode())
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        dto.setUserId(qto.getUserId());
        return dto;
    }

    private InUserCoupon generateCoupon(BbbInUserCouponQTO.BuyCouponQTO qto, BigDecimal money) {
        // 判断in会员类型 0-年 1-月
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getHalfNextYearDate(startTime);
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
