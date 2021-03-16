package com.gs.lshly.biz.support.user.repository.impl;

import com.gs.lshly.biz.support.user.entity.UserWallet;
import com.gs.lshly.biz.support.user.mapper.UserWalletMapper;
import com.gs.lshly.biz.support.user.repository.IUserWalletRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 富友用户钱包 服务实现类
 * </p>
 *
 * @author zhaoqiang
 * @since 2021-01-05
 */
@Service
public class UserWalletRepositoryImpl extends ServiceImpl<UserWalletMapper, UserWallet> implements IUserWalletRepository {

}
