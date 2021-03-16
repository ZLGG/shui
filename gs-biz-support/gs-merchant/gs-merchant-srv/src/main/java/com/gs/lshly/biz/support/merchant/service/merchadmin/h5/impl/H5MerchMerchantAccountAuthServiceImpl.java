package com.gs.lshly.biz.support.merchant.service.merchadmin.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountTypeEnum;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.merchadmin.h5.IH5MerchMerchantAccountAuthService;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-1-27
*/
@Component
public class H5MerchMerchantAccountAuthServiceImpl implements IH5MerchMerchantAccountAuthService {

    @Autowired
    private IMerchantRepository merchantRepository;
    @Autowired
    private IMerchantAccountRepository repository;
    @Autowired
    private IShopRepository shopRepository;


    @Override
    public H5MerchShopVO.ChangeShopVO changeShop(String shopId, BaseDTO dto) {
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
        H5MerchShopVO.ChangeShopVO changeShopVO = new H5MerchShopVO.ChangeShopVO();
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
}
