package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserThirdLogin;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserThirdLoginRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserAuthService;
import com.gs.lshly.common.constants.SecurityConstants;
import com.gs.lshly.common.enums.GenderEnum;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.dto.UserDTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.utils.AESUtil;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.sms.ISMSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-27
*/
@Component
@Slf4j
public class BbcUserAuthServiceImpl implements IBbcUserAuthService {

    private static final String PhoneValidCodeGroup = "PhoneValidCode_";
    private static final String BbcH5PhoneUser = "BbcH5Phone_User_";
    private static final String WxOpenidUser = "wxOpenid_User_";
    private static final String WxOpenidSessionKey = "wxOpenid_SessionKey_";
    private static final String WxSessionKeyOpenid = "wxSessionKey_Openid_";

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IUserThirdLoginRepository thirdLoginRepository;

    @Autowired
    private ISMSService smsService;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public AuthDTO loadUserByUsername(String username) {
        User user = repository.getOne(new QueryWrapper<User>().eq("user_name", username));
        return userToAuthDTO(user);
    }

    private static AuthDTO userToAuthDTO(User user) {
        if (user != null) {
            return new AuthDTO().setId(user.getId())
                    .setState(user.getState())
                    .setType(user.getType())
                    .setUsername(user.getUserName())
                    .setPassword(user.getUserPwd())
                    .setHeadImg(user.getHeadImg())
                    .setShopId(user.getFromShopId())
                    .setPhone(user.getPhone());
        }
        return null;
    }

    public static BbcUserVO.LoginVO userToLoginVO(User user, String openid) {
        BbcUserVO.LoginVO vo = new BbcUserVO.LoginVO();
        AuthDTO authDTO = userToAuthDTO(user);
        JwtUser jwtUser = new JwtUser(authDTO);
        BeanCopyUtils.copyProperties(user, vo);
        jwtUser.setWxOpenid(openid);
        vo.setWxOpenid(openid);
        vo.setAuthToken(JwtUtil.createToken(jwtUser));
        return vo;
    }

    @Override
    public void getPhoneValidCode(BbcUserDTO.GetPhoneValidCodeDTO dto) {
        //通过手机查找是已经注册了会员，如果已经注册，则发送用户登陆验证码，否则发送注册验证码
        User user = repository.getOne(new QueryWrapper<User>().eq("phone", dto.getPhone()));
        String validCode = null;
        try {
            if (user != null) {
                validCode = smsService.sendLoginSMSCode(dto.getPhone());
            } else {
                validCode = smsService.sendRegistrySMSCode(dto.getPhone());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("短信发送失败!" + (e.getMessage().contains("限流") ? "发送频率过高" : ""));
        }
        //验证码失效时间10分账
        log.info("设置-手机号码："+dto.getPhone()+"-验证码："+validCode);
        redisUtil.set(PhoneValidCodeGroup + dto.getPhone(), validCode, 10 * 60);
    }

    @Override
    public BbcUserVO.LoginVO login(BbcUserDTO.LoginETO dto) {

        /**
    	 * 验证码登录
    	 */
    	if(dto.getType()==null||dto.getType().equals(1)){
    		 //校验验证码
            Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
            String validCode = code != null ? code + "" : "";
            log.info("获取-手机号码："+dto.getPhone()+"-验证码："+validCode);
            if (!StringUtils.equals(validCode, dto.getValidCode())) {
                throw new BusinessException("验证码不匹配");
            }
            BbcUserVO.LoginVO vo = (BbcUserVO.LoginVO) redisUtil.get(BbcH5PhoneUser + dto.getPhone());
            if (vo != null) {
                return vo;
            }

            //1、获取加密对象
            String AESPhone = AESUtil.aesEncrypt(dto.getPhone());
            User user = repository.getOne(new QueryWrapper<User>().eq("phone", AESPhone));
            if (user == null) {
                user = new User();
                UserDTO.ETO userDTO = new UserDTO.ETO();
                userDTO.setState(UserStateEnum.启用.getCode()).setPhone(AESPhone).setType(UserTypeEnum._2C用户.getCode()).setId(String.valueOf(IdWorker.getId())).setFlag(false);
                repository.saveUserInfo(userDTO);
            }
            vo = userToLoginVO(user, null);
            redisUtil.set(BbcH5PhoneUser + dto.getPhone(), vo, SecurityConstants.EXPIRATION);
            return vo;
    	}else{
    		
    		User user = repository.getOne(new QueryWrapper<User>().eq("phone", AESUtil.aesEncrypt(dto.getPhone())));
            if (user == null) {
                throw new BusinessException("请先注册");
            }
            if (PwdUtil.matches(dto.getValidCode(), user.getUserPwd())) {
	            BbcUserVO.LoginVO vo = userToLoginVO(user, null);
	            redisUtil.set(BbcH5PhoneUser + dto.getPhone(), vo, SecurityConstants.EXPIRATION);
	            return vo;
            }else{
            	throw new BusinessException("密码错误");
            }
    	}
       
    }

    public static void main(String[] args) {
        System.out.println(IdWorker.getId());
    }

    @Override
    public BbcUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid) {

        //数据库中并无该openid，缓存sessionKey，登录需要调用下一个login接口
        String oldSessionKey = (String)redisUtil.get(WxOpenidSessionKey + openid);
        redisUtil.set(WxOpenidSessionKey + openid, sessionKey, 60 * 60 * 24);
        //用户获取手机时使用
        log.info("oldSessionKey:" + oldSessionKey + ";newSessionKey:" + sessionKey);
        if (StringUtils.isNotBlank(oldSessionKey) && !oldSessionKey.equals(sessionKey)) {
            redisUtil.del(WxSessionKeyOpenid + oldSessionKey);
        }
        redisUtil.set(WxSessionKeyOpenid + sessionKey, openid);

        BbcUserVO.LoginVO vo = (BbcUserVO.LoginVO) redisUtil.get(WxOpenidUser + openid);
        if (vo != null) {
            return vo;
        }
        UserThirdLogin thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("unionid", unionid).isNotNull("user_id").last(" limit 1"));
        User user = null;
        if (thirdLogin!=null) {
            user = repository.getById(thirdLogin.getUserId());
            if (user != null) {
                vo = userToLoginVO(user, openid);
                //缓存user
                redisUtil.set(WxOpenidUser + openid, vo, 60 * 60 * 5);
            }
        }
        if (user == null && StringUtils.isNotEmpty(sessionKey)) {
            vo = new BbcUserVO.LoginVO();
            vo.setWxOpenid(openid);
        }
        return vo;
    }

    @Override
    public String loadSessionKeyByWxOpenid(String openid) {
        return (String) redisUtil.get(WxOpenidSessionKey + openid);
    }

    /**
     * 微信小程序登陆，如果用户不存在，生成默认用户
     * @param dto
     * @return
     */
    @Override
    public BbcUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto) {
        BbcUserVO.LoginVO vo = (BbcUserVO.LoginVO)redisUtil.get(WxOpenidUser + dto.getOpenId());
        if (vo != null) {
            return vo;
        }
        UserThirdLogin thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("unionid", dto.getUnionId()).isNotNull("user_id").last(" limit 1"));
        User user = null;
        if (thirdLogin != null) {
            user = repository.getById(thirdLogin.getUserId());
            if (user != null) {
                vo = userToLoginVO(user, dto.getOpenId());
                //缓存user
                redisUtil.set(WxOpenidUser + dto.getOpenId(), vo, 60 * 60 * 5);
            }
        }
        if (vo == null) {
            vo = new BbcUserVO.LoginVO();
            vo.setWxOpenid(dto.getOpenId());
        }
        thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("app_id", dto.getAppId()).eq("openid", dto.getOpenId()));
        if (thirdLogin != null) {
            thirdLogin.setHeadImg(dto.getAvatarUrl());
            thirdLogin.setSex("1".equals(dto.getGender()) ? GenderEnum.男.getCode() : ("2".equals(dto.getGender()) ? GenderEnum.女.getCode() : GenderEnum.未知.getCode()));
            thirdLogin.setNickname(dto.getNickName()).setUnionid(dto.getUnionId()).setUserId(user!=null ? user.getId() : null);
            thirdLoginRepository.updateById(thirdLogin);

        } else {
            thirdLogin = new UserThirdLogin();
            thirdLogin.setNickname(dto.getNickName()).setHeadImg(dto.getAvatarUrl())
                    .setSex("1".equals(dto.getGender()) ? GenderEnum.男.getCode() : ("2".equals(dto.getGender()) ? GenderEnum.女.getCode() : GenderEnum.未知.getCode()))
                    .setOpenid(dto.getOpenId()).setUnionid(dto.getUnionId()).setAppId(dto.getAppId()).setUserId(user!=null ? user.getId() : null);
            thirdLoginRepository.save(thirdLogin);
        }
        return vo;
    }

    @Override
    public BbcUserVO.LoginVO bindPhone(String appid, String openid, String phone) {
        UserThirdLogin thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("app_id", appid).eq("openid", openid));
        //用户未绑定
        User user = repository.getOne(new QueryWrapper<User>().eq("phone", phone));
        if (user == null) {
            user = new User().setPhone(phone).setUserName(thirdLogin.getNickname()).setType(UserTypeEnum._2C用户.getCode()).setState(UserStateEnum.启用.getCode());
        }
        user.setSex(thirdLogin.getSex()).setHeadImg(thirdLogin.getHeadImg());
        if (StringUtils.isBlank(user.getUserName())) {
            user.setUserName(thirdLogin.getNickname());
        }
        if (StringUtils.isBlank(user.getId())) {
            repository.save(user);
        } else {
            repository.updateById(user);
        }
        thirdLoginRepository.updateById(new UserThirdLogin().setId(thirdLogin.getId()).setUserId(user.getId()));
        return userToLoginVO(user, thirdLogin.getOpenid());
    }

    @Override
    public BbcUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto) {
        return bindPhone(dto.getAppId(), dto.getOpenId(), dto.getPhoneNumber());
    }

    @Override
    public BbcUserVO.LoginVO bindPhone(String appid, String openid, String validCodeVar, String phone) {
        //校验验证码
        Object code = redisUtil.get(PhoneValidCodeGroup + phone);
        String validCode = code != null ? code + "" : "";
        log.info("收到手机{}验证码{}，redis缓存中的验证码{},匹配{}",
                phone,validCodeVar, validCode, StringUtils.equals(validCode, validCodeVar));
        if (!StringUtils.equals(validCode, validCodeVar)) {
            throw new BusinessException("验证码不匹配");
        }
        return bindPhone(appid, openid, phone);
    }

    @Override
    public void logout(String phone, String openid) {
        //H5退出
        if (StringUtils.isNotBlank(phone)) {
            redisUtil.del(BbcH5PhoneUser + phone);
        }

        //小程序退出
        if (StringUtils.isNotBlank(openid)) {
            redisUtil.del(WxOpenidUser + openid);
        }
    }

    @Override
    public BbcUserVO.ThirdVO innerGetWXNickName(String userId) {
        QueryWrapper<UserThirdLogin> query = MybatisPlusUtil.query();
        query.and(i->i.eq("user_id",userId));
        query.last("limit 0,1");
        UserThirdLogin one = thirdLoginRepository.getOne(query);
        if (ObjectUtils.isNotEmpty(one)){
            BbcUserVO.ThirdVO thirdVO = new BbcUserVO.ThirdVO();
            thirdVO.setNickName(one.getNickname());
            return thirdVO;
        }
        return null;
    }
}
