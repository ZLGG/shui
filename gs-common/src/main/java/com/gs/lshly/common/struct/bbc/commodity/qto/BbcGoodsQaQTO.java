package com.gs.lshly.common.struct.bbc.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-16
*/
public abstract class BbcGoodsQaQTO implements Serializable {

    @Data
    @ApiModel("BbcGoodsQaQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;
    }

    @Data
    @ApiModel("BbcGoodsQaQTO.GoodsIdQTO")
    public static class GoodsIdQTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

    }
}
