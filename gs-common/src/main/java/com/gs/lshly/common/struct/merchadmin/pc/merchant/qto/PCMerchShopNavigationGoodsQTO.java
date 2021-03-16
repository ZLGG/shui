package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-18
*/
public abstract class PCMerchShopNavigationGoodsQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopNavigationGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("店铺导航分类ID")
        private String shopNavigation;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }
}
