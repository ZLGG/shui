package com.gs.lshly.common.struct.bbb.h5.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class BbbH5UserFrequentGoodsV2DTO implements Serializable {

    @Data
    @ApiModel("BbbH5UserFrequentGoodsV2DTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("常购清单项数组")
        @NotEmpty
        private List<OneItem> skuList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbH5UserFrequentGoodsV2DTO.OneItem")
    @Accessors(chain = true)
    public static class OneItem implements Serializable {

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
    @ApiModel("BbbH5UserFrequentGoodsV2DTO.OneETO")
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
    @ApiModel("BbbH5UserFrequentGoodsV2DTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @NotBlank
        @ApiModelProperty("常购ID")
        private String id;

    }

    @Data
    @ApiModel("BbbH5UserFrequentGoodsV2DTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty("商购ID数组")
        @NotEmpty
        private List<String> idList;
    }

}
