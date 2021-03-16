package com.gs.lshly.common.struct.bbb.pc.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class PCBbbUserFrequentGoodsDTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserFrequentGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("常购清单项数组")
        @NotEmpty
        private List<FrequentItem> frequentItemList = new ArrayList<>();

    }

    @Data
    @ApiModel("PCBbbUserFrequentGoodsDTO.FrequentItem")
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
    @ApiModel("PCBbbUserFrequentGoodsDTO.OneETO")
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
    @ApiModel("PCBbbUserFrequentGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }

    @Data
    @ApiModel("PCBbbUserFrequentGoodsDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty("商购ID")
        private List<String> idList;
    }

}
