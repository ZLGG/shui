package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.middleware.sms.IContactSMSService;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserThirdLogin;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserThirdLoginRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserAuthService;
import com.gs.lshly.common.enums.GenderEnum;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserPhoneDTO;
import com.gs.lshly.common.utils.AESUtil;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-27
*/
@Component
@Slf4j
public class BbbUserAuthServiceImpl implements IBbbUserAuthService {

    private static final String PhoneValidCodeGroup = "PhoneValidCode_";
    private static final String BbbPhoneUser = "BbbPhone_User_";
    private static final String WxOpenidUser = "wxOpenid_User_";
    private static final String WxSceneIdUser = "wxSceneId_User_";
    private static final String WxSceneIdWxUser = "wxSceneId_WxUser_";
    private static final String WxOpenidSessionKey = "wxOpenid_SessionKey_";
    private static final String WxSessionKeyOpenid = "wxSessionKey_Openid_";
    private static final String EmailValidCodeGroup = "EmailValidCode_";

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IUserThirdLoginRepository thirdLoginRepository;

//    @Autowired
//    private ISMSService smsService;

    @Autowired
    private IContactSMSService iContactSMSService;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String register(BbbUserDTO.RegisterETO dto) {
        if(null == dto.getUserPwd() || null == dto.getUserPwdCfm() || null == dto.getValidCode() || null == dto.getPhone()){
            throw new BusinessException("注册信息错误");
        }
        if(!dto.getUserPwd().equals(dto.getUserPwdCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        //校验验证码
        Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
        String validCode = code != null ? code + "" : "";
        if (!org.apache.commons.lang3.StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("手机验证码不匹配");
        }
        QueryWrapper<User> userQueryWrapper = MybatisPlusUtil.query();
        userQueryWrapper.eq("phone",AESUtil.aesEncrypt(dto.getPhone()));
        User user = repository.getOne(userQueryWrapper);
        if(null != user){
            //throw new BusinessException("手机号码已注册");
            //为了方便前端开如，此处注册就是修改
            user.setPhone(AESUtil.aesEncrypt(dto.getPhone()));
            user.setUserName(AESUtil.aesEncrypt(dto.getPhone()));
            user.setUserPwd(PwdUtil.encode(dto.getUserPwd()));
            user.setType(UserTypeEnum._2C用户.getCode());
            user.setState(UserStateEnum.启用.getCode());
            repository.updateById(user);
        }else{
            user  = new User();
            user.setPhone(AESUtil.aesEncrypt(dto.getPhone()));
            user.setUserName(AESUtil.aesEncrypt(dto.getPhone()));
            user.setUserPwd(PwdUtil.encode(dto.getUserPwd()));
            user.setType(UserTypeEnum._2C用户.getCode());
            user.setState(UserStateEnum.启用.getCode());
            repository.save(user);
        }
        //解密
        user.setPhone(dto.getPhone());
        user.setUserName(dto.getPhone());
        return this.regCreateUserJwtToken(user);
    }

    /** 注册成功就生成token**/
    private String regCreateUserJwtToken(User user) {
        return JwtUtil.createToken(new JwtUser(userToAuthDTO(user)));
    }

    @Override
    public AuthDTO loadUserByUsername(String username) {
        User user = repository.getOne(new QueryWrapper<User>().eq("user_name", username));
        return userToAuthDTO(user);
    }

    private static AuthDTO userToAuthDTO(User user) {
        if(null == user){
            throw new BusinessException("用户不存在");
        }
        Integer userType = UserTypeEnum._2B用户.getCode().equals(user.getType()) ? UserTypeEnum._2B用户.getCode() : UserTypeEnum._2C用户.getCode();
        return new AuthDTO().setId(user.getId())
                .setState(user.getState())
                .setType(userType)
                .setUsername(user.getUserName())
                .setPassword(user.getUserPwd())
                .setHeadImg(user.getHeadImg())
                .setShopId(user.getFromShopId())
                .setPhone(user.getPhone());
    }

    public static BbbUserVO.LoginVO userToLoginVO(User user, String openid) {
        BbbUserVO.LoginVO vo = new BbbUserVO.LoginVO();
        AuthDTO authDTO = userToAuthDTO(user);
        JwtUser jwtUser = new JwtUser(authDTO);
        BeanCopyUtils.copyProperties(user, vo);
        jwtUser.setWxOpenid(openid);
        vo.setWxOpenid(openid);
        vo.setAuthToken(JwtUtil.createToken(jwtUser));
        return vo;
    }

    @Override
    public void getPhoneValidCode(BbbUserDTO.GetPhoneValidCodeDTO dto) {
        //通过手机查找是已经注册了会员，如果已经注册，则发送用户登陆验证码，否则发送注册验证码
        User user = repository.getOne(new QueryWrapper<User>().eq("phone", dto.getPhone()));
        String validCode = null;
        try {
            if (user != null) {
                iContactSMSService.userLogin(dto.getPhone());
//                validCode = smsService.sendLoginSMSCode(dto.getPhone());
            } else {
                iContactSMSService.merchantLogin(dto.getPhone());
//                validCode = smsService.sendRegistrySMSCode(dto.getPhone());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("短信发送失败!" + (e.getMessage().contains("限流") ? "发送频率过高" : ""));
        }
        //验证码失效时间10分账
//        log.info("设置-手机号码："+dto.getPhone()+"-验证码："+validCode);
//        redisUtil.set(PhoneValidCodeGroup + dto.getPhone(), validCode, 10 * 60);
    }

    @Override
    public BbbUserVO.LoginVO login(BbbUserDTO.LoginETO dto) {

        //校验验证码
        Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
        String validCode = code != null ? code + "" : "";
        log.info("获取-手机号码："+dto.getPhone()+"-验证码："+validCode);
        if (!StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("验证码不匹配");
        }
        BbbUserVO.LoginVO vo = (BbbUserVO.LoginVO) redisUtil.get(BbbPhoneUser + dto.getPhone());
        if (vo != null && JwtUtil.getJwtUser(vo.getAuthToken()) != null) {
            return vo;
        }
        User user = repository.getOne(new QueryWrapper<User>().eq("phone", dto.getPhone()));
        if (user == null) {
            user = new User();
            user.setState(UserStateEnum.启用.getCode()).setPhone(dto.getPhone()).setType(UserTypeEnum._2C用户.getCode());
            repository.save(user);
        }
        vo = userToLoginVO(user, null);
        redisUtil.set(BbbPhoneUser + dto.getPhone(), vo, 60 * 60 * 5);
        return vo;
    }

    @Override
    public BbbUserVO.LoginVO loadUserByWxOpenid(String appid, String openid, String sessionKey, String unionid) {

        //数据库中并无该openid，缓存sessionKey，登录需要调用下一个login接口
        String oldSessionKey = (String)redisUtil.get(WxOpenidSessionKey + openid);
        redisUtil.set(WxOpenidSessionKey + openid, sessionKey, 60 * 60 * 24);
        //用户获取手机时使用
        log.info("oldSessionKey:"+oldSessionKey+";newSessionKey:"+sessionKey);
        if (StringUtils.isNotBlank(oldSessionKey) && !oldSessionKey.equals(sessionKey)) {
            redisUtil.del(WxSessionKeyOpenid + oldSessionKey);
        }
        redisUtil.set(WxSessionKeyOpenid + sessionKey, openid);

        BbbUserVO.LoginVO vo = (BbbUserVO.LoginVO) redisUtil.get(WxOpenidUser + openid);
        if (vo != null) {
            return vo;
        }
        UserThirdLogin thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("unionid", unionid).isNotNull("user_id").last(" limit 1"));
        User user = null;
        if (thirdLogin!=null && StringUtils.isNotBlank(thirdLogin.getUserId())) {
            user = repository.getById(thirdLogin.getUserId());
            if (user != null) {
                vo = userToLoginVO(user, openid);
                //缓存user
                redisUtil.set(WxOpenidUser + openid, vo, 60 * 60 * 5);
            }
        }
        if (user == null && StringUtils.isNotEmpty(sessionKey)) {
            vo = new BbbUserVO.LoginVO();
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
    public BbbUserVO.LoginVO addWxThirdLogin(BBcWxUserInfoDTO dto) {
        BbbUserVO.LoginVO vo = (BbbUserVO.LoginVO)redisUtil.get(WxOpenidUser + dto.getOpenId());
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
            vo = new BbbUserVO.LoginVO();
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
    public BbbUserVO.LoginVO bindPhone(String appid, String openid, String phone) {
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
    public BbbUserVO.LoginVO updateUserPhoneByWxInnerPhone(BBcWxUserPhoneDTO dto) {
        return bindPhone(dto.getAppId(), dto.getOpenId(), dto.getPhoneNumber());
    }

    @Override
    public BbbUserVO.LoginVO bindPhone(String appid, String openid, String validCodeVar, String phone) {
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
            redisUtil.del(BbbPhoneUser + phone);
        }

        //小程序退出
        if (StringUtils.isNotBlank(openid)) {
            redisUtil.del(WxOpenidUser + openid);
        }
    }

    @Override
    public void forgetPasswordByPhone(BbbUserDTO.ForgetByPhoneETO dto) {
        //校验验证码
        Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
        String validCode = code != null ? code + "" : "";
        System.out.println("validCode:~~~~~~~~~~~~~"+validCode);
        if (!StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("验证码不匹配");
        }
        if(null == dto.getPassword() || null == dto.getPasswordCfm() || null == dto.getValidCode() || null == dto.getPhone()){
            throw new BusinessException("注册信息错误");
        }
        if(!dto.getPassword().equals(dto.getPasswordCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        //查询手机号对应的用户
        QueryWrapper<User> query = MybatisPlusUtil.query();
        query.and(i->i.eq("phone",dto.getPhone()));
        User one = repository.getOne(query);
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("没有查询到对应的用户");
        }
        one.setUserPwd(PwdUtil.encode(dto.getPassword()));
        repository.updateById(one);


    }

    @Override
    public String test() {
    	return null;
//        return smsService.sendPickUpSMSCode("18628300710","13156464","dddd","哈哈哈店铺");
    }

    @Override
    public void forgetByEmail(BbbUserDTO.ForgetByEmailETO dto) {
        //校验验证码
        Object code = redisUtil.get( EmailValidCodeGroup+ dto.getEmailNum());
        String validCode = code != null ? code + "" : "";
        if (!StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("验证码不匹配");
        }
        if(null == dto.getPassword() || null == dto.getPasswordCfm() || null == dto.getValidCode() || null == dto.getEmailNum()){
            throw new BusinessException("注册信息错误");
        }
        if(!dto.getPassword().equals(dto.getPasswordCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        //查询邮箱对应的用户
        QueryWrapper<User> query = MybatisPlusUtil.query();
        query.and(i->i.eq("email",dto.getEmailNum()));
        User one=null;
        try {
            one = repository.getOne(query);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BusinessException("此邮箱重复注册了商家");
        }
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("没有查询到对应的用户");
        }
        one.setUserPwd(PwdUtil.encode(dto.getPassword()));
        repository.updateById(one);
    }

    @Override
    public ResponseData<BbbUserVO.LoginVO> loadUserBySceneId(long sceneId) {
        //查缓存用户
        BbbUserVO.LoginVO vo = (BbbUserVO.LoginVO) redisUtil.get(WxSceneIdUser + sceneId);
        if (vo != null) {
            ResponseData<BbbUserVO.LoginVO> data = ResponseData.data(vo);
            data.setMessage("已登陆");
            return data;
        }
        BBcWxUserInfoDTO wxUser  = (BBcWxUserInfoDTO)redisUtil.get(WxSceneIdWxUser + sceneId);
        if (wxUser != null) {
            return ResponseData.success("已扫码,待绑定手机");
        }
        return ResponseData.success("未扫码");
    }

    @Override
    public void loadUserBySceneId(String sceneId, BBcWxUserInfoDTO dto) {
        //1，通过unionid查询用户是否存在
        BbbUserVO.LoginVO vo = (BbbUserVO.LoginVO) redisUtil.get(WxSceneIdUser + sceneId);
        if (vo != null) {
            return ;
        }
        UserThirdLogin thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("unionid", dto.getUnionId()).isNotNull("user_id").last(" limit 1"));
        User user = null;
        //1.1如果存在，将场景id的数据与用户对应，并生成token，用户放入缓存-标记登陆成功。
        if (thirdLogin != null) {
            user = repository.getById(thirdLogin.getUserId());
            if (user != null) {
                vo = userToLoginVO(user, dto.getOpenId());
                //缓存user
                redisUtil.set(WxSceneIdUser + sceneId, vo, 60 * 5);
            }
        }
        //1.2如果不存在，则返回场景id，不生成token，微信用户放入缓存-标记待绑定手机
        if (user == null) {
            //持久化扫码记录
            thirdLogin = thirdLoginRepository.getOne(new QueryWrapper<UserThirdLogin>().eq("app_id", dto.getAppId()).eq("openid", dto.getOpenId()));
            if (thirdLogin == null) {
                thirdLogin = new UserThirdLogin();
                thirdLogin.setNickname(dto.getNickName()).setHeadImg(dto.getAvatarUrl())
                        .setSex("1".equals(dto.getGender()) ? GenderEnum.男.getCode() : ("2".equals(dto.getGender()) ? GenderEnum.女.getCode() : GenderEnum.未知.getCode()))
                        .setOpenid(dto.getOpenId()).setUnionid(dto.getUnionId()).setAppId(dto.getAppId());
                thirdLoginRepository.save(thirdLogin);
            } else {
                thirdLogin.setHeadImg(dto.getAvatarUrl());
                thirdLogin.setSex("1".equals(dto.getGender()) ? GenderEnum.男.getCode() : ("2".equals(dto.getGender()) ? GenderEnum.女.getCode() : GenderEnum.未知.getCode()));
                thirdLogin.setNickname(dto.getNickName());
                thirdLoginRepository.updateById(thirdLogin);
            }
            dto.setThirdLoginId(thirdLogin.getId());
            //缓存wxuser
            redisUtil.set(WxSceneIdWxUser + sceneId, dto, 60 * 5);
        }

    }

    @Override
    public BbbUserVO.LoginVO bindQRPhone(long sceneId, String validCodeVar, String phone) {
        //校验验证码
        Object code = redisUtil.get(PhoneValidCodeGroup + phone);
        String validCode = code != null ? code + "" : "";
        log.info("收到手机{}验证码{}，redis缓存中的验证码{},匹配{}", phone,validCodeVar, validCode, StringUtils.equals(validCode, validCodeVar));
        if (!StringUtils.equals(validCode, validCodeVar)) {
            throw new BusinessException("验证码不匹配");
        }
        BBcWxUserInfoDTO wxUser = (BBcWxUserInfoDTO) redisUtil.get(WxSceneIdWxUser + sceneId);
        if (wxUser == null) {
            throw new BusinessException("扫码已过期，请重新扫码");
        }
        //会员入库
        User user = repository.getOne(new QueryWrapper<User>().eq("phone", phone));
        if (user == null) {
            user = new User().setPhone(phone).setUserName(wxUser.getNickName()).setType(UserTypeEnum._2C用户.getCode()).setState(UserStateEnum.启用.getCode());
        }
        user.setSex("1".equals(wxUser.getGender()) ? GenderEnum.男.getCode() : ("2".equals(wxUser.getGender()) ? GenderEnum.女.getCode() : GenderEnum.未知.getCode()))
                .setHeadImg(wxUser.getAvatarUrl());
        if (StringUtils.isBlank(user.getUserName())) {
            user.setUserName(wxUser.getNickName());
        }
        if (StringUtils.isBlank(user.getId())) {
            repository.save(user);
        } else {
            repository.updateById(user);
        }
        thirdLoginRepository.updateById(new UserThirdLogin().setId(wxUser.getThirdLoginId()).setUserId(user.getId()));
        BbbUserVO.LoginVO vo = userToLoginVO(user, wxUser.getOpenId());
        //缓存user
        redisUtil.set(WxSceneIdUser + sceneId, vo, 60 * 5);
        return vo;
    }
}
