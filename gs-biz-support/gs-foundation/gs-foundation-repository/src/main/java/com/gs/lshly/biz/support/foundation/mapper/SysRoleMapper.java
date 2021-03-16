package com.gs.lshly.biz.support.foundation.mapper;

import com.gs.lshly.biz.support.foundation.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 平台角色 Mapper 接口
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select r.id roleId,r.name roleName from gs_sys_user_role ur" +
            " inner join gs_sys_role r on ur.role_id=r.id" +
            " where ur.user_id=#{userId}")
    List<SysUserVO.UserRoleVO> userRoles(@Param("userId") String userId);
}
