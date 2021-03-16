package com.gs.lshly.biz.support.user.rpc.bbb.h5;

import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserAuthService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserAuthRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 */
@DubboService
public class BbbH5UserAuthRpc implements IBbbH5UserAuthRpc {

    @Autowired
    private IBbbH5UserAuthService userAuthService;

    @Override
    public AuthDTO loadUserByUsername(String username) {
        return userAuthService.loadUserByUsername(username);
    }

    @Override
    public void getPhoneValidCode(BbbH5UserDTO.GetPhoneValidCodeDTO dto) {
        if (StringUtils.isBlank(dto.getPhone())) {
            throw new BusinessException("手机号不能为空");
        }
        userAuthService.getPhoneValidCode(dto);
    }

    @Override
    public BbbH5UserVO.LoginVO login(BbbH5UserDTO.LoginETO dto) {
        if (dto==null) {
            throw new BusinessException("参数错误");
        }
        if (StringUtils.isBlank(dto.getPhone())) {
            throw new BusinessException("手机号不能为空");
        }
        if (StringUtils.isBlank(dto.getValidCode())) {
            throw new BusinessException("验证码不能为空");
        }
        return userAuthService.login(dto);
    }

    @Override
    public BbbH5UserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey) {
        if (StringUtils.isBlank(openid)) {
            return null;
        }
        return userAuthService.loadUserByWxOpenid(appid, openid, sessionKey);
    }

    @Override
    public String loadSessionKeyByWxOpenid(String openid) {
        if (StringUtils.isBlank(openid)) {
            throw new BusinessException("openid不能为空");
        }
        return userAuthService.loadSessionKeyByWxOpenid(openid);
    }

    @Override
    public BbbH5UserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数错误");
        }
        if (StringUtils.isBlank(dto.getPhoneNumber())) {
            throw new BusinessException("手机号不能为空");
        }
        return userAuthService.updateUserPhoneByWxInnerPhone(dto);
    }

    @Override
    public BbbH5UserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto) {
        if (dto == null || StringUtils.isBlank(dto.getAppId()) || StringUtils.isBlank(dto.getOpenId())) {
            return null;
        }
        return userAuthService.addWxThirdLogin(dto);
    }

    @Override
    public BbbH5UserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone) {
        if (StringUtils.isBlank(appid)) {
            throw new BusinessException("appid不能为空");
        }
        if (StringUtils.isBlank(openid)) {
            throw new BusinessException("openid不能为空");
        }
        if (StringUtils.isBlank(validCode)) {
            throw new BusinessException("验证码不能为空");
        }
        if (StringUtils.isBlank(phone)) {
            throw new BusinessException("手机号不能为空");
        }
        return userAuthService.bindPhone(appid, openid, validCode, phone);
    }

    @Override
    public void logout(String phone, String openid) {
        userAuthService.logout(phone, openid);
    }
}
