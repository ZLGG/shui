package com.gs.lshly.common.struct.bbb.h5.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class BbbH5UserFrequentGoodsDTO implements Serializable {

    @Data
    @ApiModel("BbbH5UserFrequentGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("常购清单项数组")
        private List<FrequentItem> frequentItemList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbH5UserFrequentGoodsDTO.FrequentItem")
    @Accessors(chain = true)
    public static class FrequentItem implements Serializable {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品SKU-ID")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

    }

    @Data
    @ApiModel("BbbH5UserFrequentGoodsDTO.OneETO")
    @Accessors(chain = true)
    public static class OneETO extends BaseDTO {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品SKU-ID")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

    }

    @Data
    @ApiModel("BbbH5UserFrequentGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }

    @Data
    @ApiModel("BbbH5UserFrequentGoodsDTO.IdListDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty("SKU常购ID")
        private List<String> idList;
    }

}
