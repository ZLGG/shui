package com.gs.lshly.biz.support.merchant.repository;

import com.gs.lshly.biz.support.merchant.entity.MerchantAgreement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商家入驻协议 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-06
 */
public interface IMerchantAgreementRepository extends IService<MerchantAgreement> {


    boolean checkIdExist(String id);

}
