package com.gs.lshly.facade.merchant.controller.pc.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.OAuth2UserTypeEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.common.OAuth2DTO;
import com.gs.lshly.common.struct.common.OAuth2VO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.IOAuth2Rpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 商家账号登录前端控制器
 * </p>
 * <p>
 * 1,建表oauth2_client表:client,接口权限.
 * 2,建表oauth2_code表:分配为user分配code,code使用过后立即删除.
 * 3,建表oauth2_token表:为user生成访问token,refresh_token.
 *
 * @author lxus
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/oauth/merchant")
@Api(tags = "商家账号OAuth2认证")
public class MerchantAccountOAuth2Controller {

    @DubboReference
    IOAuth2Rpc oAuth2Rpc;

    @DubboReference
    private IPCMerchMerchantAccountRpc pcMerchMerchantAccountRpc;

    @ApiOperation("根据已登录的用户为client[244d19d749aa438a82cd91182303889b]分配code,authUserType[平台为10,商家为20]")
    @GetMapping("/code/{clientId}")
    public ResponseData<String> allocateCode(@PathVariable String clientId) {
        OAuth2DTO.AllocateCodeDTO dto = new OAuth2DTO.AllocateCodeDTO();
        dto.setClientId(clientId);
        String code = oAuth2Rpc.allocateCode(OAuth2UserTypeEnum.商家账号, dto);
        return ResponseData.data(code);
    }

    @ApiOperation("校验code并返回token")
    @GetMapping("/token/{code}")
    public ResponseData<OAuth2VO.TokenVO> generationToken(@PathVariable String code) {
        OAuth2DTO.GenerationTokenDTO dto = new OAuth2DTO.GenerationTokenDTO();
        dto.setCode(code);
        OAuth2VO.TokenVO token = oAuth2Rpc.generationToken(dto);
        return ResponseData.data(token);
    }

    @ApiOperation("通过token获取用户信息")
    @PostMapping("/userInfo")
    public ResponseData<OAuth2VO.SysUserVO> userInfo(String token) {
        OAuth2DTO.UserInfoDTO dto = new OAuth2DTO.UserInfoDTO();
        dto.setToken(token);
        OAuth2VO.MerchantVO userVO = oAuth2Rpc.merchantInfo(dto);
        return ResponseData.data(userVO);
    }

    @ApiOperation("通过refresh_token刷新访问token.")
    @GetMapping("/refresh_token/{refreshToken}")
    public ResponseData<OAuth2VO.TokenVO> refreshToken(@PathVariable String refreshToken) {
        OAuth2DTO.RefreshTokenDTO dto = new OAuth2DTO.RefreshTokenDTO();
        dto.setRefreshToken(refreshToken);
        OAuth2VO.TokenVO token = oAuth2Rpc.refreshToken(dto);
        return ResponseData.data(token);
    }

    @ApiOperation("用户获取手机验证码")
    @GetMapping("/getPhoneCheck/{phone}")
    public ResponseData<Void> getPhoneCheck(@PathVariable String phone) {
        pcMerchMerchantAccountRpc.getPhoneValidCode(phone);
        return ResponseData.success("短信发送成功");
    }

    @ApiOperation("忘记密码(手机验证码找回)")
    @PostMapping("/forgetByPhone")
    public ResponseData<Void> forgetPasswordByPhone(@Valid @RequestBody PCMerchMerchantAccountDTO.ForgetByPhoneETO dto) {
        return ResponseData.data(pcMerchMerchantAccountRpc.forgetPasswordByPhone(dto));
    }
    @ApiOperation("忘记密码(邮箱验证码找回)")
    @PostMapping("/forgetByEmail")
    public ResponseData<Void> forgetByEmail(@Valid @RequestBody PCMerchMerchantAccountDTO.ForgetByEmailETO dto) {
        return ResponseData.data(pcMerchMerchantAccountRpc.forgetByEmail(dto));
    }
    @ApiOperation("获取邮箱验证码")
    @GetMapping("/getEmailCode/{email}")
    public ResponseData<Void> getEmailNum(@PathVariable String email) {
        pcMerchMerchantAccountRpc.getEmailNum(email);
        return ResponseData.success(MsgConst.SEND_EMAIL_SUCCESS);
    }

}
