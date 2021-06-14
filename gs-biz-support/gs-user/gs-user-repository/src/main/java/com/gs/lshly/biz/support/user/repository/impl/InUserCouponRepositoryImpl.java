package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
import com.gs.lshly.biz.support.user.mapper.UserCouponDTOMapper;
import com.gs.lshly.biz.support.user.repository.InUserCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yangxi
 * @create 2021/3/30 9:29
 */
@Service
public class InUserCouponRepositoryImpl extends ServiceImpl<UserCouponDTOMapper, InUserCoupon>implements InUserCouponRepository {
    @Autowired
    private UserCouponDTOMapper userCouponMapper;

    @Override
    public Boolean isInUserGoods(String goodsId) {
        Integer isInGoods = userCouponMapper.isInGoods(goodsId);
        if (isInGoods == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean getMyCouponByGoodsId(String goodsId, String couponId) {
        Integer isExist = userCouponMapper.getMyCouponByGoodsId(goodsId,couponId);
        if (isExist == 0) {
            return false;
        }
        return true;
    }
}
