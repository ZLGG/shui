package com.gs.lshly.common.struct.common;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class CommonStockQTO implements Serializable {

    @Data
    @ApiModel("BbcStockQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("SKU")
        private String skuId;

        @ApiModelProperty("SKU-值")
        private String skuVal;

        @ApiModelProperty("库存数量")
        private Integer quantity;
    }
}
