package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantAgreement;
import com.gs.lshly.biz.support.merchant.mapper.MerchantAgreementMapper;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAgreementRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家入驻协议 服务实现类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-06
 */
@Service
public class MerchantAgreementRepositoryImpl extends ServiceImpl<MerchantAgreementMapper, MerchantAgreement> implements IMerchantAgreementRepository {

    @Override
    public boolean checkIdExist(String id) {
        MerchantAgreement entity =  this.getById(id);
        if(ObjectUtils.isNull(entity)){
            return false;
        }
        return true;
    }
}
