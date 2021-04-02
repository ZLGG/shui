package com.gs.lshly.common.struct.bbc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zdf
* @since 2020-12-04
*/
public abstract class BbcMarketMerchantActivityDTO implements Serializable {

    @Data
    @ApiModel("BbcMarketMerchantActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


    }

    @Data
    @ApiModel("BbcMarketMerchantActivityDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
    }
    @Data
    @ApiModel("BbcMarketMerchantActivityDTO.MerchantIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MerchantIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商家id")
        private String id;

        @ApiModelProperty(value = "商品id(商品详情页才传)")
        private String goodsId;
    }
    @Data
    @ApiModel("BbcMarketMerchantActivityDTO.CardIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardIdDTO extends BaseDTO {

        @ApiModelProperty(value = "优惠卷Id")
        private String id;
    }



}
