package com.gs.lshly.biz.support.merchant.mapper.views;

import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import lombok.Data;

@Data
public class MerchantApplyView extends MerchantApply {

    /**
     * 管理员帐号
     */
    private String merchantAccount;


}
