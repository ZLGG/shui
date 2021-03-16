package com.gs.lshly.biz.support.merchant.repository;

import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商家入驻申请 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-14
 */
public interface IMerchantApplyRepository extends IService<MerchantApply> {

    boolean checkIdExist(String id);

    boolean checkIdListExist(List<String> idList);

}
