package com.gs.lshly.biz.support.merchant.mapper.views;

import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCategory;
import lombok.Data;

@Data
public class MerchantApplyCategoryView extends MerchantApplyCategory {

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺类型
     */
    private Integer shopType;

}
