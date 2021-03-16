package com.gs.lshly.common.struct.platadmin.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class TradeCommentVO implements Serializable {

    @Data
    @ApiModel("TradeCommentVO.ListVO")
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
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime appendCommentTime;


        @ApiModelProperty("店铺回复")
        private String shopReply;


        @ApiModelProperty("店铺回复时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime replyTime;


        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过")
        private Integer appealState;


        @ApiModelProperty("申诉类型：删除评论、修改评论")
        private Integer appealType;


    }

    @Data
    @ApiModel("TradeCommentVO.CommentAppealDetailVO")
    public static class CommentAppealDetailVO implements Serializable {
        @ApiModelProperty("评价信息的商品评分")
        private String goodsGrade;

        @ApiModelProperty("评价信息的评价内容")
        private String userComment;

        @ApiModelProperty("评价信息的评价时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime commentTime;

        @ApiModelProperty("订单信息")
        private TradeVO.TradeInfoVO tradeInfoVO;

        @ApiModelProperty("申诉进度信息的申诉类型")
        private Integer appealType;


        @ApiModelProperty("申诉基本信息")
        private TradeCommentRecordVO.AppealRecordDetailVO appealRecordDetailVO;
    }


    @Data
    @ApiModel("TradeCommentVO.CommentAppealListVO")
    public static class CommentAppealListVO implements Serializable {
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


        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过")
        private Integer appealState;


        @ApiModelProperty("申诉类型：删除评论、修改评论")
        private Integer appealType;


        @ApiModelProperty("驳回理由")
        private String refuseIdea;


        @ApiModelProperty("申诉时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime appealTime;


        @ApiModelProperty("最后修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;


    }
    @Data
    @ApiModel("TradeCommentVO.CommentListVO")
    public static class CommentListVO implements Serializable {
        @ApiModelProperty("交易评论ID")
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("会员名字")
        private String userName;

        @ApiModelProperty("商家名字")
        private String shopName;
        @ApiModelProperty("商家id")
        private String shopId;

        @ApiModelProperty("商品名字")
        private String goodsTitle;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品评分")
        private Integer goodsGrade;

        @ApiModelProperty("回复时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime replyTime;

        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过，关闭")
        private Integer appealState;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("最后修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;


    }
    @Data
    @ApiModel("TradeCommentVO.CommentDetailVO")
    public static class CommentDetailVO implements Serializable {
        @ApiModelProperty("是否有追评[10=有 20=无]")
        private Integer isAddComment;
        //TradeGoodsGradeEnum
        @ApiModelProperty("初评商品评分[10=差评 20=中评 30=好评]")
        private Integer describeGrade;

        @ApiModelProperty("初评会员评论")
        private String userComment;

        @ApiModelProperty("初评评论时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("初评晒图")
        private List<String> commentImage;

        @ApiModelProperty("店铺追评回复")
        private String shopAppendReply;
        @ApiModelProperty("店铺追评回复时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime appendReplyTime;

        @ApiModelProperty("店铺回复")
        private String shopReply;

        @ApiModelProperty("店铺回复时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime replyTime;

        @ApiModelProperty("会员追加评论")
        private String userAppendComment;
        @ApiModelProperty("追评晒图")
        private List<String> appendImage;


        @ApiModelProperty("会员追加评论时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime appendCommentTime;

        @ApiModelProperty("商品评分")
        private Integer goodsGrade;






    }


    }
