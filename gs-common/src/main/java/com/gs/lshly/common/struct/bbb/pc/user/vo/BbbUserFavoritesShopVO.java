package com.gs.lshly.common.struct.bbb.pc.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class BbbUserFavoritesShopVO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesShopVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("收藏ID")
        private String id;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

    }

    @Data
    @ApiModel("BbcUserFavoritesShopVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
