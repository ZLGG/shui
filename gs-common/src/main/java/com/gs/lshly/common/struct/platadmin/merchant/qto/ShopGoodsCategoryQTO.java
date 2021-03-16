package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-16
*/
public abstract class ShopGoodsCategoryQTO implements Serializable {

    @Data
    @ApiModel("ShopGoodsCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
    @ApiModel("ShopGoodsCategoryDTO.ShopIdDTO")
    @AllArgsConstructor
    public static class ShopIdQTO extends BaseQTO {
        @ApiModelProperty(value = "店铺ID")
        private String shopId;
    }
}
