package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zdf
 * @since 2020-12-17
 */
public abstract class PCMerchTradeRightsVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeRightsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("售后表ID")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("申请时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime cdate;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("订单编号")
        private String orderCode;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("退款备注")
        private String refundRemarks;

        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;

        @ApiModelProperty("申请售后原因(10:质量问题,20:7天无理由,30:地址信息填写错误,40:不想要了,50:价格有点贵,60:商品错选,70:商品无货,80:其他)")
        private Integer rightsReasonType;


        @ApiModelProperty("申请售后说明")
        private String rightsRemark;


        @ApiModelProperty("退货方式(10:自行寄回,20:上门取件)")
        private Integer returnType;

        @ApiModelProperty("退货物流公司名字")
        private String returnGoodsLogisticsName;

        @ApiModelProperty("退货物流单号")
        private String returnGoodsLogisticsNum;


        @ApiModelProperty("退货寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime returnGoodsLogisticsDate;

        @ApiModelProperty("换货商机寄回物流公司名字")
        private String sendBackLogisticsName;

        @ApiModelProperty("换货商机寄回物流单号")
        private String sendBackLogisticsNum;


        @ApiModelProperty("换货商机寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime sendBackLogisticsDate;

        @ApiModelProperty("审核拒绝原因")
        private String rejectReason;


        @ApiModelProperty("申请时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime applyTime;


        @ApiModelProperty("完成时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime completeTime;


        @ApiModelProperty("审批人名字")
        private String handPersonName;


        @ApiModelProperty("审批人ID")
        private String merchantAccountId;


    }

    @Data
    @ApiModel("PCMerchTradeRightsVO.DetailVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailVO implements Serializable {
        //会员名称，商家名称，下单时间，订单完成状态，收货信息，
/*        @ApiModelProperty("会员名称")
        private String userName;

        @ApiModelProperty("商家名称")
        private String merchantName;

        @ApiModelProperty("下单时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime tradeDate;

        @ApiModelProperty("订单完成状态[10:待支付,20:待发货,30:待收货,40:已完成,50:已取消]")
        private Integer tradeState;

        @ApiModelProperty("收货信息(收货人名+电话+地址)")
        private String receivingInformation;

        // 退换货商品图片，名字，售价，数量，总金额，
        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("售后数量")
        private Integer quantity;

        @ApiModelProperty("审核状态")
        private String checkState;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("总金额")
        private BigDecimal totalAmount;
        // 售后申请退换货理由，问题描 ,图片
        @ApiModelProperty("评论图片")
        private List<String> rightsImg;

        @ApiModelProperty("进度")
        private List<String> speedProgress;

        @ApiModelProperty("退款状态[10=等待平台退款 20=退款完成]TradeRefundStatusEnum")
        private Integer refundState;*/
        @ApiModelProperty("售后编号")
        private String id;
        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("售后原因(10:不想要或多拍了,15:商品信息拍错(规格/尺码/颜色等),20:地址/电话信息填写错误," +
                "25:没用或少用优惠券,30:协商退款一致,35:缺货,40:其他,45:与商品详情不符,50:生产日期/保质期与商品不符," +
                "55:图片/产地/规格等描述不符,60:商品变质,65:商品破损/缺少配件,70:卖家发错货,75:不喜欢/不想要,80:空包裹," +
                "85:快递一直未送达,90:快递无跟踪记录,95:货物破损已拒签,100:取消订单,105:充值未到账,110充值少到账)")
        private Integer rightsReasonType;

        @ApiModelProperty("备注信息")
        private String rightsRemark;

        @ApiModelProperty("订单id")
        private String tradeId;

        @ApiModelProperty("实付金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("应退金额")
        private BigDecimal shouldRefundAmount;

        @ApiModelProperty("实退金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("实付积分")
        private BigDecimal tradePointAmount;

        @ApiModelProperty("应退积分")
        private BigDecimal shouldRefundPoint;

        @ApiModelProperty("实退积分")
        private BigDecimal refundPoint;

        @ApiModelProperty("运费")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("售后凭证图片")
        private List<String> rightsImge;

        @ApiModelProperty("商品信息")
        private List<PCMerchTradeRightsVO.GoodsVO> goodsVOList;

        @ApiModelProperty("处理进度")
        private List<PCMerchTradeRightsVO.LogVO> logVOList;
    }

    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchTradeRightsVO.RightList")
    public static class RightList implements Serializable {
        @ApiModelProperty("售后编号")
        private String id;

/*        @ApiModelProperty("订单ID")
        private String tradeId;*/

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("订单编号")
        private String orderCode;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("售后原因(10:不想要或多拍了,15:商品信息拍错(规格/尺码/颜色等),20:地址/电话信息填写错误," +
                "25:没用或少用优惠券,30:协商退款一致,35:缺货,40:其他,45:与商品详情不符,50:生产日期/保质期与商品不符," +
                "55:图片/产地/规格等描述不符,60:商品变质,65:商品破损/缺少配件,70:卖家发错货,75:不喜欢/不想要,80:空包裹," +
                "85:快递一直未送达,90:快递无跟踪记录,95:货物破损已拒签,100:取消订单,105:充值未到账,110充值少到账)")
        private Integer rightsReasonType;

        @ApiModelProperty("处理进度(10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回," +
                "70:换货完成,80:商家确认收货并退款,90:用户取消)")
        private Integer state;
/*        @ApiModelProperty("申请时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("进度")
        private List<String> speedProgress;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("规格值")
        private String skuSpecValue;

        @ApiModelProperty("售价")
        private BigDecimal salePrice;*/

    }

    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchTradeRightsVO.GoodsVO")
    public static class GoodsVO implements Serializable {
        @ApiModelProperty("商品名称")
        private String goodsName;
        @ApiModelProperty("售后数量")
        private Integer quantity;
        @ApiModelProperty("售后商品类型(10:原商品,20:换货商品)")
        private String goodsType;
        @ApiModelProperty("商品货号")
        private String goodsNo;
        @ApiModelProperty("商品sku图片")
        private String skuImg;
    }

    @Data
    @ApiModel("PCMerchTradeRightsVO.LogVO")
    @Accessors(chain = true)
    public static class LogVO implements Serializable {
        @ApiModelProperty("状态(10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回,70:换货完成,80:商家确认收货并退款,90:用户取消)")
        private Integer state;

        @ApiModelProperty("状态说明")
        private String content;

        @ApiModelProperty("申请时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
    }
}