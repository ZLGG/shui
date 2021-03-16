package com.gs.lshly.biz.support.merchant.mapper.views;

import com.gs.lshly.biz.support.merchant.entity.Shop;
import lombok.Data;

@Data
public class MerchantShopView extends Shop {

    /**
     * 超级管理员帐号
     */
    private String supperAccount;

    /**
     * 商家名称
     */
    private String merchantName;


}
