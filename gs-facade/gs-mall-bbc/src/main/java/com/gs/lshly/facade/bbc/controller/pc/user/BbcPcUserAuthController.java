package com.gs.lshly.facade.bbc.controller.pc.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.middleware.wx.WxMaConfiguration;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserAuthRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-27
*/
@RestController
@RequestMapping("/pc/bbcAuth")
@Api(tags = "2C PC会员登录注册")
@Slf4j
public class BbcPcUserAuthController {

    @DubboReference
    private IBbbUserAuthRpc bbbUserAuthRpc;


    @ApiOperation("用户获取手机验证码")
    @PostMapping("/getPhoneCheck")
    public ResponseData<Void> getPhoneCheck(@Valid @RequestBody BbbUserDTO.GetPhoneValidCodeDTO dto) {
        bbbUserAuthRpc.getPhoneValidCode(dto);
        return ResponseData.success("短信发送成功");
    }

    @ApiOperation("手机号注册(注册完成就已经登录)")
    @PostMapping("/register")
    public ResponseData<String> register(@Valid @RequestBody BbbUserDTO.RegisterETO dto) {
        return ResponseData.data(bbbUserAuthRpc.register(dto));
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseData<BbbUserVO.LoginVO> login(@Valid @RequestBody BbbUserDTO.LoginETO dto) {
        BbbUserVO.LoginVO vo = bbbUserAuthRpc.login(dto);
        return ResponseData.data(vo);
    }



    @GetMapping("/wxminiapp/{appid}/openid")
    @ApiOperation(value = "1,微信小程序通过code获取openid")
    public ResponseData<BbbUserVO.LoginVO> login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseData.fail("code为空");
        }
        try {
            WxMaService wxService = WxMaConfiguration.getMaService(appid);
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //通过微信openid查找，针对已登录过的用户
            BbbUserVO.LoginVO loginVO = bbbUserAuthRpc.loadUserByWxOpenid(appid, session.getOpenid(), session.getSessionKey());
            if (loginVO != null) {
                log.info("微信小程序通过code获取openid或直接登录："+JsonUtils.toJson(loginVO));
                return ResponseData.data(loginVO);
            }
            return ResponseData.fail( "登陆失败,请重新进入小程序");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseData.fail( "登陆失败,请重新进入小程序");
        }
    }

    @GetMapping("/wxminiapp/{appid}/login")
    @ApiOperation(value = "2,通过openid登陆")
    public ResponseData<BbbUserVO.LoginVO> login(@PathVariable String appid, String openid, String encryptedData, String ivStr) {
        if (StringUtils.isBlank(openid)) {
            return ResponseData.fail("openid为空");
        }
        try {
            final WxMaService wxService = WxMaConfiguration.getMaService(appid);
            String sessionKey = bbbUserAuthRpc.loadSessionKeyByWxOpenid(openid);
            if (StringUtils.isBlank(sessionKey)) {
                return ResponseData.fail("sessionKey为空");
            }
            //数据解密
            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, ivStr);
            log.info("小程序登陆用户信息："+JsonUtils.toJson(userInfo));
            BBcWxUserInfoDTO dto = new BBcWxUserInfoDTO();
            BeanCopyUtils.copyProperties(userInfo, dto);
            dto.setAppId(appid);
            BbbUserVO.LoginVO loginVO = bbbUserAuthRpc.addWxThirdLogin(dto);
            if (loginVO != null) {
                log.info("通过openid登陆："+JsonUtils.toJson(loginVO));
                return ResponseData.data(loginVO);
            }
            return ResponseData.fail( "登陆失败,请重新进入小程序");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseData.fail( "登陆失败,请重新进入小程序");
        }
    }
    @GetMapping("/wxminiapp/{appid}/bindInnerPhone")
    @ApiOperation(value = "3,微信自身手机号绑定")
    public ResponseData<BbbUserVO.LoginVO> bindInnerPhone(@PathVariable String appid, String openid, String encryptedData, String iv){
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        String sessionKey = bbbUserAuthRpc.loadSessionKeyByWxOpenid(openid);
        // 解密
        try {
            WxMaPhoneNumberInfo phoneInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
            log.info("手机信息：" + JsonUtils.toJson(phoneInfo));
            //更新用户手机
            BBcWxUserPhoneDTO dto = new BBcWxUserPhoneDTO();
            BeanCopyUtils.copyProperties(phoneInfo, dto);
            dto.setAppId(appid);
            if (StringUtils.isBlank(dto.getOpenId())) {
                dto.setOpenId(openid);
            }
            BbbUserVO.LoginVO vo = bbbUserAuthRpc.updateUserPhoneByWxInnerPhone(dto);
            log.info("微信自身手机号绑定："+JsonUtils.toJson(vo));
            return ResponseData.data(vo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseData.fail( "微信内部手机号绑定失败");
        }
    }

    @GetMapping("/wxminiapp/{appid}/bindOutPhone")
    @ApiOperation(value = "4,微信默认登陆后外部手机号绑定")
    public ResponseData<BbbUserVO.LoginVO> phone(@PathVariable String appid, String openid, String validCode, String phone){
        BbbUserVO.LoginVO vo = bbbUserAuthRpc.bindPhone(appid, openid, validCode, phone);
        log.info("微信默认登陆后外部手机号绑定："+JsonUtils.toJson(vo));
        return ResponseData.data(vo);
    }

    @ApiOperation("退出(1，H5退出-需要带上phone；2，小程序退出-需要带上openid),目的清空服务端的缓存")
    @GetMapping("/logout")
    public void logout(String phone, String openid) {
        bbbUserAuthRpc.logout(phone, openid);
    }


    @ApiOperation("忘记密码(手机验证码找回)")
    @PostMapping("/forgetByPhone")
    public void forgetPasswordByPhone(@Valid @RequestBody BbbUserDTO.ForgetByPhoneETO dto) {
        bbbUserAuthRpc.forgetPasswordByPhone(dto);
    }

}
