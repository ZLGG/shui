package com.gs.lshly.common.struct.bbb.h5.merchant.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-11-05
*/
public abstract class BbbH5ShopFloorQTO implements Serializable {

    @Data
    @ApiModel("BbcShopFloorQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private Integer shopId;

    }
}
