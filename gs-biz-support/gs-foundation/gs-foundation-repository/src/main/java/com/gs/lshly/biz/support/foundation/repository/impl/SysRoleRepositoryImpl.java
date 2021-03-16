package com.gs.lshly.biz.support.foundation.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.foundation.entity.SysRole;
import com.gs.lshly.biz.support.foundation.mapper.SysRoleMapper;
import com.gs.lshly.biz.support.foundation.repository.ISysRoleRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台角色 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
@Service
public class SysRoleRepositoryImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleRepository {

    @Override
    public SysRoleMapper baseMapper() {
        return getBaseMapper();
    }
}
