package com.gs.lshly.common.struct.bbb.h5.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class BbbH5ShopBannerVO implements Serializable {

    @Data
    @ApiModel("BbcShopBannerVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("轮播图导航id")
        private String bannerNavigationId;


        @ApiModelProperty("图片地址")
        private String imageUrl;


        @ApiModelProperty("文字")
        private String text;


        @ApiModelProperty("跳转地址")
        private String jumpUrl;


        @ApiModelProperty("排序")
        private Integer idx;


        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("BbcShopBannerVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
