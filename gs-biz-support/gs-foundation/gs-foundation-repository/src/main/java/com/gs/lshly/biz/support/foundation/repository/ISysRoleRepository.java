package com.gs.lshly.biz.support.foundation.repository;

import com.gs.lshly.biz.support.foundation.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.foundation.mapper.SysRoleMapper;

/**
 * <p>
 * 平台角色 服务类
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
public interface ISysRoleRepository extends IService<SysRole> {

    SysRoleMapper baseMapper();

}
