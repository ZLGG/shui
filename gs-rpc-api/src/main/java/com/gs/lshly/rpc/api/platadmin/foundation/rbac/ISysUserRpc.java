package com.gs.lshly.rpc.api.platadmin.foundation.rbac;


import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;

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
    
    /**
     * 跟据手机号码获取验证码
     * @param dto
     */
    void getPhoneValidCode(SysUserDTO.GetPhoneValidCodeDTO dto);
    
    /**
     * 
     * @param dto
     */
    AuthDTO login(SysUserDTO.LoginDTO dto);
    
    /**
     * 验证手机号码密码验证码
     * @param dto
     * @return
     */
    Boolean checkPhoneCode(SysUserDTO.CheckDTO dto);
    
    /**
     * 跟据用户名查询用户
     * @param name
     * @return
     */
    SysUserVO.DetailVO getSysUserByName(String name);
}
