package com.gs.lshly.rpc.api.platadmin.foundation.rbac;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysFuncDTO;

import java.util.Map;

/**
 * @author lxus
 */
public interface ISysFuncRpc {

    Map<String, String> frontRouterMap(BaseDTO dto);

    void setFrontRouter(SysFuncDTO.ETO dto);

    PermitNode initFuncTree2DB(PermitNode allPermitNode);
}
