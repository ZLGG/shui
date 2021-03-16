package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 10:05 2020/10/17
 */
public  abstract class PCMerchGoodsQaQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsQaQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;
    }


    @Data
    @ApiModel("PCMerchGoodsQaQTO.GoodsQaQTO")
    public static class GoodsQaQTO extends BaseQTO {

        @ApiModelProperty(value ="店铺id")
        private String shopId;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;

        @ApiModelProperty(value = "是否已回复(10=未回复，20=已回复）")
        private Integer isReply;

    }


}
