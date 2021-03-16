package com.gs.lshly.biz.support.user.rpc.merchadmin;

import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.rpc.api.merchadmin.pc.user.IPCMerchAdminUserRpc;
import org.apache.dubbo.config.annotation.DubboService;

/**
*
* @author xxfc
* @since 2020-10-05
*/
@DubboService
public class MerchUserRpc implements IPCMerchAdminUserRpc {


    @Override
    public void pageListForPrivateUserList() {

    }

    @Override
    public void applyForPrivateUser() {

    }

    @Override
    public UserVO.DetailVO detailForPrivateUser(UserDTO.IdDTO dto) {
        return null;
    }
}