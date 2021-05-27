package com.gs.lshly.biz.support.trade.repository.impl;

import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.gs.lshly.biz.support.trade.mapper.CouponMapper;
import com.gs.lshly.biz.support.trade.repository.ICouponRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author chenyang
 * @since 2021-05-25
 */
@Service
public class CouponRepositoryImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponRepository {

}
