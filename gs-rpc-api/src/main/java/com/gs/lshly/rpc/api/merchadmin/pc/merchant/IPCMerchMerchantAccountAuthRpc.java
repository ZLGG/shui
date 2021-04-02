package com.gs.lshly.rpc.api.merchadmin.pc.merchant;

import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.dto.CommonPhoneLoginDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;

/**
 * @author lxus
 */
public interface IPCMerchMerchantAccountAuthRpc {

    AuthDTO loadUserByUsername(String username);

    String loadUserIdById(String operator);

    AuthDTO loadOAuth2JwtUserById(String userId);

    PCMerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto);

    AuthDTO loadUserByWxOpenid(String sessionOpenid, String sessionKey);

    AuthDTO login(String openid, String username, String password);

    void getPhoneValidCode(CommonPhoneLoginDTO.GetPhoneValidCode dto);

    BbbUserVO.LoginVO login(CommonPhoneLoginDTO.Login dto);

    String loadSessionKeyByWxOpenid(String openid);

    BbbUserVO.LoginVO merchantLoginByWxInnerPhone(CommonPhoneLoginDTO.WxUserPhone dto);

    void logout(String phone, String openid);

    //注册
    String regMerchantAccount(H5MerchMerchantAccountDTO.RegDTO eto);
}
