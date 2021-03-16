package com.gs.lshly.rpc.api.platadmin.foundation;

import com.gs.lshly.common.struct.platadmin.foundation.vo.FdSysUserVO;

/**
 *
 * @author lxus
 * @since 2020-10-26
 */
public interface IFdSysUserRpc {

    /**
     * 系统用户，基本信息获取
     * @param userId
     * @return
     */
    FdSysUserVO.SimpleVO getSysUserName(String userId);

}
