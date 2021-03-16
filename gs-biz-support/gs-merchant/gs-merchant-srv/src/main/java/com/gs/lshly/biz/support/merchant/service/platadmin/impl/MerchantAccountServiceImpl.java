package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.Merchant;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountQueryTypeEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountStateEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountTypeEnum;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAccountMapper;
import com.gs.lshly.biz.support.merchant.mapper.ShopMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountShopView;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantShopView;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantAccountService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAccountDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-08
*/
@Component
public class MerchantAccountServiceImpl implements IMerchantAccountService {

    @Autowired
    private IMerchantRepository merchantRepository;

    @Autowired
    private IMerchantAccountRepository repository;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private IShopRepository shopRepository;

    @Autowired
    private MerchantAccountMapper merchantAccountMapper;

    @Override
    public PageData<MerchantAccountVO.ListVO> pageData(MerchantAccountQTO.QTO qto) {
        QueryWrapper<AccountShopView> queryWrapper = MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getConditionValue())){
            if(MerchantAccountQueryTypeEnum.用户名.getCode().equals(qto.getConditionType())){
                queryWrapper.like("act.user_name",qto.getConditionValue());
            }
            else if(MerchantAccountQueryTypeEnum.手机号.getCode().equals(qto.getConditionType())){
                queryWrapper.like("act.phone",qto.getConditionValue());
            }
        }
        queryWrapper.eq("act.terminal",qto.getTerminal());
        queryWrapper.eq("act.account_type",MerchantAccountTypeEnum.主帐号.getCode());
        IPage<AccountShopView> page = MybatisPlusUtil.pager(qto);
        merchantAccountMapper.mapperPage(page,queryWrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantAccountVO.ListVO.class, page);
    }

    @Override
    public void addMerchantAccountForPlatForm(MerchantAccountDTO.PlatformAccountETO eto) {

        Shop shop = shopRepository.getById(eto.getShopId());
        if(null == shop){
            throw new BusinessException("店铺不在存");
        }
        if(StringUtils.isNotBlank(shop.getMainAccountId())){
            throw new BusinessException("店铺已有主帐号");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        BeanCopyUtils.copyProperties(eto, merchantAccount);
        merchantAccount.setUserPwd(PwdUtil.encode(eto.getUserPwd()));
        merchantAccount.setShopId(shop.getId());
        merchantAccount.setMerchantId(shop.getMerchantId());
        merchantAccount.setAccountType(MerchantAccountTypeEnum.主帐号.getCode());
        merchantAccount.setAccountState(MerchantAccountStateEnum.启用.getCode());
        merchantAccount.setTerminal(shop.getTerminal());
        repository.save(merchantAccount);
        shop.setMainAccountId(merchantAccount.getId());
        shopRepository.updateById(shop);
    }

    @Override
    public void editMerchantAccountPwd(MerchantAccountDTO.ModifyPwdETO eto) {
        if(ObjectUtils.isNull(eto.getId(),eto.getUserPwd(),eto.getUserPwdCfm())){
            throw new BusinessException("参数不能为Null");
        }
        if(!eto.getUserPwd().equals(eto.getUserPwdCfm())){
            throw new BusinessException("确认密码错误");
        }
        MerchantAccount merchantAccount = new MerchantAccount();
        merchantAccount.setId(eto.getId());
        merchantAccount.setUserPwd(PwdUtil.encode(eto.getUserPwd()));
        repository.updateById(merchantAccount);
    }


    @Override
    public MerchantAccountVO.MerchShopVO getMerchantShopByPhone(MerchantAccountQTO.PhoneQTO qto) {
        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        wrapper.eq("merch.main_account_name",qto.getPhone());
        MerchantShopView merchantShopView = shopMapper.getIdByMerchantPhone(wrapper);
        MerchantAccountVO.MerchShopVO merchShopVO = new MerchantAccountVO.MerchShopVO();
        BeanCopyUtils.copyProperties(merchShopVO,merchantShopView);
        return merchShopVO;
    }

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
}
