package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.mapper.UserMapper;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User> implements IUserRepository {


}
