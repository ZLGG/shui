//package com.gs.lshly.facade.platform.controller.user;
//import cn.binarywang.wx.miniapp.api.WxMaService;
//import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
//import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
//import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
//import cn.hutool.core.util.StrUtil;
//import com.gs.lshly.common.response.ResponseData;
//import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
//import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
//import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
//import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
//import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
//import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
//import com.gs.lshly.common.utils.AESUtil;
//import com.gs.lshly.common.utils.BeanCopyUtils;
//import com.gs.lshly.common.utils.JsonUtils;
//import com.gs.lshly.common.utils.JwtUtil;
//import com.gs.lshly.middleware.log.Log;
//import com.gs.lshly.middleware.log.aop.LogAspect;
//import com.gs.lshly.middleware.wx.WxMaConfiguration;
//import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserAuthRpc;
//import com.gs.lshly.rpc.api.bbc.user.IBbcUserAuthRpc;
//import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.web.bind.annotation.*;
//import javax.validation.Valid;
//
///**
//* <p>
//*  前端控制器
//* </p>
//*
//* @author xxfc
//* @since 2020-10-27
//*/
//@RestController
//@RequestMapping("/auth")
//@Api(tags = "后台管理员登录-v1.1.0")
//@Slf4j
//public class UserAuthController {
//	
//	@DubboReference
//	private IUserRpc userRpc;
//
//    @DubboReference
//    private IBbcUserAuthRpc bbcUserAuthRpc;
//
//    @DubboReference
//    private IBbbUserAuthRpc bbbUserAuthRpc;
//
//    @ApiOperation("通过手机号码验证码-v1.1.0")
//    @PostMapping("/getPhoneCheck")
//    public ResponseData<Void> getPhoneCheck(@Valid @RequestBody UserDTO.GetPhoneValidCodeDTO dto) {
//    	userRpc.getPhoneValidCode(dto);
//        return ResponseData.success("短信发送成功");
//    }
//
//    @ApiOperation("登录")
//    @PostMapping("/login")
//    @Log(module = "登陆", func = "后台管理员-手机验证码")
//    public ResponseData<BbcUserVO.LoginVO> login(@Valid @RequestBody BbcUserDTO.LoginETO dto) {
//    	
//        BbcUserVO.LoginVO vo = bbcUserAuthRpc.login(dto);
//        setLogAspect(vo);
//        return ResponseData.data(vo);
////        BbbUserVO.LoginVO vo = bbbUserAuthRpc.login(dto);
////        return ResponseData.data(vo);
//    }
//
//}
