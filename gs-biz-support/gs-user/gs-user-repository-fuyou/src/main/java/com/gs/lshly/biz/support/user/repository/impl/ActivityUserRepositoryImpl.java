package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.ActivityUser;
import com.gs.lshly.biz.support.user.mapper.ActivityUserMapper;
import com.gs.lshly.biz.support.user.repository.IActivityUserRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动关联会员表 服务实现类
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-26
 */
@Service
public class ActivityUserRepositoryImpl extends ServiceImpl<ActivityUserMapper, ActivityUser> implements IActivityUserRepository {

}
