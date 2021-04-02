package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.Oauth2Client;
import com.gs.lshly.biz.support.foundation.entity.Oauth2Code;
import com.gs.lshly.biz.support.foundation.entity.Oauth2Token;
import com.gs.lshly.biz.support.foundation.entity.SysUser;
import com.gs.lshly.biz.support.foundation.repository.IOauth2ClientRepository;
import com.gs.lshly.biz.support.foundation.repository.IOauth2CodeRepository;
import com.gs.lshly.biz.support.foundation.repository.IOauth2TokenRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRepository;
import com.gs.lshly.common.enums.OAuth2UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.common.OAuth2DTO;
import com.gs.lshly.common.struct.common.OAuth2VO;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountAuthRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class OAuth2ServiceImpl implements IOAuth2Service {

    @Autowired
    private IOauth2ClientRepository clientRepository;

    @Autowired
    private IOauth2CodeRepository codeRepository;

    @Autowired
    private IOauth2TokenRepository tokenRepository;

    @Autowired
    private ISysUserRepository sysUserRepository;

    @DubboReference
    private IPCMerchMerchantAccountAuthRpc merchantAccountAuthRpc;


    /**
     * token过期小时数
     */
    public static final long TOKEN_EXPIRE_HOUR = 2;

    /**
     * 刷新token过期天数
     */
    public static final long REFRESH_TOKEN_EXPIRE_DAY = 7;


    @Override
    public String allocateCode(OAuth2UserTypeEnum type, OAuth2DTO.AllocateCodeDTO dto) {
        //1,校验clientId-clientRepository
        Oauth2Client client = clientRepository.getById(dto.getClientId());
        if (client == null) {
            throw new BusinessException("clientId无效");
        }
        //2,获取当前用户信息-sysUserRepository
        String userId = null;
        if (type == OAuth2UserTypeEnum.平台账号) {
            SysUser sysUser = sysUserRepository.getById(dto.getJwtUserId());
            userId = sysUser != null ? sysUser.getId() : null;

        }
        if (type == OAuth2UserTypeEnum.商家账号) {
            userId = merchantAccountAuthRpc.loadUserIdById(dto.getJwtUserId());
        }
        if (userId == null) {
            throw new BusinessException("请先登录");
        }

        //3,code与当前用户信息对应.-codeRepository
        String code = IdUtil.simpleUUID();
        codeRepository.save(new Oauth2Code().setId(code).setUserId(userId).setUserType(type.getCode()));
        return code;
    }

    @Override
    public OAuth2VO.TokenVO generationToken(OAuth2DTO.GenerationTokenDTO dto) {
        Oauth2Code code = codeRepository.getById(dto.getCode());
        if (code==null) {
            throw new BusinessException("code无效");
        }
        //1,找到用户,并生成token存储
        Oauth2Token token = initToken();
        JwtUser jwtUser = null;
        if (code.getUserType().equals(OAuth2UserTypeEnum.平台账号.getCode())) {
            SysUser sysUser = sysUserRepository.getById(code.getUserId());
            jwtUser = toJwtUser(sysUser);
        }
        if (code.getUserType().equals(OAuth2UserTypeEnum.商家账号.getCode())) {
            AuthDTO authDTO = merchantAccountAuthRpc.loadOAuth2JwtUserById(code.getUserId());
            jwtUser = new JwtUser(authDTO);
            jwtUser.setRememberMe(true);
        }

        token.setJwt(JwtUtil.createToken(jwtUser));
        tokenRepository.save(token);
        //2,返回token
        return view(token);
    }

    private JwtUser check(OAuth2DTO.UserInfoDTO dto) {
        if (StringUtils.isBlank(dto.getToken())) {
            throw new BusinessException("token不能为空");
        }
        //1,获取token
        Oauth2Token token = tokenRepository.getById(dto.getToken());
        if (token == null || token.getExpire() == null) {
            throw new BusinessException("token无效");
        }
        if (token.getExpire().isBefore(LocalDateTime.now())) {
            throw new BusinessException("token已过期");
        }
        //2,解密jwt
        JwtUser jwtUser = JwtUtil.getJwtUser(token.getJwt());
        if (JwtUtil.isExpiration(jwtUser)) {
            //todo 更新jwt内容
            log.warn("OAuth2 JWT过期!");
        }
        return jwtUser;
    }

    @Override
    public OAuth2VO.MerchantVO merchantInfo(OAuth2DTO.UserInfoDTO dto) {
        JwtUser jwtUser = check(dto);

        OAuth2VO.MerchantVO merchantInfo = new OAuth2VO.MerchantVO();

        if (StringUtils.isBlank(jwtUser.getMerchantId())) {
            merchantInfo.setSysHeadImg(jwtUser.getHeadImg()).setSysUserName(jwtUser.getUsername()).setSysUserId(jwtUser.getId());
        } else {
            merchantInfo.setUserId(jwtUser.getId()).setHeadImg(jwtUser.getHeadImg()).setUserName(jwtUser.getUsername());
            merchantInfo.setMerchantId(jwtUser.getMerchantId()).setShopId(jwtUser.getShopId()).setPhone(jwtUser.getPhone());
        }
        return merchantInfo;
    }

    @Override
    public OAuth2VO.TokenVO refreshToken(OAuth2DTO.RefreshTokenDTO dto) {
        if (StringUtils.isBlank(dto.getRefreshToken())) {
            throw new BusinessException("refreshToken不能为空");
        }
        //1,获取token
        Oauth2Token oldToken = tokenRepository.getOne(new QueryWrapper<Oauth2Token>().eq("refresh_token", dto.getRefreshToken()));
        if (oldToken == null || oldToken.getRefreshExpire() == null) {
            throw new BusinessException("refreshToken无效");
        }
        if (oldToken.getRefreshExpire().isBefore(LocalDateTime.now())) {
            throw new BusinessException("refreshToken已过期");
        }
        //2,重新设置token,并更新
        Oauth2Token newToken = initToken();
        newToken.setJwt(oldToken.getJwt());
        tokenRepository.update(newToken, new UpdateWrapper<Oauth2Token>().set("id", newToken.getId()).eq("id", oldToken.getId()));

        //3,返回token
        return view(newToken);
    }

    private JwtUser toJwtUser(SysUser sysUser) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(sysUser.getId());
        jwtUser.setRememberMe(true);
        jwtUser.setUsername(sysUser.getName());
        jwtUser.setHeadImg(sysUser.getHeadImg());
        return jwtUser;
    }

    private Oauth2Token initToken() {
        Oauth2Token token = new Oauth2Token();
        String tokenId = IdUtil.simpleUUID();
        token.setId(tokenId);
        token.setExpire(LocalDateTime.now().plusHours(TOKEN_EXPIRE_HOUR));
        String refreshToken = IdUtil.simpleUUID();
        token.setRefreshToken(refreshToken);
        token.setRefreshExpire(LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRE_DAY));
        return token;
    }

    private OAuth2VO.TokenVO view(Oauth2Token token) {
        OAuth2VO.TokenVO tokenVO = new OAuth2VO.TokenVO();
        tokenVO.setToken(token.getId()).setRefreshToken(token.getRefreshToken())
                .setTokenExpire(token.getExpire()).setRefreshTokenExpire(token.getRefreshExpire());
        return tokenVO;
    }
}
