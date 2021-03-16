package com.gs.lshly.common.struct.bbb.h5.trade.dto;

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
public abstract class BbbH5MarketMerchantActivityDTO implements Serializable {

    @Data
    @ApiModel("BbbH5MarketMerchantActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


    }

    @Data
    @ApiModel("BbbH5MarketMerchantActivityDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
    }
    @Data
    @ApiModel("BbbH5MarketMerchantActivityDTO.MerchantIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MerchantIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商家id")
        private String id;
    }
    @Data
    @ApiModel("BbbH5MarketMerchantActivityDTO.CardIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardIdDTO extends BaseDTO {

        @ApiModelProperty(value = "优惠卷Id")
        private String id;
    }



}
