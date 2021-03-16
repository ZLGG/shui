package com.gs.lshly.common.struct.platadmin.commodity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Starry
 * @Date 16:35 2020/10/17
 */
public abstract class GoodsQaVO implements Serializable {
    @Data
    @ApiModel("GoodsQaVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商品问答id")
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

        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;

    }

    @Data
    @ApiModel("GoodsQaVO.GoodsQaListVO")
    public static class GoodsQaListVO extends ListVO{

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型")
        private Integer shopType;
    }

    @Data
    @ApiModel("GoodsQaVO.GoodsQaDetailVO")
    public static class GoodsQaDetailVO extends ListVO{
        @ApiModelProperty(value = "商家名称")
        private String merchantName;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型")
        private Integer shopType;

        @ApiModelProperty("商品图片")
        private String goodsImg;
    }
}
