package com.gs.lshly.common.struct.bbb.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-10-16
*/
public abstract class PCBbbGoodsQaDTO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsQaDTO.ETO")
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

        @ApiModelProperty("回答内容")
        private String content;

        @ApiModelProperty("联系方式")
        private String contactWay;

        @ApiModelProperty("是否匿名（默认为10=实名，20=匿名）")
        private Integer isAnonymous;

        @ApiModelProperty("是否将咨询内容显示在商品详情页（默认为10=不显示，20=显示）")
        private Integer isShowQuizContent;

        @ApiModelProperty("是否将回答内容显示在商品详情页（默认为10=不显示，20=显示）")
        private Integer isShowContent;

        @ApiModelProperty(value = "是否已回复(10=未回复，20=已回复）")
        private Integer isReply;

        @ApiModelProperty("ip地址")
        private String ip;

        @ApiModelProperty("操作人")
        private String operator;

    }

    @Data
    @ApiModel("PCBbbGoodsQaDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "商品问答id")
        private List<String> idList = new ArrayList<>();
    }


}
