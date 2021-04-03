package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.InUserCoupon;
import com.gs.lshly.biz.support.user.mapper.UserCouponDTOMapper;
import com.gs.lshly.biz.support.user.repository.InUserCouponRepository;
import org.springframework.stereotype.Service;

/**
 * @Author yangxi
 * @create 2021/3/30 9:29
 */
@Service
public class InUserCouponRepositoryImpl extends ServiceImpl<UserCouponDTOMapper, InUserCoupon>implements InUserCouponRepository {
}
