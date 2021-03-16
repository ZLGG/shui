package com.gs.lshly.biz.support.merchant.mapper.views;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import lombok.Data;

@Data
public class AccountShopView extends MerchantAccount {

    /**
     * 店铺类型
     */
    private Integer shopType;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

}
