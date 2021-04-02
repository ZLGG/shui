package com.gs.lshly.biz.support.foundation.rpc.platadmin.rbac;

import com.gs.lshly.biz.support.foundation.service.platadmin.rbac.IOAuth2Service;
import com.gs.lshly.common.enums.OAuth2UserTypeEnum;
import com.gs.lshly.common.struct.common.OAuth2DTO;
import com.gs.lshly.common.struct.common.OAuth2VO;
import com.gs.lshly.rpc.api.platadmin.foundation.IOAuth2Rpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * oauth2应用实现
 * @author lxus
 * @since 2020-10-25=6
 */
@DubboService
public class OAuth2Rpc implements IOAuth2Rpc {

    @Autowired
    private IOAuth2Service oAuth2Service;

    /**
     * 根据已登录的用户为登录端分配code
     * @param dto
     * @return
     */
    @Override
    public String allocateCode(OAuth2UserTypeEnum type, OAuth2DTO.AllocateCodeDTO dto) {
        return oAuth2Service.allocateCode(type, dto);
    }

    /**
     * 通过code生成token
     * @param dto
     * @return
     */
    @Override
    public OAuth2VO.TokenVO generationToken(OAuth2DTO.GenerationTokenDTO dto) {
        return oAuth2Service.generationToken(dto);
    }

    /**
     * 通过token获取商家信息
     * @param dto
     * @return
     */
    @Override
    public OAuth2VO.MerchantVO merchantInfo(OAuth2DTO.UserInfoDTO dto) {
        return oAuth2Service.merchantInfo(dto);
    }

    /**
     * 通过refresh_token刷新访问token.
     * @param dto
     * @return
     */
    @Override
    public OAuth2VO.TokenVO refreshToken(OAuth2DTO.RefreshTokenDTO dto) {
        return oAuth2Service.refreshToken(dto);
    }


}
