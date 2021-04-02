package com.gs.lshly.facade.merchant.controller.h5.merchant;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.dto.CommonPhoneLoginDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchMerchantAccountDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.wx.WxMaConfiguration;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountAuthRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商家账号小程序登录前端控制器
 * </p>
 *
 * @author lxus
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/auth/login")
@Api(tags = "H5商家账号微信登录")
@Slf4j
public class H5MerchantAccountAuthController {

    @DubboReference
    private IPCMerchMerchantAccountAuthRpc pcMerchMerchantAccountAuthRpc;

    @DubboReference
    private ISiteRpc siteRpc;

    @GetMapping("/wxminiapp/{appid}/openid")
    @ApiOperation(value = "1,微信小程序通过code获取openid,如果已经登录过,则直接返回登录信息,否则返回openid")
    public ResponseData<Void> login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseData.fail("code为空");
        }
        try {
            WxMaService wxService = WxMaConfiguration.getMaService(appid);
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //通过微信openid查找，针对已登录过的用户
            AuthDTO auth = pcMerchMerchantAccountAuthRpc.loadUserByWxOpenid(session.getOpenid(), session.getSessionKey());
            if (auth != null) {
                log.info("微信小程序通过code获取openid或直接登录："+ JsonUtils.toJson(auth));
                return loginSuccess(auth);
            } else {
                Map<String, Object> result = new HashMap<>();
                result.put("wxOpenid", session.getOpenid());
                return ResponseData.data(result);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseData.fail( "登陆失败,请重新进入小程序");
        }
    }

    @GetMapping("/wxminiapp/{appid}/login")
    @ApiOperation(value = "2,获取openid后,通过账号/密码登录")
    public ResponseData<Void> login(@PathVariable String appid, String openid, String username, String password) {
        AuthDTO auth = pcMerchMerchantAccountAuthRpc.login(openid, username, password);
        if (auth != null) {
            log.info("微信小程序通过openid加用户名和密码登录："+ JsonUtils.toJson(auth));
            return loginSuccess(auth);
        } else {
            return ResponseData.fail( "用户名密码错误");
        }
    }

    private ResponseData<Void> loginSuccess(AuthDTO auth) {
        Map<String, Object> result = new HashMap<>();
        JwtUser jwtUser = new JwtUser(auth);
        String token = JwtUtil.createToken(jwtUser);
        result.put("Authorization", token);
        result.put("Permit", jwtUser.getPermitNode());
        return ResponseData.data(auth);
    }

    @ApiOperation("3-1，获取手机验证码")
    @PostMapping("/getPhoneCheck")
    public ResponseData<Void> getPhoneCheck(@Valid @RequestBody CommonPhoneLoginDTO.GetPhoneValidCode dto) {
        Boolean merchantCanUsePhoneLogin = siteRpc.merchantCanUsePhoneLogin(dto);
        if(merchantCanUsePhoneLogin!=null && merchantCanUsePhoneLogin) {
            pcMerchMerchantAccountAuthRpc.getPhoneValidCode(dto);
            return ResponseData.success("短信发送成功");
        } else {
            return ResponseData.success("平台未开启商家手机登陆");
        }
    }

    @ApiOperation("3-2，通过手机号加验证码登录")
    @PostMapping("/login")
    public ResponseData<BbbUserVO.LoginVO> login(@Valid @RequestBody CommonPhoneLoginDTO.Login dto) {
        if (StringUtils.isBlank(dto.getPhone())) {
            return ResponseData.fail("手机号不能为空");
        }
        BbbUserVO.LoginVO vo = pcMerchMerchantAccountAuthRpc.login(dto);
        return ResponseData.data(vo);
    }


    @GetMapping("/wxminiapp/{appid}/bindInnerPhone")
    @ApiOperation(value = "4,微信自身手机号登陆")
    public ResponseData<BbbUserVO.LoginVO> bindInnerPhone(@PathVariable String appid, String openid, String encryptedData, String iv){
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        String sessionKey = pcMerchMerchantAccountAuthRpc.loadSessionKeyByWxOpenid(openid);
        // 解密
        try {
            WxMaPhoneNumberInfo phoneInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
            log.info("手机信息：" + JsonUtils.toJson(phoneInfo));
            //更新用户手机
            CommonPhoneLoginDTO.WxUserPhone dto = new CommonPhoneLoginDTO.WxUserPhone();
            BeanCopyUtils.copyProperties(phoneInfo, dto);
            dto.setAppId(appid);
            if (StringUtils.isBlank(dto.getOpenId())) {
                dto.setOpenId(openid);
            }
            BbbUserVO.LoginVO vo = pcMerchMerchantAccountAuthRpc.merchantLoginByWxInnerPhone(dto);
            log.info("微信自身手机号登陆："+JsonUtils.toJson(vo));
            return ResponseData.data(vo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseData.fail( "微信自身手机号登陆失败");
        }
    }


    @ApiOperation("注册")
    @PostMapping("/reg")
    public ResponseData<String> add(@Valid @RequestBody H5MerchMerchantAccountDTO.RegDTO dto) {
        return ResponseData.data(pcMerchMerchantAccountAuthRpc.regMerchantAccount(dto));
    }

    @ApiOperation("退出(1，H5退出-需要带上phone；2，小程序退出-需要带上openid),目的清空服务端的缓存")
    @GetMapping("/logout")
    public void logout(String phone, String openid) {
        pcMerchMerchantAccountAuthRpc.logout(phone, openid);
    }

}
