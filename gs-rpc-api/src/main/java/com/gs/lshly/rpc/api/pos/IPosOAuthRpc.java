package com.gs.lshly.rpc.api.pos;

import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;

/**
*
* @author lxus
* @since 2020-12-01
*/
public interface IPosOAuthRpc {

    void persistenceToken(PosOAuthDTO.TokenDTO token);

    String getToken(String key, String secret);

    String getRefreshToken(String key, String secret);
}