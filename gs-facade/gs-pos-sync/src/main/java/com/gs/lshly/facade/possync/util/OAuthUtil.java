package com.gs.lshly.facade.possync.util;

import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.facade.possync.config.QianmiProperties;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.response.TokenResponse;
import com.qianmi.open.api.tool.util.OAuthUtils;
import com.qianmi.open.api.tool.util.QianmiContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OAuthUtil {

    @Autowired
    private QianmiProperties properties;

    /**
     * 根据授权码获取token
     * @param code
     * @return
     * @throws ApiException
     */
    public PosOAuthDTO.TokenDTO getToken(String code) throws ApiException {

        QianmiContext context = OAuthUtils.getToken(properties.getKey(), properties.getSecret(), code);
        TokenResponse response = context.getTokenResponse();

        return build(response);
    }

    /**
     * 根据refreshToken刷新token
     * @param refreshToken
     * @return
     * @throws ApiException
     */
    public PosOAuthDTO.TokenDTO refreshToken(String refreshToken) throws ApiException {

        QianmiContext context = OAuthUtils.refreshToken(properties.getKey(), properties.getSecret(), refreshToken);
        TokenResponse response = context.getTokenResponse();

        return build(response);
    }

    private PosOAuthDTO.TokenDTO build(TokenResponse response) {
        log.info("千米token获取:" + JsonUtils.toJson(response));
        PosOAuthDTO.TokenDTO accessToken = new PosOAuthDTO.TokenDTO();

        accessToken.setId(response.getAccessToken()).setExpire(response.getExpiresIn())
                .setRefreshToken(response.getRefreshToken()).setReExpire(response.getReExpiresIn())
                .setUserId(response.getUserId()).setUserNick(response.getUserNick()).setParentId(response.getParentId())
                .setAppKey(properties.getKey()).setAppSecret(properties.getSecret());

        return accessToken;
    }
}
