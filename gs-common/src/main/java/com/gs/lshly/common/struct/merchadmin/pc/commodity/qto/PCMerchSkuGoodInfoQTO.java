package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-08
*/
public abstract class PCMerchSkuGoodInfoQTO implements Serializable {

    @Data
    @ApiModel("PCMerchSkuGoodInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("类目id")
        private String categoryId;

        @ApiModelProperty("规格id组")
        private String specsKey;

        @ApiModelProperty("规格值组")
        private String specsValue;

        @ApiModelProperty("sku商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("条形码")
        private String barcode;

        @ApiModelProperty("商品图片")
        private String image;

        @ApiModelProperty("阶梯价格")
        private String stepPrice;

        @ApiModelProperty("商品状态")
        private Integer state;
    }
}
