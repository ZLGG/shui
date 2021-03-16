package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-11-04
*/
public abstract class PCMerchShopFloorGoodsQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopFloorGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("楼层ID")
        private String shopFloorId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("排序")
        private Integer idx;
    }
}
