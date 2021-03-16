package com.gs.lshly.biz.support.user.rpc.bbb.pc;

import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserAuthService;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserAuthRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 */
@DubboService
public class BbbUserAuthRpc implements IBbbUserAuthRpc {

    @Autowired
    private IBbbUserAuthService userAuthService;

    @Override
    public String register(BbbUserDTO.RegisterETO dto) {
        return userAuthService.register(dto);
    }

    @Override
    public AuthDTO loadUserByUsername(String username) {
        return userAuthService.loadUserByUsername(username);
//        return new AuthDTO().setId("0001").setState(UserStateEnum.启用.getCode()).setUsername(username).setPassword("$2a$10$ccZj8lZoa.UHTLwaCxoQ0em//hlOurIQ9EaxuTDYjXmd.BMC4yPpm")
//                .setShopId("shopid001").setMerchantId("merchant001").setPhone("13000010001");
    }

    @Override
    public void getPhoneValidCode(BbbUserDTO.GetPhoneValidCodeDTO dto) {
        userAuthService.getPhoneValidCode(dto);
    }


    @Override
    public BbbUserVO.LoginVO login(BbbUserDTO.LoginETO dto) {
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
    public BbbUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey) {
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
    public BbbUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数错误");
        }
        if (StringUtils.isBlank(dto.getPhoneNumber())) {
            throw new BusinessException("手机号不能为空");
        }
        return userAuthService.updateUserPhoneByWxInnerPhone(dto);
    }

    @Override
    public BbbUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto) {
        if (dto == null || StringUtils.isBlank(dto.getAppId()) || StringUtils.isBlank(dto.getOpenId())) {
            return null;
        }
        return userAuthService.addWxThirdLogin(dto);
    }

    @Override
    public BbbUserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone) {
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

    @Override
    public void forgetPasswordByPhone(BbbUserDTO.ForgetByPhoneETO dto) {
        userAuthService.forgetPasswordByPhone(dto);
    }

    @Override
    public String test() {
        return userAuthService.test();
    }
}
