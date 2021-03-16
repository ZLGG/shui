package com.gs.lshly.biz.support.user.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserSignIn;
import com.gs.lshly.biz.support.user.mapper.UserSignInMapper;
import com.gs.lshly.biz.support.user.repository.IUserSignInRepository;
import com.gs.lshly.common.struct.BaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2021-01-13
*/
@Service
public class UserSignInRepositoryImpl extends ServiceImpl<UserSignInMapper, UserSignIn> implements IUserSignInRepository {
    @Autowired
    private UserSignInMapper userSignInMapper;

    @Override
    public UserSignIn seletcNowDay(QueryWrapper<BaseDTO> qw) {
        return userSignInMapper.seletcNowDay(qw);
    }
}