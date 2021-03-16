package com.gs.lshly.biz.support.merchant.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCategory;

import java.util.List;

/**
 * <p>
 * 商家店铺商品类目申请 服务类
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
public interface IMerchantApplyCategoryRepository extends IService<MerchantApplyCategory> {

    boolean checkIdExist(String id);

    boolean checkIdListExist(List<String> idList);
}
