package com.gs.lshly.biz.support.merchant.repository.impl;

import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAccountMapper;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家帐号 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Service
public class MerchantAccountRepositoryImpl extends ServiceImpl<MerchantAccountMapper, MerchantAccount> implements IMerchantAccountRepository {

}
