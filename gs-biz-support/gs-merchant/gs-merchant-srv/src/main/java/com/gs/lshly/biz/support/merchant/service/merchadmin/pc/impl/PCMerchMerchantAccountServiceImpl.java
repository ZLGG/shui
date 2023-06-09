package com.gs.lshly.biz.support.merchant.service.merchadmin.pc.impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.gs.lshly.middleware.sms.IContactSMSService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccountRole;
import com.gs.lshly.biz.support.merchant.entity.MerchantRoleDict;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountStateEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountTypeEnum;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAccountMapper;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAccountRoleMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountShopView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRoleRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRoleDictRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchMerchantAccountService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantAccountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO.AccountDetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.CheckEmailUtils;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mail.Email;
import com.gs.lshly.middleware.mail.IMailService;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;

import lombok.extern.slf4j.Slf4j;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-23
*/
@Component
@Slf4j
public class PCMerchMerchantAccountServiceImpl implements IPCMerchMerchantAccountService {

    private static final String PhoneValidCodeGroup = "PhoneValidCode_";
    private static final String EmailValidCodeGroup = "EmailValidCode_";

    @Autowired
    private IMerchantAccountRepository repository;

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Autowired
    private MerchantAccountRoleMapper merchantAccountRoleMapper;

    @Autowired
    private IMerchantRoleDictRepository merchantRoleDictRepository;

    @Autowired
    private IMerchantAccountRoleRepository merchantAccountRoleRepository;

//    @Autowired
//    private ISMSService smsService;

    @Autowired
    private IContactSMSService iContactSMSService;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IMailService iMailService;

    @Override
    public boolean checkUserName(PCMerchMerchantAccountDTO.CheckUserNameDTO dto) {
        if(StringUtils.isBlank(dto.getUserName())){
            throw new BusinessException("用户名,不能为空");
        }
        QueryWrapper<MerchantAccount> userQueryWrapper = MybatisPlusUtil.query();
        userQueryWrapper.eq("user_name",dto.getUserName());
        MerchantAccount merchantAccount = repository.getOne(userQueryWrapper);
        if(null != merchantAccount){
            throw new BusinessException("用户名已存在");
        }
        return true;
    }


    @Override
    public String regMerchantAccount(PCMerchMerchantAccountDTO.RegDTO eto) {
        /**
         * 校验验证码

        Object code = redisUtil.get(PhoneValidCodeGroup + eto.getPhone());
        String validCode = code != null ? code + "" : "";
        if (!StringUtils.equals(validCode, eto.getVcode())) {
            throw new BusinessException("验证码不匹配");
        }
                 */
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


    @Override
    public PageData<PCMerchMerchantAccountVO.ListVO> pageData(PCMerchMerchantAccountQTO.QTO qto) {

        IPage<AccountShopView> page = MybatisPlusUtil.pager(qto);
        QueryWrapper<AccountShopView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("act.account_type",MerchantAccountTypeEnum.子帐号.getCode());
        queryWrapper.eq("act.shop_id",qto.getJwtShopId());
        if(StringUtils.isNotBlank(qto.getName())){
            queryWrapper.eq("act.name",qto.getName());
        }
        if(null!=qto.getType()&&0!=qto.getType()){
            queryWrapper.eq("act.type",qto.getType());
        }
        if(StringUtils.isNotBlank(qto.getMerAddress())){
            queryWrapper.like("CONCAT( act.province,'', act.city )",qto.getMerAddress());
        }
        merchantAccountMapper.mapperPage(page,queryWrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMerchantAccountVO.ListVO.class, page);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addMerchantAccount(PCMerchMerchantAccountDTO.AddDTO eto) {
        if(!eto.getUserPwdCfm().equals(eto.getUserPwd())){
            throw new BusinessException("确认输入密码失败");
        }
        if(ObjectUtils.isEmpty(eto.getRoleId())){
            throw new BusinessException("帐号没有分配角色");
        }
        QueryWrapper<MerchantAccount> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_name",eto.getUserName());
        int count = repository.count(queryWrapper);
        if(count > 0 ){
            throw new BusinessException("帐号已存在");
        }
        MerchantAccount supperMerchantAccount = repository.getById(eto.getJwtUserId());
        if(null == supperMerchantAccount){
            throw new BusinessException("管理商家帐号不存在");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        merchantAccount.setAccountType(MerchantAccountTypeEnum.子帐号.getCode());
        merchantAccount.setAccountState(MerchantAccountStateEnum.启用.getCode());
        merchantAccount.setTerminal(supperMerchantAccount.getTerminal());
        merchantAccount.setUserPwd(PwdUtil.encode(merchantAccount.getUserPwd()));
        merchantAccount.setMainAccountId(supperMerchantAccount.getId());
        merchantAccount.setShopId(supperMerchantAccount.getShopId());
        merchantAccount.setMerchantId(supperMerchantAccount.getMerchantId());
        repository.save(merchantAccount);
        //存帐号角色关系
        MerchantAccountRole merchantAccountRole = new MerchantAccountRole();
        merchantAccountRole.setAccountId(merchantAccount.getId());
        merchantAccountRole.setRoleId(eto.getRoleId());
        merchantAccountRoleRepository.save(merchantAccountRole);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto) {
        String shopId =dto.getJwtShopId();
        //判断商户下是否存在普通商品和sku商品
        int goods = merchantAccountMapper.getGoodsCount(shopId!=null?shopId:"-1");
        int skuGoods = merchantAccountMapper.getSkuGoodsCount(shopId!=null?shopId:"-1");
        if((goods>0)||(skuGoods>0)){
            throw new BusinessException("删除失败,该商户下存在商品！");
        }
        QueryWrapper<MerchantAccount> removeQueryWrapper = MybatisPlusUtil.query();
        removeQueryWrapper.eq("id",dto.getId());
        removeQueryWrapper.eq("account_type",MerchantAccountTypeEnum.子帐号.getCode());
        removeQueryWrapper.eq("shop_id",shopId);
        boolean bool =  repository.remove(removeQueryWrapper);
        if(bool == false){
            throw new BusinessException("删除失败");
        }
        QueryWrapper<MerchantAccountRole> deleteQueryWrapper = MybatisPlusUtil.query();
        deleteQueryWrapper.eq("account_id",dto.getId());
        merchantAccountRoleMapper.delete(deleteQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editMerchantAccount(PCMerchMerchantAccountDTO.ETO eto) {
        if(ObjectUtils.isEmpty(eto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(ObjectUtils.isEmpty(eto.getRoleId())){
            throw new BusinessException("角色ID不能为空");
        }
        QueryWrapper<MerchantAccount> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_name",eto.getUserName()).eq("id", eto.getId());
        int count = repository.count(queryWrapper);
        if(count > 0 ){
            throw new BusinessException("帐号已存在");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        UpdateWrapper<MerchantAccount> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",eto.getId());
        updateWrapper.eq("account_type",MerchantAccountTypeEnum.子帐号.getCode());
        updateWrapper.eq("shop_id",eto.getJwtShopId());
        boolean bool = repository.update(merchantAccount,updateWrapper);
        if(bool == true){
            //删除帐号角色
            QueryWrapper<MerchantAccountRole> deleteQueryWrapper = MybatisPlusUtil.query();
            deleteQueryWrapper.eq("account_id",eto.getId());
            merchantAccountRoleMapper.delete(deleteQueryWrapper);
            //存帐号角色关系
            MerchantAccountRole merchantAccountRole = new MerchantAccountRole();
            merchantAccountRole.setAccountId(merchantAccount.getId());
            merchantAccountRole.setRoleId(eto.getRoleId());
            merchantAccountRoleRepository.save(merchantAccountRole);
        }
    }

    @Override
    public void updatePassworld(PCMerchMerchantAccountDTO.PassworldETO eto) {
        if(ObjectUtils.isEmpty(eto.getId())){
            throw new BusinessException("ID不能为空");
        }
        if(!eto.getUserPwdCfm().equals(eto.getUserPwd())){
            throw new BusinessException("确认输入密码失败");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        UpdateWrapper<MerchantAccount> updatePassworldWrapper = new UpdateWrapper<>();
        updatePassworldWrapper.eq("id",eto.getId());
        updatePassworldWrapper.eq("account_type",MerchantAccountTypeEnum.子帐号.getCode());
        updatePassworldWrapper.eq("shop_id",eto.getJwtShopId());
        boolean bool = repository.update(merchantAccount,updatePassworldWrapper);
        if(bool == false){
            throw new BusinessException("修改密码失败");
        }
    }


    @Override
    public PCMerchMerchantAccountVO.DetailVO detailMerchantAccount(PCMerchMerchantAccountDTO.IdDTO dto) {
        QueryWrapper<AccountShopView> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("act.id",dto.getId());
        AccountShopView accountShopView = merchantAccountMapper.mapperOne(queryWrapper);
        if(null == accountShopView){
            throw new BusinessException("帐号不存在");
        }
        PCMerchMerchantAccountVO.DetailVO detailVo = new PCMerchMerchantAccountVO.DetailVO();
        BeanCopyUtils.copyProperties(accountShopView, detailVo);
        //查询所有的角色
        QueryWrapper<MerchantRoleDict> merchantRoleDictWrapper = MybatisPlusUtil.query();
        List<MerchantRoleDict> merchantRoleDictList = merchantRoleDictRepository.list(merchantRoleDictWrapper);
        detailVo.setRoleList(ListUtil.listCover(PCMerchMerchantAccountVO.RoleItemVO.class,merchantRoleDictList));
        return detailVo;
    }


    @Override
    public PCMerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto) {
        PCMerchMerchantAccountVO.CheckShopVO checkShopVO = new PCMerchMerchantAccountVO.CheckShopVO();
        checkShopVO.setShopId(dto.getJwtShopId());
        return checkShopVO;
    }

    @Override
    public PCMerchMerchantAccountVO.ListVO innnerDetailMerchantAccount(String phoneNum) {
        if (ObjectUtils.isEmpty(phoneNum)){
            return null;
        }
        QueryWrapper<MerchantAccount> query = MybatisPlusUtil.query();
        query.and(i->i.eq("phone",phoneNum));
        query.last("limit 0,1");
        MerchantAccount one = repository.getOne(query);
        if (ObjectUtils.isEmpty(one)){
            return null;
        }
        PCMerchMerchantAccountVO.ListVO listVO = new PCMerchMerchantAccountVO.ListVO();
        BeanUtils.copyProperties(one,listVO);
        return listVO;
    }

    @Override
    public void getPhoneValidCode(String phone) {
        //通过手机号查询商家账户（主账户）
        QueryWrapper<MerchantAccount> query = MybatisPlusUtil.query();
        query.and(i->i.eq("phone",phone));
        //是主账户
        query.and(i->i.eq("account_type",10));
        //已启用
        query.and(i->i.eq("account_state",10));
        MerchantAccount one =null;
        try {
            one=repository.getOne(query);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BusinessException("此手机重复注册了商家");
        }
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("没有查询到此手机对应的商家账户");
        }
//        String validCode=null;
        try {
//                validCode = smsService.sendLoginSMSCode(phone);
            iContactSMSService.userLogin(phone);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("短信发送失败!" + (e.getMessage().contains("限流") ? "发送频率过高" : ""));
        }
//        //验证码失效时间10分账
//        log.info("设置-手机号码："+phone+"-验证码："+validCode);
//        redisUtil.set(PhoneValidCodeGroup + phone, validCode, 10 * 60);
    }

    @Override
    public void getRegPhoneValidCode(String phone) {
        QueryWrapper<MerchantAccount> query = MybatisPlusUtil.query();
        query.and(i->i.eq("phone",phone));
        Integer count = repository.count(query);
        if (count != null && count > 0) {
            throw new BusinessException("该手机已注册");
        }
//        String validCode=null;
        try {
//            validCode = smsService.sendRegistrySMSCode(phone);
            iContactSMSService.merchantLogin(phone);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("短信发送失败!" + (e.getMessage().contains("限流") ? "发送频率过高" : ""));
        }
        //验证码失效时间10分账
//        log.info("设置-手机号码："+phone+"-验证码："+validCode);
//        redisUtil.set(PhoneValidCodeGroup + phone, validCode, 10 * 60);
    }

    @Override
    public String forgetPasswordByPhone(PCMerchMerchantAccountDTO.ForgetByPhoneETO dto) {
        //校验验证码
        Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
        String validCode = code != null ? code + "" : "";
        if (!StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("验证码不匹配");
        }
        if(null == dto.getPassword() || null == dto.getPasswordCfm() || null == dto.getValidCode() || null == dto.getPhone()){
            throw new BusinessException("注册信息错误");
        }
        if(!dto.getPassword().equals(dto.getPasswordCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        //通过手机号查询商家账户（主账户）
        QueryWrapper<MerchantAccount> query = MybatisPlusUtil.query();
        query.and(i->i.eq("phone",dto.getPhone()));
        //是主账户
        query.and(i->i.eq("account_type",10));
        //已启用
        query.and(i->i.eq("account_state",10));
        MerchantAccount one =null;
        try {
            one=repository.getOne(query);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BusinessException("此手机重复注册了商家");
        }
        one.setUserPwd(PwdUtil.encode(dto.getPassword()));
        repository.updateById(one);
        return one.getUserName();
    }

    @Override
    public String forgetByEmail(PCMerchMerchantAccountDTO.ForgetByEmailETO dto) {
        //校验验证码
        Object code = redisUtil.get( EmailValidCodeGroup+ dto.getEmail());
        String validCode = code != null ? code + "" : "";
        if (!StringUtils.equals(validCode, dto.getValidCode())) {
            throw new BusinessException("验证码不匹配");
        }
        if(null == dto.getPassword() || null == dto.getPasswordCfm() || null == dto.getValidCode() || null == dto.getEmail()){
            throw new BusinessException("注册信息错误");
        }
        if(!dto.getPassword().equals(dto.getPasswordCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        //通过手机号查询商家账户（主账户）
        QueryWrapper<MerchantAccount> query = MybatisPlusUtil.query();
        query.and(i->i.eq("email",dto.getEmail()));
        //是主账户
        query.and(i->i.eq("account_type",10));
        //已启用
        query.and(i->i.eq("account_state",10));
        MerchantAccount one =null;
        try {
            one=repository.getOne(query);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BusinessException("此邮箱重复注册了商家");
        }
        if (ObjectUtils.isEmpty(one)){
            throw new BusinessException("此邮箱下没有商家");
        }
        one.setUserPwd(PwdUtil.encode(dto.getPassword()));
        repository.updateById(one);
        return one.getUserName();
    }

    @Override
    public void getEmailNum(String email) {
        //校验邮箱
        if (!CheckEmailUtils.checkEmail(email)) {
            throw new BusinessException("输入的邮箱格式不正确");
        }
        String code=Integer.toString(randomCode());
        iMailService.send(new Email().setTo(email)
                .setSubject("好粮油商城账户邮箱绑定验证")
                .setContent(email+",你好!<br/>以下是你绑定邮箱的验证码：<br/>"+code+"<br/><a href='www.baidu.com'>baidu</a>").setHtml(true));
        //验证码失效时间10分账
        log.info("设置-邮箱号："+email+"-验证码："+code);
        redisUtil.set(EmailValidCodeGroup + email, code, 10 * 60);
    }
    /**
     * 生成随机验证码.
     *
     * @return 随机数
     */
    static int randomCode() {
        return 100_000 + ThreadLocalRandom.current().nextInt(1_000_000 - 100_000);
    }


	@Override
	public AccountDetailVO getByPhone(String phone) {
		QueryWrapper<MerchantAccount> userQueryWrapper = MybatisPlusUtil.query();
        userQueryWrapper.eq("phone",phone);
        MerchantAccount merchantAccount = repository.getOne(userQueryWrapper);
        AccountDetailVO detailVO = new AccountDetailVO();
        if(merchantAccount!=null){
        	BeanCopyUtils.copyProperties(merchantAccount, detailVO);
        }
		return detailVO;
	}

}
