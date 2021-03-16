package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysFuncDTO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
public interface ISysFuncService {

    Map<String, String> frontRouterMap(BaseDTO dto);

    void setFrontRouter(SysFuncDTO.ETO dto);

    PermitNode initFuncTree2DB(PermitNode allPermitNode);
}
