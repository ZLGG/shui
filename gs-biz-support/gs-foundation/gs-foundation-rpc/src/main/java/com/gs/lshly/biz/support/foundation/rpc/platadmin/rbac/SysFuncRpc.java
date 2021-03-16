package com.gs.lshly.biz.support.foundation.rpc.platadmin.rbac;

import com.gs.lshly.biz.support.foundation.service.platadmin.rbac.ISysFuncService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysFuncDTO;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysFuncRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@DubboService
public class SysFuncRpc implements ISysFuncRpc {

    @Autowired
    private ISysFuncService sysFuncService;

    @Override
    public Map<String, String> frontRouterMap(BaseDTO dto) {
        return sysFuncService.frontRouterMap(dto);
    }

    @Override
    public void setFrontRouter(SysFuncDTO.ETO dto) {
        sysFuncService.setFrontRouter(dto);
    }


    @Override
    public PermitNode initFuncTree2DB(PermitNode allPermitNode) {
        return sysFuncService.initFuncTree2DB(allPermitNode);
    }
}
