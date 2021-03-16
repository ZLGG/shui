package com.gs.lshly.common.struct.merchadmin.h5.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2021-01-22
*/
public abstract class H5MerchGoodsQaQTO implements Serializable {

    @Data
    @ApiModel("H5MerchGoodsQaQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;
    }

    @Data
    @ApiModel("H5MerchGoodsQaQTO.GoodsQaQTO")
    public static class GoodsQaQTO extends BaseQTO {

        @ApiModelProperty(value ="店铺id")
        private String shopId;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;

        @ApiModelProperty(value = "是否已回复(10=未回复，20=已回复）")
        private Integer isReply;

    }

}
