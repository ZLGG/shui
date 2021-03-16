package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.Activity;
import com.gs.lshly.biz.support.user.mapper.ActivityMapper;
import com.gs.lshly.biz.support.user.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-24
 */
@Service
public class ActivityRepositoryImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityRepository {

}
