package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.enums.TradeAppealStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-11-16
*/
public abstract class PCMerchTradeCommentVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentVO.ListVO")
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
    @ApiModel("PCMerchTradeCommentVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    @Data
    @ApiModel("PCMerchTradeCommentVO.CommentOverViewVO")
    public static class CommentOverViewVO implements Serializable {
        //店铺动态评分
        @ApiModelProperty("实物与描述相符")
        private String describeGrade;

        @ApiModelProperty("服务质量")
        private String deliveryGrade;

        @ApiModelProperty("发货速度")
        private String serviceGrade;

        //行业平均分同必

        @ApiModelProperty("描述相符")
        private String describeGradeLv;

        @ApiModelProperty("服务质量")
        private String deliveryGradeLv;

        @ApiModelProperty("发货速度")
        private String serviceGradeLv;

        //店铺近期评分状况
        @ApiModelProperty("发货速度")
        private List<Grade> grades;

    }
    @Data
    @ApiModel("PCMerchTradeCommentVO.Grade")
    public static class Grade implements Serializable {
        @ApiModelProperty("商品评价[10=差评 20=中评 30=好评 40=总计]")
        private Integer goodsGrade;

        @ApiModelProperty("最近1周")
        private Integer week;

        @ApiModelProperty("最近1个月")
        private Integer months;

        @ApiModelProperty("最近6个月")
        private Integer sixMonths;

        @ApiModelProperty("6个月前")
        private Integer sixMonthsAgo;

        @ApiModelProperty("总计")
        private Integer total;


    }
    @Data
    @ApiModel("PCMerchTradeCommentVO.CommentDetailVO")
    public static class CommentDetailVO implements Serializable {
        @ApiModelProperty("商品图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("成交价")
        private BigDecimal price;

        @ApiModelProperty("评分[10=好评 20=中评 30=差评]")
        private Integer score;

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
    }




    @Data
    @ApiModel("PCMerchTradeCommentVO.AppealDetailVO")
    public static class AppealDetailVO implements Serializable {
        @ApiModelProperty("商品图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("成交价")
        private BigDecimal price;

        @ApiModelProperty("商品评分")
        private String commentGrade;

        @ApiModelProperty("评价时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime commentTime;

        @ApiModelProperty("评价内容")
        private String commentContent;

        @ApiModelProperty("申诉理由")
        private String appealIdea;

        @ApiModelProperty("申诉结果 10=未申诉 20=申诉中 30=驳回 40=通过 50=关闭")
        private Integer appealState;


    }

    @Data
    @ApiModel("PCMerchTradeCommentVO.CommentAppealListVO")
    public static class CommentAppealListVO implements Serializable{
        @ApiModelProperty("评价id")
        private String id;

        @ApiModelProperty("评价等级")
        private String commentLevel;

        @ApiModelProperty("申诉内容")
        private PCMerchTradeCommentRecordVO.AppealContentVO appealContentVO;

        @ApiModelProperty("订单编号")
        private String tradeCode;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品销售金额")
        private BigDecimal salePrice;

        @ApiModelProperty("评价人")
        private String userName;

        @ApiModelProperty("进度")
        private String progress;

        @ApiModelProperty("申诉结果")
        private Integer appealState;

        @ApiModelProperty("是否开启二次申诉")
        private boolean openReAppeal;
    }
    @Data
    @ApiModel("PCMerchTradeCommentVO.CommentListListVO")
    public static class CommentListListVO implements Serializable{
        @ApiModelProperty("评论ID")
        private String id;
        @ApiModelProperty("评分[10=好评 20=中评 30=差评]")
        private Integer score;

        @ApiModelProperty("初评")
        private String userComment;

        @ApiModelProperty("初评时间/时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("初评图片")
        private List<String> userCommentImg;

        @ApiModelProperty("追加评论")
        private String userAppendComment;


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

        @ApiModelProperty("会员追加评论时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime appendCommentTime;
        @ApiModelProperty("追评图片")
        private List<String> userAppendCommentImg;
        @ApiModelProperty("是否匿名[10=是 20=否]")
        private Integer openInfo;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("商品总价")
        private BigDecimal totalAmount;

        @ApiModelProperty("用户昵称")
        private String userName;

        @ApiModelProperty("商品描述分")
        private Integer describeGrade;

        @ApiModelProperty("商品评分[10=差评 20=中评 30=好评]")
        private Integer goodsGrade;

        @ApiModelProperty("申诉状态:未申诉、商家申诉、驳回、通过，关闭")
        private Integer appealState;


    }


}
