package com.gs.lshly.facade.bbc.controller.h5.user;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.utils.AESUtil;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.middleware.log.aop.LogAspect;
import com.gs.lshly.middleware.wx.WxMaConfiguration;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserAuthRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserAuthRpc;
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
@RequestMapping("/bbcAuth")
@Api(tags = "会员登录注册-v1.1.0")
@Slf4j
public class BbcUserAuthController {

    @DubboReference
    private IBbcUserAuthRpc bbcUserAuthRpc;

    @DubboReference
    private IBbbUserAuthRpc bbbUserAuthRpc;

    @ApiOperation("用户获取手机验证码")
    @PostMapping("/getPhoneCheck")
    public ResponseData<Void> getPhoneCheck(@Valid @RequestBody BbcUserDTO.GetPhoneValidCodeDTO dto) {
        bbcUserAuthRpc.getPhoneValidCode(dto);
        return ResponseData.success("短信发送成功");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    @Log(module = "登陆", func = "2C-手机验证码")
    public ResponseData<BbcUserVO.LoginVO> login(@Valid @RequestBody BbcUserDTO.LoginETO dto) {
    	
        BbcUserVO.LoginVO vo = bbcUserAuthRpc.login(dto);
        setLogAspect(vo);
        return ResponseData.data(vo);
//        BbbUserVO.LoginVO vo = bbbUserAuthRpc.login(dto);
//        return ResponseData.data(vo);
    }

    @ApiOperation("手机号注册(注册完成就已经登录)-v1.1.0")
    @PostMapping("/register")
    public ResponseData<String> register(@Valid @RequestBody BbbUserDTO.RegisterETO dto) {
        return ResponseData.data(bbbUserAuthRpc.register(dto));
    }
    
    @GetMapping("/wxminiapp/{appid}/openid")
    @ApiOperation(value = "1,微信小程序通过code获取openid")
    public ResponseData<BbcUserVO.LoginVO> login(@PathVariable String appid, String code) {
        if (StringUtils.isBlank(code)) {
            return ResponseData.fail("code为空");
        }
        try {
            WxMaService wxService = WxMaConfiguration.getMaService(appid);
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //通过微信openid查找，针对已登录过的用户
            BbcUserVO.LoginVO loginVO = bbcUserAuthRpc.loadUserByWxOpenid(appid, session.getOpenid(), session.getSessionKey(), session.getUnionid());
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
    @Log(module = "登陆", func = "2C-小程序")
    public ResponseData<BbcUserVO.LoginVO> login(@PathVariable String appid, String openid, String encryptedData, String ivStr) {
        if (StringUtils.isBlank(openid)) {
            return ResponseData.fail("openid为空");
        }
        try {
            final WxMaService wxService = WxMaConfiguration.getMaService(appid);
            String sessionKey = bbcUserAuthRpc.loadSessionKeyByWxOpenid(openid);
            if (StringUtils.isBlank(sessionKey)) {
                return ResponseData.fail("sessionKey为空");
            }
            //数据解密
            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, ivStr);
            log.info("小程序登陆用户信息："+JsonUtils.toJson(userInfo));
            BBcWxUserInfoDTO dto = new BBcWxUserInfoDTO();
            BeanCopyUtils.copyProperties(userInfo, dto);
            dto.setAppId(appid);
            BbcUserVO.LoginVO loginVO = bbcUserAuthRpc.addWxThirdLogin(dto);
            if (loginVO != null) {
                setLogAspect(loginVO);
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
    @Log(module = "登陆", func = "2C-小程序")
    public ResponseData<BbcUserVO.LoginVO> bindInnerPhone(@PathVariable String appid, String openid, String encryptedData, String iv){
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        String sessionKey = bbcUserAuthRpc.loadSessionKeyByWxOpenid(openid);
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
            BbcUserVO.LoginVO vo = bbcUserAuthRpc.updateUserPhoneByWxInnerPhone(dto);
            log.info("微信自身手机号绑定："+JsonUtils.toJson(vo));
            setLogAspect(vo);
            return ResponseData.data(vo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseData.fail( "微信内部手机号绑定失败");
        }
    }

    @GetMapping("/wxminiapp/{appid}/bindOutPhone")
    @ApiOperation(value = "4,微信默认登陆后外部手机号绑定")
    @Log(module = "登陆", func = "2C-小程序")
    public ResponseData<BbcUserVO.LoginVO> phone(@PathVariable String appid, String openid, String validCode, String phone){
        BbcUserVO.LoginVO vo = bbcUserAuthRpc.bindPhone(appid, openid, validCode, phone);
        log.info("微信默认登陆后外部手机号绑定："+JsonUtils.toJson(vo));
        setLogAspect(vo);
        return ResponseData.data(vo);
    }

    private void setLogAspect(BbcUserVO.LoginVO vo){
        if (vo!=null && StrUtil.isNotBlank(vo.getAuthToken())) {
            LogAspect.set(LogAspect.toDTO(JwtUtil.getJwtUser(vo.getAuthToken())));
        }
    }

    @ApiOperation("退出(1，H5退出-需要带上phone；2，小程序退出-需要带上openid),目的清空服务端的缓存")
    @GetMapping("/logout")
    public void logout(String phone, String openid) {
        bbcUserAuthRpc.logout(phone, openid);
    }

}
