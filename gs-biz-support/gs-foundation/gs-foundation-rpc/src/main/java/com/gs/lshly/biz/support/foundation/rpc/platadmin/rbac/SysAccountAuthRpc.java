package com.gs.lshly.biz.support.foundation.rpc.platadmin.rbac;


import com.gs.lshly.biz.support.foundation.service.platadmin.rbac.ISysUserService;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysAccountAuthRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 */
@DubboService
public class SysAccountAuthRpc implements ISysAccountAuthRpc {

    @Autowired
    private ISysUserService userService;

    @Override
    public AuthDTO loadUserByUsername(String username) {
        return userService.findByUserName(username);
    }
}
