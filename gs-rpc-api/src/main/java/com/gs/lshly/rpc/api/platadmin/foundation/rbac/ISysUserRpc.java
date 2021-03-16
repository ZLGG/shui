package com.gs.lshly.rpc.api.platadmin.foundation.rbac;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;

import java.util.List;

/**
 *
 * @author lxus
 * @since 2020/9/14
 */
public interface ISysUserRpc {

    PageData<SysUserVO.ListVO> list(SysUserQTO.QTO qoDTO);

    void addSysUser(SysUserDTO.ETO dto);

    void updateSysUser(SysUserDTO.ETO dto);

    void deleteSysUser(SysUserDTO.IdDTO dto);

    void enableSysUser(SysUserDTO.IdDTO dto);

    void disableSysUser(SysUserDTO.IdDTO dto);

    SysUserVO.DetailVO getSysUser(SysUserDTO.IdDTO idDTO);

    void changePwd(SysUserDTO.EditPasswordDTO dto);

    ExportDataDTO export(SysUserQTO.QTO qo) throws Exception;

    List<SysUserVO.UserRoleVO> roles(SysUserDTO.IdDTO dto);

    void addUserRolePermit(SysUserDTO.UserRoleETO eto);

    void deleteUserRolePermit(SysUserDTO.UserRoleETO eto);
}
