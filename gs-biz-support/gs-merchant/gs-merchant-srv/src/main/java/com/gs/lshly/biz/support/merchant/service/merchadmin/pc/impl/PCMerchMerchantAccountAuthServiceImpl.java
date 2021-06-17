package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountStateEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountTypeEnum;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantAccountAuthService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.dto.CommonPhoneLoginDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.sms.IAliSMSService;
import com.gs.lshly.middleware.sms.ISMSService;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-08
*/
@Component
@Slf4j
public class PCMerchMerchantAccountAuthServiceImpl implements IPCMerchMerchantAccountAuthService {

    private static final String PhoneValidCodeGroup = "PhoneValidCode_";
    
    private static final String H5PhoneUser = "h5Phone_User_";
    private static final String WxOpenidUser = "wxOpenid_User_";
    private static final String WxOpenidSessionKey = "wxOpenid_SessionKey_";
    private static final String WxSessionKeyOpenid = "wxSessionKey_Openid_";

    @Autowired
    private IMerchantRepository merchantRepository;
    @Autowired
    private IMerchantAccountRepository repository;
    @Autowired
    private IShopRepository shopRepository;
    @Autowired
    private ISMSService smsService;

    @Autowired
    private IAliSMSService aliSMSService;
    
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Boolean isValidAccount(String id) {
        return repository.getById(id) != null;
    }

    @Override
    public AuthDTO getJwtUserById(String userId) {
        MerchantAccount user = repository.getById(userId);
        return merchantUserToAuthDTO(user);
    }

    @Override
    public AuthDTO findByUserName(String username) {
        MerchantAccount user = repository.getOne(new QueryWrapper<MerchantAccount>().eq("user_name", username));
        return merchantUserToAuthDTO(user);
    }

    @Override
    public AuthDTO findByWxOpenid(String openid, String sessionKey) {
        //数据库中并无该openid，缓存sessionKey，登录需要调用下一个login接口
        String oldSessionKey = (String)redisUtil.get(WxOpenidSessionKey + openid);
        redisUtil.set(WxOpenidSessionKey + openid, sessionKey, 60 * 60 * 24);
        //用户获取手机时使用
        log.info("oldSessionKey:"+oldSessionKey+";newSessionKey:"+sessionKey);
        if (StringUtils.isNotBlank(oldSessionKey) && !oldSessionKey.equals(sessionKey)) {
            redisUtil.del(WxSessionKeyOpenid + oldSessionKey);
        }
        redisUtil.set(WxSessionKeyOpenid + sessionKey, openid);

        MerchantAccount user = repository.getOne(new QueryWrapper<MerchantAccount>().eq("wx_openid", openid));
        return merchantUserToAuthDTO(user);
    }

    @Override
    public AuthDTO login(String openid, String username, String password) {
        MerchantAccount user = repository.getOne(new QueryWrapper<MerchantAccount>().eq("user_name", username));
        if (user != null && PwdUtil.matches(password, user.getUserPwd())) {
            //更新openid
            user.setWxOpenid(openid);
            repository.updateById(user);
            //返回登录所需信息
            return merchantUserToAuthDTO(user);
        }
        return null;
    }

    @Override
    public PCMerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto) {
        Shop shop =  shopRepository.getById(shopId);
        if(null == shop){
            throw new BusinessException("店铺不在存");
        }
        if(!dto.getJwtMerchantId().equals(shop.getMerchantId())){
            throw new BusinessException("店铺不在存");
        }
        //如果店铺商家相同，找到店铺的主帐号
        QueryWrapper<MerchantAccount> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("account_type", MerchantAccountTypeEnum.主帐号.getCode());
        queryWrapper.eq("shop_id",shop.getId());
        MerchantAccount merchantAccount = repository.getOne(queryWrapper);
        if(null == merchantAccount){
            throw new BusinessException("店铺切换失败,主帐号数据错误");
        }
        AuthDTO authDTO = this.merchantUserToAuthDTO(merchantAccount);
        PCMerchShopVO.ChangeShopVO changeShopVO = new PCMerchShopVO.ChangeShopVO();
        BeanUtils.copyProperties(shop,changeShopVO);
        changeShopVO.setToken(JwtUtil.createToken(new JwtUser(authDTO)));
        return changeShopVO;
    }

    private AuthDTO merchantUserToAuthDTO(MerchantAccount user) {
        if (user == null) {
            return null;
        }
        AuthDTO authDTO =  new AuthDTO();
        authDTO.setId(user.getId())
                .setType(this.userType(user))
                .setPassword(user.getUserPwd())
                .setUsername(user.getUserName())
                .setHeadImg(user.getHeadImg())
                .setState(user.getAccountState())
                .setShopId(user.getShopId())
                .setPhone(user.getPhone())
                .setMerchantId(user.getMerchantId());
        return authDTO;
    }
    private Integer userType(MerchantAccount user) {
        if(user.getTerminal() == null){
            throw new BusinessException("商家帐号有异常数据(terminal)");
        }
        if (user.getTerminal().equals(TerminalEnum.BBB.getCode())) {
            if(null == user.getAccountType()){
                throw new BusinessException("2b商家帐号类型数据错误[AccountType]");
            }
            if (user.getAccountType().equals(MerchantAccountTypeEnum.主帐号.getCode())) {
                return UserTypeEnum._2B商家主账号.getCode();
            } else {
                return UserTypeEnum._2B商家子账号.getCode();
            }
        } else {
            if(null == user.getAccountType()){
                throw new BusinessException("2c商家帐号类型数据错误[AccountType]");
            }
            if (user.getAccountType().equals(MerchantAccountTypeEnum.主帐号.getCode())) {
                return UserTypeEnum._2C商家主账号.getCode();
            } else {
                return UserTypeEnum._2C商家子账号.getCode();
            }
        }
    }

    @Override
    public void getPhoneValidCode(CommonPhoneLoginDTO.GetPhoneValidCode dto) {
        String validCode = null;
        try {
//                validCode = smsService.sendLoginSMSCode(dto.getPhone());
                
            validCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            
            Boolean flag = aliSMSService.sendRegisterSMS(dto.getPhone(), validCode);
        	if(!flag){
        		 throw new BusinessException("短信发送失败!");
        	}
            	
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("短信发送失败!" + (e.getMessage().contains("限流") ? "发送频率过高" : ""));
        }
        //验证码失效时间10分账
        log.info("运营平台跟据手机号码获取验证码登录-手机号码："+dto.getPhone()+"-验证码："+validCode);
        redisUtil.set(PhoneValidCodeGroup + dto.getPhone(), validCode, 10 * 60);
    }

    @Override
    public BbbUserVO.LoginVO login(CommonPhoneLoginDTO.Login dto) {
    	
    	/**
    	 * TODO yingjun

    	Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
        String validCode = code != null ? code + "" : "";
        if (!StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("验证码不匹配");
        }
            	 */
    	MerchantAccount user = repository.getOne(new QueryWrapper<MerchantAccount>().eq("phone", dto.getPhone()));
    	BbbUserVO.LoginVO loginVO= new BbbUserVO.LoginVO();
    	if (user != null) {
            //返回登录所需信息
    		AuthDTO authDTO = merchantUserToAuthDTO(user);
    		
    		String createToken = JwtUtil.createToken(new JwtUser(authDTO));
    		authDTO.setToken(createToken);
    		BeanCopyUtils.copyProperties(authDTO, loginVO);
    		loginVO.setAuthToken(createToken);
    		return loginVO;
        }
    	throw new BusinessException("跟据手机号码未找到帐户");
    }

    @Override
    public String loadSessionKeyByWxOpenid(String openid) {
        return (String) redisUtil.get(WxOpenidSessionKey + openid);
    }

    @Override
    public BbbUserVO.LoginVO merchantLoginByWxInnerPhone(CommonPhoneLoginDTO.WxUserPhone dto) {
        return null;
    }

    @Override
    public void logout(String phone, String openid) {
        //H5退出
        if (StringUtils.isNotBlank(phone)) {
            redisUtil.del(H5PhoneUser + phone);
        }

        //小程序退出
        if (StringUtils.isNotBlank(openid)) {
            redisUtil.del(WxOpenidUser + openid);
        }
    }

    @Override
    public String regMerchantAccount(H5MerchMerchantAccountDTO.RegDTO eto) {
        if(!eto.getUserPwd().equals(eto.getUserPwdCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        QueryWrapper<MerchantAccount> merchantAccountQueryWrapper = MybatisPlusUtil.query();
        merchantAccountQueryWrapper.eq("user_name",eto.getUserName());
        merchantAccountQueryWrapper.or();
        merchantAccountQueryWrapper.eq("phone",eto.getPhone());
        List<MerchantAccount> merchantAccountList =  repository.list(merchantAccountQueryWrapper);
        if(ObjectUtils.isNotEmpty(merchantAccountList)){
            for(MerchantAccount merchantAccount:merchantAccountList){
                if(merchantAccount.getUserName().equals(eto.getUserName())){
                    throw new BusinessException("用户名已注册");
                }
                if(merchantAccount.getPhone().equals(eto.getPhone())){
                    throw new BusinessException("手机号已注册");
                }
            }
        }
        //注册成功生成主帐号(所有的入驻信息是关联在这个主帐号下),平台审核的时候为帐号分配商家,审核开通店铺的时候创建详细的商品分类数据,和企业字点
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanUtils.copyProperties(eto,merchantAccount);
        merchantAccount.setAccountType(MerchantAccountTypeEnum.主帐号.getCode());
        merchantAccount.setTerminal(TerminalEnum.BBB.getCode());
        merchantAccount.setAccountState(MerchantAccountStateEnum.启用.getCode());
        merchantAccount.setUserPwd(PwdUtil.encode(merchantAccount.getUserPwd()));
        repository.save(merchantAccount);

        return this.createMerchantAccountJwtToken(merchantAccount);
    }
    /** 商家帐号token **/
    private String createMerchantAccountJwtToken(MerchantAccount merchantAccount){
        AuthDTO authDTO = new AuthDTO().setId(merchantAccount.getId())
                .setType(UserTypeEnum._2B商家主账号.getCode())
                .setPassword(merchantAccount.getUserPwd())
                .setUsername(merchantAccount.getUserName())
                .setHeadImg(merchantAccount.getHeadImg())
                .setState(merchantAccount.getAccountState())
                .setShopId(merchantAccount.getShopId())
                .setPhone(merchantAccount.getPhone())
                .setMerchantId(merchantAccount.getMerchantId());
        return JwtUtil.createToken(new JwtUser(authDTO));

    }

}
