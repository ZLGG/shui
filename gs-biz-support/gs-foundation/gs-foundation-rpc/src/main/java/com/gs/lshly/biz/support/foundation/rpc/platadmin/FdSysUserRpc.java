package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.biz.support.foundation.service.platadmin.IFdSysUserService;
import com.gs.lshly.common.struct.platadmin.foundation.vo.FdSysUserVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IFdSysUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-06
*/
@DubboService
public class FdSysUserRpc implements IFdSysUserRpc {

    @Autowired
    private IFdSysUserService sysUserService;

    @Override
    public FdSysUserVO.SimpleVO getSysUserName(String userId) {
        return sysUserService.getSysUserName(userId);
    }
}