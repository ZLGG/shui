package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysRoleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysRoleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysRoleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
public interface ISysRoleService {

    PageData<SysRoleVO.ListVO> pageData(SysRoleQTO.QTO qoDTO);

    void add(SysRoleDTO.ETO dto);

    void update(SysRoleDTO.ETO dto);

    void delete(SysRoleDTO.IdDTO dto);

    List<String> permitFuncs(SysRoleDTO.IdDTO dto);

    void setRoleFuncPermit(SysRoleDTO.RoleFuncETO eto);
    
    List<SysRoleVO.ListVO> listAll(BaseQTO qto);
}
