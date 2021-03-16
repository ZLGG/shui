package com.gs.lshly.biz.support.user.repository.impl;

import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.mapper.UserPrivateUserMapper;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xxfc
 * @since 2020-10-10
 */
@Service
public class UserPrivateUserRepositoryImpl extends ServiceImpl<UserPrivateUserMapper, UserPrivateUser> implements IUserPrivateUserRepository {

}
