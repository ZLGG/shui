package com.gs.lshly.common.struct.bbb.pc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-04
*/
public abstract class BbbMarketMerchantActivityDTO implements Serializable {

    @Data
    @ApiModel("BbbMarketMerchantActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


    }

    @Data
    @ApiModel("BbbMarketMerchantActivityDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
    }
    @Data
    @ApiModel("BbbMarketMerchantActivityDTO.MerchantIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MerchantIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商家id")
        private String id;
    }
    @Data
    @ApiModel("BbbMarketMerchantActivityDTO.CardIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardIdDTO extends BaseDTO {

        @ApiModelProperty(value = "优惠卷Id")
        private String id;
    }



}
