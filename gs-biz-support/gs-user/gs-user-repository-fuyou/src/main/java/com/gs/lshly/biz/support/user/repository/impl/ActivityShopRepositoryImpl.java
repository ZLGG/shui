package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.ActivityShop;
import com.gs.lshly.biz.support.user.mapper.ActivityShopMapper;
import com.gs.lshly.biz.support.user.repository.IActivityShopRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动关联商家表 服务实现类
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-24
 */
@Service
public class ActivityShopRepositoryImpl extends ServiceImpl<ActivityShopMapper, ActivityShop> implements IActivityShopRepository {

}
