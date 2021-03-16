package com.gs.lshly.biz.support.merchant.service.pos;


import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;

/**
 * 千米token服务
 * @author lxus 
 * @since 2020-12-01
 */
public interface IPosOAuthService {


    void persistence(PosOAuthDTO.TokenDTO token);

    String getToken(String key, String secret);

    String getRefreshToken(String key, String secret);
}