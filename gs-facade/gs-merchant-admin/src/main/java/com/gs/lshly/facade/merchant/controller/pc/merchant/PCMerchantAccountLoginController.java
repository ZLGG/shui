package com.gs.lshly.facade.merchant.controller.pc.merchant;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.dto.CommonPhoneLoginDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchantAccountLoginDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.middleware.vcode.kaptcha.CaptchaService;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountAuthRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-27
*/
@RestController
@RequestMapping("/auth")
@Api(tags = "商户管理员登录-v1.1.0")
@Slf4j
@SuppressWarnings("unchecked")
public class PCMerchantAccountLoginController {
	
	@DubboReference
	private IPCMerchMerchantAccountAuthRpc pcMerchMerchantAccountAuthRpc;
	
    @DubboReference
    private IPCMerchMerchantAccountRpc pcMerchMerchantAccountRpc;
    
    @DubboReference
    private ISiteRpc siteRpc;
    
    @Autowired
    private CaptchaService captchaService;

    @ApiOperation("1、验证用户名+密码是否正确-v1.1.0")
    @PostMapping("/checkPhonePassword")
    @Log(module = "验证用户名+密码", func = "商户管理员-手机验证码")
    public ResponseData<Boolean> checkPhonePassword(@Valid @RequestBody PCMerchantAccountLoginDTO.CheckDTO dto) {
    	String vcId = dto.getVcId();
        String vcode = dto.getVcode();
        
        if(captchaService.match(vcId, vcode)) {
        	PCMerchMerchantAccountVO.AccountDetailVO userDetails = pcMerchMerchantAccountRpc.getByPhone(dto.getUsername());
            if (userDetails == null || StringUtils.isEmpty(userDetails.getId())) {
                throw new BusinessException("找不到该用户");
            }
            if(PwdUtil.matches(dto.getPassword(), userDetails.getUserPwd())){
            	return ResponseData.data(true);
            }else{
            	throw new BusinessException("密码出错输入出错！");
            }
        }else{
        	throw new BusinessException("验证码输入出错！");
        }
    }
    
    @ApiOperation("2、获取手机验证码")
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

    @ApiOperation("3、通过手机号加验证码登录")
    @PostMapping("/loginByPhone")
    public ResponseData<BbbUserVO.LoginVO> login(@Valid @RequestBody CommonPhoneLoginDTO.Login dto) {
        if (StringUtils.isBlank(dto.getPhone())) {
            return ResponseData.fail("手机号不能为空");
        }
        BbbUserVO.LoginVO vo = pcMerchMerchantAccountAuthRpc.login(dto);
        
        return ResponseData.data(vo);
    }

    
}
