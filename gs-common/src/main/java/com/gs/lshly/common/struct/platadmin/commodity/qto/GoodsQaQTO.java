package com.gs.lshly.common.struct.platadmin.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 16:35 2020/10/17
 */
public abstract class GoodsQaQTO implements Serializable {

    @Data
    @ApiModel("GoodsQaQTO.QTO")
    public static class QTO extends BaseQTO {
        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "店铺类型")
        private Integer shopType;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;

        @ApiModelProperty(value = "咨询内容")
        private String quizContent;

        @ApiModelProperty(value = "是否答复")
        private Integer isReply;

        @ApiModelProperty(value = "操作人")
        private String operator;
    }
}
