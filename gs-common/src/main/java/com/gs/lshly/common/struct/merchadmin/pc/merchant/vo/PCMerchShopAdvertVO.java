package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopAdvertVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopAdvertVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("跳转链接")
        private String jumpUrl;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("描述")
        private String text;

        @ApiModelProperty("广告类型[10=通栏广告 20=单张广告]")
        private Integer advertType;
    }

    @Data
    @ApiModel("PCMerchShopAdvertVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("跳转链接")
        private String jumpUrl;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("描述")
        private String text;

    }
}
