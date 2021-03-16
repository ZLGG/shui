package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-11-17
*/
public abstract class TradeCommentDTO implements Serializable {

    @Data
    @ApiModel("TradeCommentDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易评论ID",hidden = true)
        private String id;

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

        @ApiModelProperty("会员追加评论")
        private String userAppendComment;

        @ApiModelProperty("会员追加评论时间")
        private LocalDateTime appendCommentTime;

        @ApiModelProperty("店铺回复")
        private String shopReply;

        @ApiModelProperty("店铺回复时间")
        private LocalDateTime replyTime;

        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过")
        private Integer appealState;

        @ApiModelProperty("申诉类型：删除评论、修改评论")
        private Integer appealType;
    }

    @Data
    @ApiModel("TradeCommentDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论ID")
        private String id;
    }

    @Data
    @ApiModel("TradeCommentDTO.IdsDTO")
    @AllArgsConstructor
    public static class IdsDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论ID")
        private List<String> ids;
    }

    @Data
    @ApiModel("TradeCommentDTO.CheckCommentAppealDTO")
    public static class CheckCommentAppealDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论ID")
        private String commentId;

        @ApiModelProperty(value = "驳回理由")
        private String refuseIdea;

        @ApiModelProperty(value = "审核状态  10=未申诉、20=商家申诉、30=驳回、40=通过，50=关闭")
        private Integer checkState;

    }


}
