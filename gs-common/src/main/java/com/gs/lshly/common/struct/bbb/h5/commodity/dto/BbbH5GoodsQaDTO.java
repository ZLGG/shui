package com.gs.lshly.common.struct.bbb.h5.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-16
*/
public abstract class BbbH5GoodsQaDTO implements Serializable {

    @Data
    @ApiModel("BbcGoodsQaDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品问答id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("咨询类型（10=商品咨询，20=库存配送，30=支付方式，40=发票保修）")
        private Integer quizType;

        @ApiModelProperty("咨询内容")
        private String quizContent;

        @ApiModelProperty("联系方式")
        private String contactWay;

        @ApiModelProperty("是否匿名（默认为10=实名，20=匿名）")
        private Integer isAnonymous;

        @ApiModelProperty("咨询人")
        private String operator;

        @ApiModelProperty(value = "ip",hidden = true)
        private String ip;

    }

    @Data
    @ApiModel("BbcGoodsQaDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品问答id")
        private String id;
    }


}
