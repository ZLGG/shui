package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import com.gs.lshly.common.enums.OAuth2UserTypeEnum;
import com.gs.lshly.common.struct.common.OAuth2DTO;
import com.gs.lshly.common.struct.common.OAuth2VO;

/**
 * 平台管理账号oauth2认证服务
 * @author lxus
 * @since 2020-10-26
 */
public interface IOAuth2Service {

    String allocateCode(OAuth2UserTypeEnum type, OAuth2DTO.AllocateCodeDTO dto);

    OAuth2VO.TokenVO generationToken(OAuth2DTO.GenerationTokenDTO dto);

    OAuth2VO.SysUserVO userInfo(OAuth2DTO.UserInfoDTO dto);

    OAuth2VO.MerchantVO merchantInfo(OAuth2DTO.UserInfoDTO dto);

    OAuth2VO.TokenVO refreshToken(OAuth2DTO.RefreshTokenDTO dto);
}
