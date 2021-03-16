package com.gs.lshly.biz.support.foundation.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SysUser;
import com.gs.lshly.biz.support.foundation.mapper.SysUserMapper;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRepository;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  仓储服务
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class SysUserRepositoryImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserRepository {

}
