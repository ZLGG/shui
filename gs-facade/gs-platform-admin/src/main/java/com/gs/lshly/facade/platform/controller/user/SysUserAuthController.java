package com.gs.lshly.facade.platform.controller.user;
import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.auth.rbac.RbacContants;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.vcode.kaptcha.CaptchaService;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserAuthRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserAuthRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysAccountAuthRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysUserRpc;

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
@Api(tags = "后台管理员登录-v1.1.0")
@Slf4j
@SuppressWarnings("unchecked")
public class SysUserAuthController {
	
	
    @Autowired
    private RedisUtil redisUtil;

    @DubboReference
    private ISysAccountAuthRpc sysAccountAuthRpc;
	
	@DubboReference
	private ISysUserRpc sysUserRpc;

    @DubboReference
    private IBbcUserAuthRpc bbcUserAuthRpc;

    @DubboReference
    private IBbbUserAuthRpc bbbUserAuthRpc;
    
    @DubboReference
    private UserDetailsService UserDetailsService;
    
    @Value("${auth.terminal}")
    private String terminal;

    @Value("${auth.prefixs}")
    private String[] authUriPrefix;
    
    private CaptchaService captchaService;

	@ApiOperation("通过手机号码验证码-v1.1.0")
    @PostMapping("/getPhoneCheck")
    public ResponseData<Void> getPhoneCheck(@Valid @RequestBody SysUserDTO.GetPhoneValidCodeDTO dto) {
    	sysUserRpc.getPhoneValidCode(dto);
        return ResponseData.success("短信发送成功");
    }

    @ApiOperation("登录-v1.1.0")
    @PostMapping("/loginByPhone")
    @Log(module = "登陆", func = "后台管理员-手机验证码")
    public ResponseData<Void> login(@Valid @RequestBody SysUserDTO.LoginDTO dto) {
    	AuthDTO auth = sysUserRpc.login(dto);
    	return ResponseData.data(UserDetailsService.loadUserByUsername(auth.getUsername()));
    	
    	/**
    	 * 
    	 
    	PermitNode allPermitNode = (PermitNode)redisUtil.get(RbacContants.SESSION_PERMIT_PREFIX + terminal + RbacContants.ALL_PERMIT);

    	String token = JwtUtil.createToken(new JwtUser(auth));
    	auth.setToken(token);
    	
    	auth.setPermitFuncs(permitFuncs);
    	
    	return ResponseData.data(auth);
    	*/
    	
    }
    
    @ApiOperation("验证用户名+密码-v1.1.0")
    @PostMapping("/loginByPhone")
    @Log(module = "验证用户名+密码", func = "后台管理员-手机验证码")
    public ResponseData<Boolean> checkPhoneCode(@Valid @RequestBody SysUserDTO.CheckDTO dto) {
    	String vcId = dto.getVcId();
        String vcode = dto.getVcode();
        
        if(captchaService.match(vcId, vcode)) {
        	return ResponseData.data(sysUserRpc.checkPhoneCode(dto));
        }else{
        	throw new BusinessException("验证码输入出错！");
        }
    	
    }

}
