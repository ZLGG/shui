package com.gs.lshly.rpc.api.bbb.h5.user;

import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
/**
 * @author lxus
 */
public interface IBbbH5UserAuthRpc {

    AuthDTO loadUserByUsername(String username);

    void getPhoneValidCode(BbbH5UserDTO.GetPhoneValidCodeDTO dto);

    BbbH5UserVO.LoginVO login(BbbH5UserDTO.LoginETO dto);

    BbbH5UserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid);

    String loadSessionKeyByWxOpenid(String openid);

    BbbH5UserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto);

    BbbH5UserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto);

    BbbH5UserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone);

    void logout(String phone, String openid);

    /**
     *
     * 通过userId查询微信名字
     */
    BbbH5UserVO.ThirdVO innerGetWXNickName(String userId);

}
