package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-06
*/
public abstract class PCMerchMerchantUserTypeRatioDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantUserTypeRatioDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品skuId")
        private String skuId;
    }

    @Data
    @ApiModel("PCMerchMerchantUserTypeRatioDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }


}
