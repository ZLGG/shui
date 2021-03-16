package com.gs.lshly.rpc.api.platadmin.foundation.rbac;

import com.gs.lshly.common.struct.AuthDTO;

public interface ISysAccountAuthRpc {

    AuthDTO loadUserByUsername(String username);

}
