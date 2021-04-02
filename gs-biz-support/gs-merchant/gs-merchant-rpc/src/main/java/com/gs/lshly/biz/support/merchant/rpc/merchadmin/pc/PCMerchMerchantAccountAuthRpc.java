package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantAccountAuthService;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.dto.CommonPhoneLoginDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountAuthRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lxus
 */
@DubboService
public class PCMerchMerchantAccountAuthRpc implements IPCMerchMerchantAccountAuthRpc {

    @Autowired
    private IPCMerchMerchantAccountAuthService accountService;

    @Override
    public AuthDTO loadUserByUsername(String username) {
        return accountService.findByUserName(username);
    }

    @Override
    public String loadUserIdById(String userId) {
        if (accountService.isValidAccount(userId)) {
            return userId;
        }
        return null;
    }

    @Override
    public AuthDTO loadOAuth2JwtUserById(String userId) {
        return accountService.getJwtUserById(userId);
    }

    @Override
    public PCMerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto) {
        return accountService.changeShop(shopId,dto);
    }

    @Override
    public AuthDTO loadUserByWxOpenid(String sessionOpenid, String sessionKey) {
        return accountService.findByWxOpenid(sessionOpenid, sessionKey);
    }

    @Override
    public AuthDTO login(String openid, String username, String password) {
        return accountService.login(openid, username, password);
    }

    @Override
    public void getPhoneValidCode(CommonPhoneLoginDTO.GetPhoneValidCode dto) {
        accountService.getPhoneValidCode(dto);
    }

    @Override
    public BbbUserVO.LoginVO login(CommonPhoneLoginDTO.Login dto) {
        return accountService.login(dto);
    }

    @Override
    public String loadSessionKeyByWxOpenid(String openid) {
        return accountService.loadSessionKeyByWxOpenid(openid);
    }

    @Override
    public BbbUserVO.LoginVO merchantLoginByWxInnerPhone(CommonPhoneLoginDTO.WxUserPhone dto) {
        return accountService.merchantLoginByWxInnerPhone(dto);
    }

    @Override
    public void logout(String phone, String openid) {
        accountService.logout(phone, openid);
    }

    @Override
    public String regMerchantAccount(H5MerchMerchantAccountDTO.RegDTO eto) {
        return accountService.regMerchantAccount(eto);
    }

}
