package com.gs.lshly.rpc.api.bbc.user;

import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;

/**
 * @author lxus
 */
public interface IBbcUserAuthRpc {

    AuthDTO loadUserByUsername(String username);

    void getPhoneValidCode(BbcUserDTO.GetPhoneValidCodeDTO dto);

    BbcUserVO.LoginVO login(BbcUserDTO.LoginETO dto);

    BbcUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey);

    String loadSessionKeyByWxOpenid(String openid);

    BbcUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto);

    BbcUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto);

    BbcUserVO.LoginVO bindPhone(String appid, String openid, String validCode, String phone);

    void logout(String phone, String openid);
}
