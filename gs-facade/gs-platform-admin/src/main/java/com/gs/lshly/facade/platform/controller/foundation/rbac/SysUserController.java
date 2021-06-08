package com.gs.lshly.facade.platform.controller.foundation.rbac;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysUserRpc;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/platform/sysUser")
@Api(tags = "RBAC账号管理")
@Module(code = "listSysUser",parent = "set",name = "账号权限管理列表",index = 1)
public class SysUserController {

    @DubboReference
    private ISysUserRpc userRpc;

    @ApiOperation("账号列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<SysUserVO.ListVO>> list(SysUserQTO.QTO qo) {
        return ResponseData.data(userRpc.list(qo));
    }

    @ApiOperation("新增平台账号")
    @Log(module = "平台账号管理", func = "新增平台账号")
    @PostMapping("")
    @Func(code = "add",name = "增")
    public ResponseData<Void> add(@Valid @RequestBody SysUserDTO.ETO dto) {
        //新增用户,初始密码必填
        /*String password = dto.getPwd();
        if(StrUtil.isBlank(password) || password.trim().length() < 6 || password.trim().length() > 32) {
            throw new BusinessException("密码长度需要在6至32位间");
        }*/
        dto.setId(null);
        userRpc.addSysUser(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("修改平台账号")
    @Log(module = "平台账号管理", func = "修改平台账号")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody SysUserDTO.ETO dto) {
        dto.setPwd(null).setId(id);
        //新增用户,初始密码必填
        String password = dto.getPwd();
        if(StrUtil.isNotBlank(password) && ( password.trim().length() < 6 || password.trim().length() > 32)) {
            throw new BusinessException("密码长度需要在6至32位间");
        }
        userRpc.updateSysUser(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除平台账号")
    @Log(module = "平台账号管理", func = "删除平台账号")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete",name = "删")
    public ResponseData<Void> delete(@PathVariable String id) {
        SysUserDTO.IdDTO dto = new SysUserDTO.IdDTO(id);
        userRpc.deleteSysUser(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("启用")
    @PostMapping(value = "/{id}/enable")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> enable(@PathVariable String id) {
        SysUserDTO.IdDTO dto = new SysUserDTO.IdDTO(id);
        userRpc.enableSysUser(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("禁用")
    @PostMapping(value = "/{id}/disable")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> disable(@PathVariable String id) {
        SysUserDTO.IdDTO dto = new SysUserDTO.IdDTO(id);
        userRpc.disableSysUser(dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("用户详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<SysUserVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(userRpc.getSysUser(new SysUserDTO.IdDTO(id)));
    }

    @ApiOperation("修改平台账号密码")
    @Log(module = "平台账号管理", func = "修改平台账号密码")
    @RequestMapping(value = "/password", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Func(code = "edit",name = "改")
    public ResponseData<Void> change(@Valid @RequestBody SysUserDTO.EditPasswordDTO dto) {
        if (!dto.getConfirmPwd().equals(dto.getNewPwd())) {
            return ResponseData.success("新密码和确认密码不匹配");
        }
        userRpc.changePwd(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("导出平台账号列表")
    @Log(module = "平台账号管理", func = "导出平台账号列表")
    @GetMapping(value = "/export")
    @Func(code = "view",name = "查")
    public void export(SysUserQTO.QTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = userRpc.export(qo);
        ExcelUtil.export(exportData, response);

    }

    @ApiOperation("获取账号角色集")
    @GetMapping("/userRoles/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<SysUserVO.UserRoleVO> userRoles(@PathVariable String id) {
        SysUserDTO.IdDTO dto = new SysUserDTO.IdDTO(id);
        List<SysUserVO.UserRoleVO> roles = userRpc.roles(dto);
        return ResponseData.data(roles);
    }

    @ApiOperation("添加账号角色")
    @PutMapping("/userRoles/{id}")
    @Func(code = "add",name = "增")
    public ResponseData<Void> addUserRolePermit(@PathVariable String id, @Valid @RequestBody SysUserDTO.UserRoleETO eto) {
        eto.setUserId(id);
        userRpc.addUserRolePermit(eto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("删除账号角色")
    @PostMapping("/userRoles/{id}")
    @Func(code = "delete",name = "删")
    public ResponseData<Void> deleteUserRolePermit(@PathVariable String id, @Valid @RequestBody SysUserDTO.UserRoleETO eto) {
        eto.setUserId(id);
        userRpc.deleteUserRolePermit(eto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

}
