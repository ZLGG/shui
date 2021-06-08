package com.gs.lshly.rpc.api.platadmin.foundation.rbac;


import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysRoleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysRoleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysRoleVO;

/**
 *
 * @author lxus
 * @since 2020/12/12
 */public interface ISysRoleRpc {

    PageData<SysRoleVO.ListVO> list(SysRoleQTO.QTO qoDTO);
    
    List<SysRoleVO.ListVO> listAll(BaseQTO qto);

    void add(SysRoleDTO.ETO dto);

    void update(SysRoleDTO.ETO dto);

    void delete(SysRoleDTO.IdDTO dto);

    List<String> permitFuncs(SysRoleDTO.IdDTO dto);

    void setRoleFuncPermit(SysRoleDTO.RoleFuncETO eto);
}
