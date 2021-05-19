package com.gs.lshly.facade.platform.controller.user;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;
import com.gs.lshly.common.utils.AES;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.common.utils.PwdUtil;
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
    
    @Autowired
    private CaptchaService captchaService;

	@ApiOperation("2、通过手机号码验证码-v1.1.0")
    @PostMapping("/getPhoneCheck")
    public ResponseData<Void> getPhoneCheck(@Valid @RequestBody SysUserDTO.GetPhoneValidCodeDTO dto) {
    	sysUserRpc.getPhoneValidCode(dto);
        return ResponseData.success("短信发送成功");
    }

    @ApiOperation("3、通过手机号码+验证码登录-v1.1.0")
    @PostMapping("/loginByPhone")
    @Log(module = "登陆", func = "后台管理员-手机验证码")
    public ResponseData<Void> login(@Valid @RequestBody SysUserDTO.LoginDTO dto) {
    	
    	AuthDTO auth = sysUserRpc.login(dto);
    	String createToken = JwtUtil.createToken(new JwtUser(auth));
    	auth.setToken(createToken);
    	return ResponseData.data(auth);
    }

    
    @ApiOperation("1、验证用户名+密码是否正确-v1.1.0")
    @PostMapping("/checkPhonePassword")
    @Log(module = "验证用户名+密码", func = "后台管理员-手机验证码")
    public ResponseData<Boolean> checkPhonePassword(@Valid @RequestBody SysUserDTO.CheckDTO dto) {
    	String vcId = dto.getVcId();
        String vcode = dto.getVcode();
        
        if(captchaService.match(vcId, vcode)) {
        	/**
        	 * 解码
        	 */
        	String password = AES.Encrypt(dto.getPassword());
        	dto.setPassword(password);
        	SysUserVO.DetailVO userDetails = sysUserRpc.getSysUserByName(dto.getUsername());
            if (userDetails == null || StringUtils.isEmpty(userDetails.getId())) {
                throw new BusinessException("找不到该用户");
            }
            if(PwdUtil.matches(dto.getPassword(), userDetails.getPwd())){
            	return ResponseData.data(true);
            }else{
            	throw new BusinessException("密码出错输入出错！");
            }
        }else{
        	throw new BusinessException("验证码输入出错！");
        }
    }

}
