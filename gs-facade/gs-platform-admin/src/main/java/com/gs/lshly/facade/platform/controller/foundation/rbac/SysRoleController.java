package com.gs.lshly.facade.platform.controller.foundation.rbac;


import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysRoleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysRoleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysRoleVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysRoleRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/platform/sys-role")
@Api(tags = "RBAC角色管理")
@Module(code = "listSysRole",parent = "set",name = "角色管理",index = 2)
public class SysRoleController {

    @DubboReference
    private ISysRoleRpc roleRpc;

    @ApiOperation("获取所有角色列表-v1.1.0")
    @GetMapping("/listAll")
    public ResponseData<List<SysRoleVO.ListVO>> listAll(BaseQTO qto) {
        return ResponseData.data(roleRpc.listAll(qto));
    }
    
    @ApiOperation("角色列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<SysRoleVO.ListVO>> list(SysRoleQTO.QTO qo) {
        return ResponseData.data(roleRpc.list(qo));
    }

    @ApiOperation("新增平台角色")
    @Log(module = "平台角色管理", func = "新增平台角色")
    @PostMapping("")
    @Func(code = "add",name = "增")
    public ResponseData<Void> add(@Valid @RequestBody SysRoleDTO.ETO dto) {
        roleRpc.add(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("修改平台角色")
    @Log(module = "平台角色管理", func = "修改平台角色")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody SysRoleDTO.ETO dto) {
        dto.setId(id);
        roleRpc.update(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除平台角色")
    @Log(module = "平台角色管理", func = "删除平台角色")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete",name = "删")
    public ResponseData<Void> delete(@PathVariable String id) {
        SysRoleDTO.IdDTO dto = new SysRoleDTO.IdDTO(id);
        roleRpc.delete(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("获取角色权限集")
    @GetMapping("/roleFunc/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<List<String>> roleFunc(@PathVariable String id) {
        SysRoleDTO.IdDTO dto = new SysRoleDTO.IdDTO(id);
        return ResponseData.data(roleRpc.permitFuncs(dto));
    }

    @ApiOperation("设置角色权限集")
    @PutMapping("/roleFunc/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<PermitNodeVO.CheckedNodeVO> roleFunc(@PathVariable String id, @Valid @RequestBody SysRoleDTO.RoleFuncETO eto) {
        eto.setRoleId(id);
        roleRpc.setRoleFuncPermit(eto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

}
