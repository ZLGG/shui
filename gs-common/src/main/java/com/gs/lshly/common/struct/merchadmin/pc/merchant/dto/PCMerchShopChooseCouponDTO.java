package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author 陈奇
* @since 2020-10-23
*/
public abstract class PCMerchShopChooseCouponDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopChooseCouponDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("优惠券id")
        private String couponId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("PCMerchShopChooseCouponDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
