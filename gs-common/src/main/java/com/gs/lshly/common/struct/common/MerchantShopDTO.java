package com.gs.lshly.common.struct.common;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class MerchantShopDTO implements Serializable {


    @Data
    @ApiModel("MerchantShopDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID",position = 1)
        private String shopId;

        @ApiModelProperty(value = "商家ID",position = 2)
        private String merchantId;

    }


}
