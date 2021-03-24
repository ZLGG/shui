package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.entity.UserSignIn;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.enums.UserCardStatusEnum;
import com.gs.lshly.biz.support.user.mapper.UserCardMapper;
import com.gs.lshly.biz.support.user.mapper.UserIntegralMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.repository.IUserSignInRepository;
import com.gs.lshly.biz.support.user.repository.IUserUser2bApplyRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserService;
import com.gs.lshly.common.enums.ApplyStateEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.enums.UserSexEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReportVO;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.sms.ISMSService;
import com.gs.lshly.middleware.sms.utils.CcsMsgCrypt;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbMerchantAccountRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IPCBbbMerchantUserTypeRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketMerchantCardUsersRpc;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsIntegralRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsReportRpc;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-06
*/
@Component
@Slf4j
public class BbbUserServiceImpl implements IBbbUserService {

    @Autowired
    private IUserUser2bApplyRepository userUser2bApplyRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISMSService smsService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserPrivateUserRepository userPrivateUserRepository;
    @Autowired
    private IUserIntegralRepository userIntegralRepository;
    @Autowired
    private UserIntegralMapper userIntegralMapper;
    @Autowired
    private UserCardMapper userCardMapper;
    @Autowired
    private IUserSignInRepository iUserSignInRepository;
    @DubboReference
    private IBbbMerchantAccountRpc bbbMerchantAccountRpc;
    @DubboReference
    private ISettingsReportRpc iSettingsReportRpc;
    @DubboReference
    private ISettingsIntegralRpc iSettingsIntegralRpc;
    @DubboReference
    private ILegalDictRpc legalDictRpc;
    @DubboReference
    private IPCBbbMerchantUserTypeRpc bbbMerchantUserTypeRpc;
    @DubboReference
    private IPCBbbMarketMerchantCardUsersRpc ipcBbbMarketMerchantCardUsersRpc;


    private static final String PhoneValidCodeGroup = "PhoneValidCode_";
    private static final String H5PhoneUser = "h5Phone_User_";
    private static final String WxOpenidUser = "wxOpenid_User_";
    private static final String WxOpenidSessionKey = "wxOpenid_SessionKey_";
    private static final String WxSessionKeyOpenid = "wxSessionKey_Openid_";

    @Override
    public void editorPassword(BbbUserDTO.EditorPasswordETO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        if(null == user){
            throw new BusinessException("用户不存在");
        }
        if(!dto.getNewPassword().equals(dto.getNewPasswordCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        if(!PwdUtil.encoder().matches(dto.getPassword(),user.getUserPwd())){
            throw new BusinessException("登录密码错误");
        }
        user.setUserPwd(PwdUtil.encode(dto.getNewPassword()));
        userRepository.updateById(user);
    }

    @Override
    public void editorPayPassword(BbbUserDTO.EditorPayPasswordDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        if(null == user){
            throw new BusinessException("用户不存在");
        }
        if(!PwdUtil.encoder().matches(dto.getPassword(),user.getUserPwd())){
            throw new BusinessException("登录密码错误");
        }
        user.setPayPwd(PwdUtil.encode(dto.getPayPassword()));
        userRepository.updateById(user);
    }

    @Override
    public void editorUserName(BbbUserDTO.EditorUserNameDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        if(null == user){
            throw new BusinessException("用户不存在");
        }
        user.setUserName(dto.getUserName());
        userRepository.updateById(user);
    }

    @Override
    public void bindEmail(BbbUserDTO.BindEmailDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        if(null == user){
            throw new BusinessException("用户不存在");
        }
        checkEmail(user,dto.getEmail());
    }

    private  void checkEmail(User user,String email) {
        if (email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
            user.setEmail(email);
            userRepository.updateById(user);
        } else {
            throw new BootstrapMethodError("邮箱格式错误, 请输出正确邮箱地址");
        }
    }

    @Override
    public void bindPhone(BbbUserDTO.BindPhoneDTO dto) {
        if(StringUtils.isBlank(dto.getJwtUserId())){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        if(null == user){
            throw new BusinessException("用户不存在");
        }
        user.setPhone(dto.getPhone());
        userRepository.updateById(user);
    }

    @Override
    public BbbUserVO.CheckPasswordVO checkPassword(BbbUserDTO.CheckPasswordETO dto) {
        BbbUserVO.CheckPasswordVO checkPasswordVO = new BbbUserVO.CheckPasswordVO();
        if(StringUtils.isBlank(dto.getJwtUserId())){
            checkPasswordVO.setCheckBool(TrueFalseEnum.否.getCode());
            return checkPasswordVO;
        }
        User user = userRepository.getById(dto.getJwtUserId());
        if(null == user){
            checkPasswordVO.setCheckBool(TrueFalseEnum.否.getCode());
            return checkPasswordVO;
        }
        if(user.getUserPwd().equals( PwdUtil.encode(dto.getPassword()))){
            checkPasswordVO.setCheckBool(TrueFalseEnum.是.getCode());
        }
        return checkPasswordVO;
    }

    @Override
    public BbbUserVO.UserTypeVO userType(BaseDTO dto) {
        User user = userRepository.getById(dto.getJwtUserId());
        if(null == user){
            return null;
        }
        BbbUserVO.UserTypeVO userTypeVO  = new BbbUserVO.UserTypeVO();
        userTypeVO.setId(user.getId());
        userTypeVO.setUserType(user.getType());
        userTypeVO.setMemberType(user.getMemberType());
        userTypeVO.setIsInUser(user.getIsInUser());
        return userTypeVO;
    }

    @Override
    public BbbUserVO.UserIntegralVO integral(BaseDTO dto) {
        QueryWrapper<UserIntegral> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",dto.getJwtUserId());
        queryWrapper.groupBy("user_id");
        UserIntegralView sumCountView = userIntegralMapper.sumCount(queryWrapper);
        UserIntegralView sumCountPassView =  userIntegralMapper.sumCountPass(10,queryWrapper);
        BbbUserVO.UserIntegralVO userIntegralVO  = new BbbUserVO.UserIntegralVO();
        if(null != sumCountView){
            userIntegralVO.setOkIntegral(sumCountView.getQuantity());
        }
        if(null != sumCountPassView){
            userIntegralVO.setJpassIntegral(sumCountPassView.getQuantity());
        }
        return userIntegralVO;
    }

    @Override
    public PageData<BbbUserVO.UserIntegralRecordVO> integralLog(BbbUserQTO.IntegralLogQTO qto) {
        QueryWrapper<UserIntegral> userIntegralQueryWrapper = MybatisPlusUtil.query();
        IPage<UserIntegral> pager = MybatisPlusUtil.pager(qto);
        userIntegralQueryWrapper.eq("user_id",qto.getJwtUserId());
        userIntegralRepository.page(pager, userIntegralQueryWrapper);
        if (ObjectUtils.isNotEmpty(pager)){
            return MybatisPlusUtil.toPageData(qto,BbbUserVO.UserIntegralRecordVO.class,pager);
        }
        return new PageData<>();
    }

    @Override
    public BbbUserVO.DetailVO getUserInfo(BbbUserQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(qto.getJwtUserId());
        BbbUserVO.DetailVO detailVO = new BbbUserVO.DetailVO();
        BeanUtils.copyProperties(user,detailVO);
        //封装优惠卷数量
        packCountCard(qto,detailVO);
        //获取用户的积分
        BbbUserVO.UserIntegralVO  integralVO = integral(qto);
        if (ObjectUtils.isNotEmpty(integralVO)){
            detailVO.setIntegral(integralVO.getOkIntegral());
        }
        return detailVO;
    }


    private void packCountCard(BbbUserQTO.QTO qto,BbbUserVO.DetailVO detailVO){
        QueryWrapper<PCBbbMarketMerchantCardUsersQTO.QTO> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("td.user_id",qto.getJwtUserId())
                .eq("td.user_id",UserCardStatusEnum.未使用);
        detailVO.setCountCard(userCardMapper.countCard(queryWrapper));
    }



    @Override
    public BbbUserVO.EditorUserInfoVO getEditorUserInfo(BaseDTO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        BbbUserVO.EditorUserInfoVO editorUserInfoVO = new BbbUserVO.EditorUserInfoVO();
        BeanUtils.copyProperties(user,editorUserInfoVO);
        return editorUserInfoVO;
    }

    @Override
    public void editorUserInfo(BbbUserDTO.UserInfoETO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  userRepository.getById(dto.getJwtUserId());
        BeanUtils.copyProperties(dto,user);
        userRepository.updateById(user);
    }

    @Override
    public BbbUserVO.InnerUserInfoVO innerGetUserInfo(String userId) {
        User user =  userRepository.getById(userId);
        if(null == user){
            return null;
        }
        BbbUserVO.InnerUserInfoVO detailVO = new BbbUserVO.InnerUserInfoVO();
        BeanUtils.copyProperties(user,detailVO);
        return detailVO;
    }

    @Override
    public BbbUserVO.PrivateUserInfoVO oneShopPrivateUserInfo(String shopId, String userId) {
        if(StringUtils.isBlank(shopId) || StringUtils.isBlank(userId)){
            return null;
        }
        User user = userRepository.getById(userId);
        if(null == user){
            return null;
        }
        QueryWrapper<UserPrivateUser> userPrivateUserQueryWrapper = MybatisPlusUtil.query();
        userPrivateUserQueryWrapper.eq("shop_id",shopId);
        userPrivateUserQueryWrapper.eq("user_id",userId);
        userPrivateUserQueryWrapper.eq("state", ApplyStateEnum.通过.getCode());
        userPrivateUserQueryWrapper.eq("bind_state", PrivateUserBindStateEnum.关联.getCode());
        UserPrivateUser userPrivateUser = userPrivateUserRepository.getOne(userPrivateUserQueryWrapper);
        if(null == userPrivateUser){
            return null;
        }
        BbbUserVO.PrivateUserInfoVO privateUserInfoVO = new BbbUserVO.PrivateUserInfoVO();
        BeanUtils.copyProperties(userPrivateUser,privateUserInfoVO);
        privateUserInfoVO.setUserId(user.getId());
        privateUserInfoVO.setUserName(user.getUserName());
        privateUserInfoVO.setUserType(user.getType());

        PCBbbMerchantUserTypeVO.DetailsVO userTypeVo =  bbbMerchantUserTypeRpc.details(userPrivateUser.getUserTypeId());
        if(null != userTypeVo){
           privateUserInfoVO.setDetailsVO(userTypeVo);
        }
        return privateUserInfoVO;
    }

    @Override
    @Transactional
    public void signInIntegralLog(BaseDTO dto) {
        //在系统设置里面查询是否开启了签到送积分
        SettingsReportVO.DetailVO detailVO = iSettingsReportRpc.detailSettingsReport(dto);
        if (ObjectUtils.isEmpty(detailVO)){
            throw new BusinessException("系统设置不能为空");
        }
        if (ObjectUtils.isNotEmpty(detailVO.getIsOpenState())) {
            if (detailVO.getIsOpenState() != 10) {
                throw new BusinessException("不能签到");
            }
        }
        //通过userID查询当天是否已经签到了，，，
        QueryWrapper<BaseDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("user_id",dto.getJwtUserId()));
        UserSignIn userSignIn=iUserSignInRepository.seletcNowDay(query);
        if (ObjectUtils.isNotEmpty(userSignIn)){
            throw new BusinessException("已经签到过了");
        }
        UserSignIn userSignIn1 = new UserSignIn();
        userSignIn1.setUserId(  dto.getJwtUserId());
        iUserSignInRepository.save(userSignIn1);
        if (detailVO.getIsReportInteg()==10){
            //开启签到领积分
            UserIntegral userIntegral = new UserIntegral();
            userIntegral.setQuantity(detailVO.getReportInteg());
            userIntegral.setFromType(30);//积分来源[10=平台添加 20=订单 30=签到]
            userIntegral.setUserId(dto.getJwtUserId());
            userIntegral.setFromId(userSignIn1.getId());
            //获取积分获取月份
            SettingsIntegralVO.DetailVO detailVO1 = iSettingsIntegralRpc.detailSettingsIntegral(dto);
            LocalDateTime now = LocalDateTime.now();//获取当前月份
            if (now.getMonthValue()<detailVO1.getMonthToExpire()){
                userIntegral.setEndDate(LocalDateTime.of(now.getYear(),detailVO1.getMonthToExpire(),1,0,0,0));
            }else {
                userIntegral.setEndDate(LocalDateTime.of(now.getYear()+1,detailVO1.getMonthToExpire(),1,0,0,0));
            }
            userIntegralMapper.insert(userIntegral);
        }

    }

    @Override
    public BbbUserVO.UserIntegralStatusVO signInIntegralLogState(BaseDTO dto) {
        QueryWrapper<BaseDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("user_id",dto.getJwtUserId()));
        UserSignIn userSignIn=iUserSignInRepository.seletcNowDay(query);
        BbbUserVO.UserIntegralStatusVO userIntegralStatusVO = new BbbUserVO.UserIntegralStatusVO();
        if (ObjectUtils.isNotEmpty(userSignIn)){
            userIntegralStatusVO.setStatus(20);
        }else {
            userIntegralStatusVO.setStatus(10);
        }
        return userIntegralStatusVO;
    }

    @Override
    public BbbUserVO.InnerUserInfoVO innerUserVo(BaseDTO dto) {
        QueryWrapper<User> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id",dto.getJwtUserId());
        User user = userRepository.getOne(wrapper);
        if (ObjectUtils.isNotEmpty(user)){
            BbbUserVO.InnerUserInfoVO userInfoVO = new BbbUserVO.InnerUserInfoVO();
            BeanUtils.copyProperties(user,userInfoVO);
            return userInfoVO;
        }
        return null;
    }

	@Override
	public String customerAuthorize(BaseDTO dto) {
		if (null == dto.getJwtUserId()) {
			throw new BusinessException("没有登录");
		}
		User user = userRepository.getById(dto.getJwtUserId());
		
//		String src = "{\"phoneNumber\":\"18267495869\",\"uname\":\"summer\",
		//\"gender\":\"男\",\"headImg\":\"\",\"tenantId\":\"_1LWHJ9M\",\"userId\":\"bbq1221\"}";
        JSONObject json = new JSONObject();
        json.put("phoneNumber", user.getPhone());
        if(StringUtils.isEmpty(user.getNick())){
        	json.put("uname", user.getPhone());
        }else{
        	json.put("uname", user.getNick());
        }
        //性别[10=男  20=女 30=保密]
        json.put("gender",UserSexEnum.getEnum(user.getSex()));
        json.put("headImg", user.getSex());
        json.put("tenantId", TerminalEnum.BBB.getCode());
        json.put("userId", user.getId());
        //return "x5BjTkg7Ieky+MTqaRJ/Qk8AhLqTTLjaMft+09cX36OWvPMHNUNIV5+jl0siUX5yDx8ZNfJD+o7kt0BudAKIEledsHrjoMYn2cW8VJKFFGHaz0OINShG/vxccmGiB8/zvqF4KiPFZOcfak1CUIS33e3UfPwpaZ1hMKXAZ4c4ejc=";
        return CcsMsgCrypt.encrypt(json.toJSONString(),"aHV6aG91SHp6c3RIenpzdA==");
	}


}
