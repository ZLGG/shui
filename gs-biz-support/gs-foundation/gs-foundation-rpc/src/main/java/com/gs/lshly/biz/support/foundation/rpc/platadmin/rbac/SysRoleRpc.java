package com.gs.lshly.biz.support.foundation.rpc.platadmin.rbac;

import com.gs.lshly.biz.support.foundation.service.platadmin.rbac.ISysRoleService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysRoleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysRoleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysRoleVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysRoleVO.ListVO;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysRoleRpc;
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
public class SysRoleRpc implements ISysRoleRpc {

    @Autowired
    private ISysRoleService sysRoleService;


    @Override
    public PageData<SysRoleVO.ListVO> list(SysRoleQTO.QTO qoDTO) {
        return sysRoleService.pageData(qoDTO);
    }

    @Override
    public void add(SysRoleDTO.ETO dto) {
        sysRoleService.add(dto);
    }

    @Override
    public void update(SysRoleDTO.ETO dto) {
        sysRoleService.update(dto);
    }

    @Override
    public void delete(SysRoleDTO.IdDTO dto) {
        sysRoleService.delete(dto);
    }

    @Override
    public List<String> permitFuncs(SysRoleDTO.IdDTO dto) {
        return sysRoleService.permitFuncs(dto);
    }

    @Override
    public void setRoleFuncPermit(SysRoleDTO.RoleFuncETO eto) {
        sysRoleService.setRoleFuncPermit(eto);
    }

	@Override
	public List<ListVO> listAll(BaseQTO qto) {
		return sysRoleService.listAll(qto);
	}

}
