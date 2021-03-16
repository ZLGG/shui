package com.gs.lshly.biz.support.merchant.repository.impl;

import com.gs.lshly.biz.support.merchant.entity.Merchant;
import com.gs.lshly.biz.support.merchant.mapper.MerchantMapper;
import com.gs.lshly.biz.support.merchant.repository.IMerchantRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Service
public class MerchantRepositoryImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantRepository {

}
