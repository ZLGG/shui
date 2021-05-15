package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
public interface ISysUserService {

    void delete(SysUserDTO.IdDTO dto);

    void enable(SysUserDTO.IdDTO dto);

    void disable(SysUserDTO.IdDTO dto);

    SysUserVO.DetailVO detail(SysUserDTO.IdDTO dto);

    void changePassword(SysUserDTO.EditPasswordDTO dto);

    void uniqueCheck(SysUserDTO.ETO dto);

    PageData<SysUserVO.ListVO> pageData(SysUserQTO.QTO qoDTO);

    void saveUser(SysUserDTO.ETO dto);

    void editUser(SysUserDTO.ETO dto);

    AuthDTO findByUserName(String username);

    List<SysUserVO.UserRoleVO> roles(SysUserDTO.IdDTO dto);

    void addUserRolePermit(SysUserDTO.UserRoleETO eto);

    void deleteUserRolePermit(SysUserDTO.UserRoleETO eto);
    
    /**
     * 跟据手机号码获取验证码
     * @param dto
     */
    void getPhoneValidCode(SysUserDTO.GetPhoneValidCodeDTO dto) ;
    
    /**
     * 登录登录
     * @param dto
     */
    AuthDTO login(SysUserDTO.LoginDTO dto);
    
    /**
     * 验证手机号码，密码
     * @param dto
     * @return
     */
    Boolean checkPhoneCode(SysUserDTO.CheckDTO dto);
}
