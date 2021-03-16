package com.gs.lshly.common.struct.bbb.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbbTradeCommentListVO implements Serializable {

    @Data
    @ApiModel("BbbTradeCommentListVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易评论ID")
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
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
        private LocalDateTime tradeCreateTime;


        @ApiModelProperty("商品描述分")
        private Integer describeGrade;


        @ApiModelProperty("物流服务分")
        private Integer deliveryGrade;


        @ApiModelProperty("服务态度分")
        private Integer serviceGrade;

        @ApiModelProperty("商品评价[10=差评 20=中评 30=好评]")
        private Integer goodsGrade;


        @ApiModelProperty("是否公开头像昵称")
        private Integer openInfo;


        @ApiModelProperty("会员评论")
        private String userComment;


        @ApiModelProperty("店铺回复")
        private String shopReply;


        @ApiModelProperty("店铺回复时间")
        private LocalDateTime replyTime;


        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过")
        private Integer appealState;


        @ApiModelProperty("申诉类型：删除评论、修改评论")
        private Integer appealType;

        @ApiModelProperty("交易评论图片集合")
        List<CommentImgVO> commentImgVOS;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("会员追加评论")
        private String userAppendComment;

        @ApiModelProperty("追加评论时间")
        private LocalDateTime appendCommentTime;

        @ApiModelProperty("追加评论以后天数")
        private Integer appendCommentTranslateTime;

        @ApiModelProperty("初评时间")
        private LocalDateTime cdate;

        @ApiModelProperty("是否可以初评")
        private Integer commentFlag;

    }

    @Data
    @ApiModel("BbbTradeCommentListVO.CommentImgVO")
    @Accessors(chain = true)
    public static class CommentImgVO implements Serializable{

        @ApiModelProperty("交易评论图片ID")
        private String id;

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论图片")
        private String commentImg;

    }
    @Data
    @ApiModel("BbbTradeCommentListVO.GoodsGrade")
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoodsGrade implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;

        @ApiModelProperty("商品中和评分")
        private String gradeScore;
    }

}
