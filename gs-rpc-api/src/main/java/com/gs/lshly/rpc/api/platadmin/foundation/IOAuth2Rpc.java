package com.gs.lshly.rpc.api.platadmin.foundation;

import com.gs.lshly.common.enums.OAuth2UserTypeEnum;
import com.gs.lshly.common.struct.common.OAuth2DTO;
import com.gs.lshly.common.struct.common.OAuth2VO;

/**
 * oauth2应用接口
 * @author lxus
 * @since 2020-10-26
 */
public interface IOAuth2Rpc {

    /**
     * 为客户端生成code
     * @param dto
     * @return
     */
    String allocateCode(OAuth2UserTypeEnum type, OAuth2DTO.AllocateCodeDTO dto);

    /**
     * 通过code生成token
     * @param dto
     * @return
     */
    OAuth2VO.TokenVO generationToken(OAuth2DTO.GenerationTokenDTO dto);

    /**
     * 通过token获取商家账号信息
     * @param dto
     * @return
     */
    OAuth2VO.MerchantVO merchantInfo(OAuth2DTO.UserInfoDTO dto);

    /**
     * 通过refresh_token刷新访问token.
     * @param dto
     * @return
     */
    OAuth2VO.TokenVO refreshToken(OAuth2DTO.RefreshTokenDTO dto);
}
