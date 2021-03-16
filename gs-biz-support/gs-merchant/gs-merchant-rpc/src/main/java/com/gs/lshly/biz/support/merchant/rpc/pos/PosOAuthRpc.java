package com.gs.lshly.biz.support.merchant.rpc.pos;

import com.gs.lshly.biz.support.merchant.service.pos.IPosOAuthService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;
import com.gs.lshly.rpc.api.pos.IPosOAuthRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 千米token
 * @author lxus
 * @since 2020-12-01
 */
@DubboService
public class PosOAuthRpc implements IPosOAuthRpc {

    @Autowired
    private IPosOAuthService oAuthService;

    @Override
    public void persistenceToken(PosOAuthDTO.TokenDTO token) {
        if (StringUtils.isBlank(token.getId())) {
            throw new BusinessException("千米token为空");
        }
        if (StringUtils.isBlank(token.getRefreshToken())) {
            throw new BusinessException("千米刷新token为空");
        }
        oAuthService.persistence(token);
    }

    @Override
    public String getToken(String key, String secret) {
        return oAuthService.getToken(key, secret);
    }

    @Override
    public String getRefreshToken(String key, String secret) {
        return oAuthService.getRefreshToken(key, secret);
    }

}
