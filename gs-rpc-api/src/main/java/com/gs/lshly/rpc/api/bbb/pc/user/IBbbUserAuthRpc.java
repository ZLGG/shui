package com.gs.lshly.rpc.api.bbb.pc.user;

import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;

public interface IBbbUserAuthRpc {

    String register(BbbUserDTO.RegisterETO dto);

    AuthDTO loadUserByUsername(String username);

    void getPhoneValidCode(BbbUserDTO.GetPhoneValidCodeDTO dto);

    BbbUserVO.LoginVO login(BbbUserDTO.LoginETO dto);

    BbbUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid);

    String loadSessionKeyByWxOpenid(String openid);

    BbbUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto);

    BbbUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto);

    BbbUserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone);

    void logout(String phone, String openid);

    void forgetPasswordByPhone(BbbUserDTO.ForgetByPhoneETO dto);

    String  test();

    void forgetByEmail(BbbUserDTO.ForgetByEmailETO dto);
}
