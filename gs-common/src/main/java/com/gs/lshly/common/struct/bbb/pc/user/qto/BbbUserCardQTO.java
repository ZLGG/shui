package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbUserCardQTO implements Serializable {

    @Data
    @ApiModel("BbcUserCardQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("优惠卷ID")
        private String cardId;

        @ApiModelProperty("活动卷类型[10=平台购物卷 20=商家优惠卷]")
        private Integer cardType;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }
}
