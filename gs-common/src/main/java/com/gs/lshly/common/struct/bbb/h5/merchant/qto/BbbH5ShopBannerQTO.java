package com.gs.lshly.common.struct.bbb.h5.merchant.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class BbbH5ShopBannerQTO implements Serializable {

    @Data
    @ApiModel("BbcShopBannerQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private String shopId;

    }
}
