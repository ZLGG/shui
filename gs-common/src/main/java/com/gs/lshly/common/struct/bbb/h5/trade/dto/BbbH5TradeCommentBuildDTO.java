package com.gs.lshly.common.struct.bbb.h5.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentBuildDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbbH5TradeCommentBuildDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeCommentDTO.CommentBuildDTO")
    @Accessors(chain = true)
    public static class DTO extends BaseDTO implements Serializable  {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("订单商品ID")
        private String tradeGoodsId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("SKU ID")
        private String skuId;

        @ApiModelProperty("格规值")
        private String skuSpecValue;

        @ApiModelProperty("用户头像")
        private String userHeadImg;

        @ApiModelProperty("用户昵称")
        private String userName;

        @ApiModelProperty("订单创建时间")
        private LocalDateTime tradeCreateTime;

        @ApiModelProperty("商品描述分")
        private Integer describeGrade;

        @ApiModelProperty("物流服务分")
        private Integer deliveryGrade;

        @ApiModelProperty("服务态度分")
        private Integer serviceGrade;

        @ApiModelProperty("是否公开头像昵称")
        private Integer openInfo;

        @ApiModelProperty("会员评论")
        private String userComment;

        @ApiModelProperty("店铺回复")
        private String shopReply;

        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过")
        private Integer appealState;

        @ApiModelProperty("申诉类型：删除评论、修改评论")
        private Integer appealType;

        @ApiModelProperty(value = "评论图片集合")
        private List<CommentImgData> commentImgData;

        @Data
        public static class CommentImgData  extends BaseDTO implements Serializable {
            @ApiModelProperty(value = "评论ID")
            private String commentId;

            @ApiModelProperty(value = "评论图片")
            private String commentImg;

        }

    }
    @Data
    @ApiModel("BbbH5TradeCommentBuildDTO.AdditionalDTO")
    @Accessors(chain = true)
    public static class AdditionalDTO extends BaseDTO implements Serializable  {
        @ApiModelProperty("评论ID")
        private String id;

        @ApiModelProperty("追加评论")
        private String  userAppendComment;

        @ApiModelProperty(value = "评论图片集合")
        private List<CommentImgData> commentImgData;

        @Data
        public static class CommentImgData  extends BaseDTO implements Serializable {
            @ApiModelProperty(value = "评论ID")
            private String commentId;

            @ApiModelProperty(value = "评论图片")
            private String commentImg;

        }

    }

}
