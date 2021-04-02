package com.gs.lshly.biz.support.user.service.bbc;


import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;

/**
 * 用户登陆认证
 * @author lxus
 * @since 2020/11/08
 */
public interface IBbcUserAuthService {

    void getPhoneValidCode(BbcUserDTO.GetPhoneValidCodeDTO dto);

    BbcUserVO.LoginVO login(BbcUserDTO.LoginETO dto);

    AuthDTO loadUserByUsername(String username);

    BbcUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid);

    String loadSessionKeyByWxOpenid(String openid);

    BbcUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto);

    BbcUserVO.LoginVO bindPhone(String appid, String openid, String phone);

    BbcUserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone);

    BbcUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto);

    void logout(String phone, String openid);

    BbcUserVO.ThirdVO innerGetWXNickName(String userId);
}