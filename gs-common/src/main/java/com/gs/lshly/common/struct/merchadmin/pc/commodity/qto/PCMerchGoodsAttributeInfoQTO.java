package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-09
*/
public abstract class PCMerchGoodsAttributeInfoQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsAttributeInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("商品属性名称")
        private String attributeName;

        @ApiModelProperty("商品属性值名称")
        private String attributeValue;

        @ApiModelProperty("排序")
        private Integer idx;

    }
}
