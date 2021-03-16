package com.gs.lshly.biz.support.foundation.rpc.platadmin.rbac;

import com.gs.lshly.biz.support.foundation.service.platadmin.rbac.ISysUserService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * RPC里面不允许写业务代码：
 * 只允许做三件事情：
 *              1，面向Service做数据准备；
 *              2，调用Service；
 *              3，面向前端解析VO数据；
* @author lxus
* @since 2020/09/14
 */
@DubboService
public class SysUserRpc implements ISysUserRpc {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public PageData<SysUserVO.ListVO> list(SysUserQTO.QTO qoDTO) {
        return sysUserService.pageData(qoDTO);
    }

    @Override
    public void addSysUser(SysUserDTO.ETO dto) {
        sysUserService.saveUser(dto);
    }

    @Override
    public void updateSysUser(SysUserDTO.ETO dto) {
        sysUserService.editUser(dto);
    }

    @Override
    public void deleteSysUser(SysUserDTO.IdDTO dto) {
        sysUserService.delete(dto);
    }

    @Override
    public void enableSysUser(SysUserDTO.IdDTO dto) {
        sysUserService.enable(dto);
    }

    @Override
    public void disableSysUser(SysUserDTO.IdDTO dto) {
        sysUserService.disable(dto);
    }

    @Override
    public SysUserVO.DetailVO getSysUser(SysUserDTO.IdDTO dto) {
        return sysUserService.detail(dto);
    }

    @Override
    public void changePwd(SysUserDTO.EditPasswordDTO dto) {
        sysUserService.changePassword(dto);
    }

    @Override
    public ExportDataDTO export(SysUserQTO.QTO qo) throws Exception {
        PageData<SysUserVO.ListVO> pageData = list(qo);
        return ExcelUtil.treatmentBean(pageData.getContent(), SysUserVO.ListVO.class);
    }

    @Override
    public List<SysUserVO.UserRoleVO> roles(SysUserDTO.IdDTO dto) {
        return sysUserService.roles(dto);
    }

    @Override
    public void addUserRolePermit(SysUserDTO.UserRoleETO eto) {
        sysUserService.addUserRolePermit(eto);
    }

    @Override
    public void deleteUserRolePermit(SysUserDTO.UserRoleETO eto) {
        sysUserService.deleteUserRolePermit(eto);
    }
}
