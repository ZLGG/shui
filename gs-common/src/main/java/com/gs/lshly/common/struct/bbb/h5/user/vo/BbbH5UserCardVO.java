package com.gs.lshly.common.struct.bbb.h5.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbH5UserCardVO implements Serializable {

    @Data
    @ApiModel("BbcUserCardVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

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

    @Data
    @ApiModel("BbcUserCardVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
