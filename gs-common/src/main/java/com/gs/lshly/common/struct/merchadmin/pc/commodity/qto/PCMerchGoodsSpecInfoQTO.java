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
public abstract class PCMerchGoodsSpecInfoQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsSpecInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("规格名称")
        private String specName;

        @ApiModelProperty("规格值名称")
        private String specValue;

    }
}
