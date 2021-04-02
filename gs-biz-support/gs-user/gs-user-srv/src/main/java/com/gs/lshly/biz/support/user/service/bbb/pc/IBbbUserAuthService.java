package com.gs.lshly.biz.support.user.service.bbb.pc;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;

/**
 * 用户登陆认证
 * @author lxus
 * @since 2020/11/08
 */
public interface IBbbUserAuthService {

    String register(BbbUserDTO.RegisterETO dto);

    void getPhoneValidCode(BbbUserDTO.GetPhoneValidCodeDTO dto);

    BbbUserVO.LoginVO login(BbbUserDTO.LoginETO dto);

    AuthDTO loadUserByUsername(String username);

    BbbUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid);

    String loadSessionKeyByWxOpenid(String openid);

    BbbUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto);

    BbbUserVO.LoginVO bindPhone(String appid, String openid, String phone);

    BbbUserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone);

    BbbUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto);

    void logout(String phone, String openid);

    void forgetPasswordByPhone(BbbUserDTO.ForgetByPhoneETO dto);

    String test();

    void forgetByEmail(BbbUserDTO.ForgetByEmailETO dto);

    ResponseData<BbbUserVO.LoginVO> loadUserBySceneId(long sceneId);

    void loadUserBySceneId(String sceneId, BBcWxUserInfoDTO dto);

    BbbUserVO.LoginVO bindQRPhone(long sceneId, String validCode, String phone);
}