package com.gs.lshly.biz.support.user.repository.impl;

import com.gs.lshly.biz.support.user.entity.UserCtccPoint;
import com.gs.lshly.biz.support.user.mapper.UserCtccPointMapper;
import com.gs.lshly.biz.support.user.repository.IUserCtccPointRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员积分余额数据 服务实现类
 * </p>
 *
 * @author yingjun
 * @since 2021-03-30
 */
@Service
public class UserCtccPointRepositoryImpl extends ServiceImpl<UserCtccPointMapper, UserCtccPoint> implements IUserCtccPointRepository {

}
