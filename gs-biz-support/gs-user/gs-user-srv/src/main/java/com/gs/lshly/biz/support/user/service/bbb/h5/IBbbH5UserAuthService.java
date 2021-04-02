package com.gs.lshly.biz.support.user.service.bbb.h5;


import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
/**
 * 用户登陆认证
 * @author lxus
 * @since 2020/11/08
 */
public interface IBbbH5UserAuthService {

    void getPhoneValidCode(BbbH5UserDTO.GetPhoneValidCodeDTO dto);

    BbbH5UserVO.LoginVO login(BbbH5UserDTO.LoginETO dto);

    AuthDTO loadUserByUsername(String username);

    BbbH5UserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid);

    String loadSessionKeyByWxOpenid(String openid);

    BbbH5UserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto);

    BbbH5UserVO.LoginVO bindPhone(String appid, String openid, String phone);

    BbbH5UserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone);

    BbbH5UserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto);

    void logout(String phone, String openid);

    BbbH5UserVO.ThirdVO innerGetWXNickName(String userId);
}