package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-16
*/
public abstract class ShopGoodsCategoryDTO implements Serializable {

    @Data
    @ApiModel("ShopGoodsCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("类目费用")
        private BigDecimal sharePrice;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("ShopGoodsCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }


    @Data
    @ApiModel("ShopGoodsCategoryDTO.PriceETO")
    @Accessors(chain = true)
    public static class PriceETO extends BaseDTO {

        @ApiModelProperty(value = "主键ID(不是商品三级类目ID)")
        private String id;

        @ApiModelProperty("类目费用")
        private BigDecimal fee;

    }

    @Data
    @ApiModel("ShopGoodsCategoryDTO.ListPriceETO")
    @Accessors(chain = true)
    public static class ListPriceETO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "编辑商品类目费用数组")
        private List<PriceETO> list;

    }

}
