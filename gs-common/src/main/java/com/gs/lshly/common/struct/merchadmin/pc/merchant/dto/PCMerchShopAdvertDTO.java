package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopAdvertDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopAdvertDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("跳转链接")
        private String jumpUrl;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("描述")
        private String text;

        @ApiModelProperty(value = "广告类型[10=通栏广告 20=单张广告]",hidden = true)
        private Integer advertType;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchShopAdvertDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
