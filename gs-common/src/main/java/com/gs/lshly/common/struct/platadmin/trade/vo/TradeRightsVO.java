package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
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
 * @author zdf
 * @since 2020-12-22
 */
public abstract class TradeRightsVO implements Serializable {

    @Data
    @ApiModel("TradeRightsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("售后表ID")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("订单编号")
        private String orderCode;


        @ApiModelProperty("退款备注")
        private String refundRemarks;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;


        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;


        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;


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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime returnGoodsLogisticsDate;


        @ApiModelProperty("换货商机寄回物流公司名字")
        private String sendBackLogisticsName;


        @ApiModelProperty("换货商机寄回物流单号")
        private String sendBackLogisticsNum;


        @ApiModelProperty("换货商机寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime sendBackLogisticsDate;


        @ApiModelProperty("审核拒绝原因")
        private String rejectReason;


        @ApiModelProperty("申请时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime applyTime;


        @ApiModelProperty("完成时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime completeTime;


        @ApiModelProperty("审批人名字")
        private String handPersonName;


        @ApiModelProperty("审批人ID")
        private String merchantAccountId;


    }

    @Data
    @ApiModel("TradeRightsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("TradeRightsVO.RightsRefundListVO")
    public static class RightsRefundListVO implements Serializable {
        @ApiModelProperty("售后表ID")
        private String id;
        @ApiModelProperty("申请售后原因(10:质量问题,20:7天无理由,30:地址信息填写错误,40:不想要了,50:价格有点贵,60:商品错选,70:商品无货,80:其他)")
        private Integer rightsReasonType;
        @ApiModelProperty("修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;
        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;
        @ApiModelProperty("订单金额")
        private BigDecimal tradeAmount;
        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;
        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;
        @ApiModelProperty("订单ID")
        private String tradeId;
        @ApiModelProperty("订单编号")
        private String tradeCode;

    }

    @Data
    @ApiModel("TradeRightsVO.RightsListVO")
    public static class RightsListVO implements Serializable {

        @ApiModelProperty("售后表ID")
        private String id;

/*        @ApiModelProperty("二次审核状态(0:二次审核完成,1:需要二次审核)")
        private Integer isTwoCheck;*/

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("售后申请类型[10:换货,20:仅退款,30:退货退款]")
        private Integer rightsType;

        @ApiModelProperty("退款申请类型([10:取消订单退款,20:售后申请退款])")
        private Integer refundMoneyType;

        @ApiModelProperty("处理进度([10:处理中，20：已完成，30：待审核]) ")
        private Integer checkState;

        @ApiModelProperty("商品标题")
        private String goodsName;

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

        @ApiModelProperty("订单编号")
        private String orderCode;

/*        @ApiModelProperty("售后表ID")
        private String id;
        @ApiModelProperty("申请会员")
        private String userName;
        @ApiModelProperty("所属商家")
        private String shopName;
        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;
        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;
        @ApiModelProperty("订单id")
        private String tradeId;
        @ApiModelProperty("订单编号")
        private String tradeCode;
        @ApiModelProperty("商品标题")
        private String goodsTitle;
        @ApiModelProperty("申请售后数量")
        private Integer quantity;
        @ApiModelProperty("申请售后原因(10:质量问题,20:7天无理由,30:地址信息填写错误,40:不想要了,50:价格有点贵,60:商品错选,70:商品无货,80:其他)")
        private Integer rightsReasonType;
        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;*/
    }

    @Data
    @ApiModel("TradeRightsVO.RightsListViewVO")
    public static class RightsListViewVO implements Serializable {
        @ApiModelProperty("退换货基本信息")
        private TradeRightsVO.RefundBasicVO refundBasicVO;

        @ApiModelProperty("订单商品信息")
        private List<TradeRightsVO.TradeRightsGoodsVO> tradeRightsGoodsListVO;

       /* @ApiModelProperty("换货商品信息")
        private List<TradeRightsVO.TradeRightsGoodsVO> tradeRightsGoodsSendListVO;*/

        @ApiModelProperty("明细")
        private TradeRightsVO.RefundDetailVO refundDetailVO;

        @ApiModelProperty("退款信息")
        private TradeRightsVO.RefundMessageVO refundMessageVO;

        @ApiModelProperty("退换货处理结果")
        private TradeRightsVO.RefundResultVO refundResultVO;

        @ApiModelProperty("平台处理结果")
        private TradeRightsVO.PlatformResultVO platformResultVO;


/*        @ApiModelProperty("所属商家")
        private String shopName;
        @ApiModelProperty("图片售后凭证")
        private String rightsImage;
        @ApiModelProperty("商品名")
        private String goodsName;
        @ApiModelProperty("商品图片")
        private String goodsImage;
        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
        @ApiModelProperty("退款数量")
        private Integer quantity;
        @ApiModelProperty("总金额")
        private BigDecimal totalPrice;
        @ApiModelProperty("商家处理说明")
        private String merchanHandle;*/

    }

    @Data
    @ApiModel("TradeRightsVO.RefundBasicVO")
    public static class RefundBasicVO implements Serializable {
        @ApiModelProperty("售后编号")
        private String id;
        @ApiModelProperty("订单流水号")
        private String tradeId;
        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("状态(10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回,70:换货完成,80:商家确认收货并退款,90:用户取消)")
        private Integer state;

        @ApiModelProperty("申请时间")
        private LocalDateTime applyTime;

/*        @ApiModelProperty("二次审核状态(0:二次审核完成,1:需要二次审核)")
        private Integer isTwoCheck;*/

        @ApiModelProperty("售后申请类型[10:换货,20:仅退款,30:退货退款]")
        private Integer rightsType;

        @ApiModelProperty("订单编号(订单号)")
        private String orderCode;

        @ApiModelProperty("申请描述")
        private String rightsRemark;

        @ApiModelProperty("申请售后原因")
        private Integer rightsReasonType;

        @ApiModelProperty("支付方式(10:支付扫码,20:支付宝APP,30:微信扫码,40:微信公众号,50:微信APP支付,60:微信小程序支付," +
                "70:银联PC,80:银联移动,90:线下支付,100:积分支付,510:混合支付积分_微信小程序,511:混合支付积分_支付宝)")
        private Integer payType;

        @ApiModelProperty("商品图片凭证")
        private List<String> rightsImg;

    }

    @Data
    @ApiModel("TradeRightsVO.RefundDetailVO")
    public static class RefundDetailVO implements Serializable {
        @ApiModelProperty("实付金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("实退金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("应退金额")
        private BigDecimal shouldRefundAmount;

        @ApiModelProperty("实付积分")
        private BigDecimal tradePointAmount;

        @ApiModelProperty("应退积分")
        private BigDecimal shouldRefundPoint;

        @ApiModelProperty("实退积分")
        private BigDecimal refundPoint;
    }

    @Data
    @ApiModel("TradeRightsVO.RefundMessageVO")
    public static class RefundMessageVO implements Serializable {
        @ApiModelProperty("实退金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("实退积分")
        private BigDecimal refundPoint;

        @ApiModelProperty("退款方式/积分退回(10:原路退回)")
        private Integer refundType;
    }

    @Data
    @ApiModel("TradeRightsVO.RefundResultVO")
    public static class RefundResultVO implements Serializable {
        @ApiModelProperty("状态(10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回,70:换货完成,80:商家确认收货并退款,90:用户取消)")
        private Integer state;

        @ApiModelProperty("商家处理说明")
        private String rejectReason;
    }

    @Data
    @ApiModel("TradeRightsVO.PlatformResultVO")
    public static class PlatformResultVO implements Serializable {
        @ApiModelProperty("审核状态(10:处理中,20:已完成,30:待审核)")
        private Integer checkState;

        @ApiModelProperty("平台处理说明")
        private List<String> platformCheckReason;
    }


    @Data
    @ApiModel("TradeRightsVO.TradeRightsGoodsVO")
    public static class TradeRightsGoodsVO implements Serializable {

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("商品图片组")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品编号")
        private String goodsId;

        @ApiModelProperty("商品sku编号")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("规格")
        private String skuSpecValue;

        @ApiModelProperty("单价")
        private BigDecimal salePrice;

        @ApiModelProperty("积分")
        private BigDecimal pointPrice;

        @ApiModelProperty("商品类型(10:原商品,20:换货商品)")
        private Integer goodsType;
    }


    @Data
    @ApiModel("TradeRightsVO.RightsRefundViewVO")
    public static class RightsRefundViewVO implements Serializable {

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;
        @ApiModelProperty("订单号")
        private String tradeId;
        @ApiModelProperty("订单编号")
        private String tradeCode;
        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;
        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
        @ApiModelProperty("售后表ID")
        private String id;
        @ApiModelProperty("申请售后原因(10:质量问题,20:7天无理由,30:地址信息填写错误,40:不想要了,50:价格有点贵,60:商品错选,70:商品无货,80:其他)")
        private Integer rightsReasonType;
        @ApiModelProperty("实需退款")
        private BigDecimal refundAmount;
        @ApiModelProperty("用户名字")
        private String userName;
        @ApiModelProperty("退款方式 10=线下退款 20=原路返回")
        private Integer refundType;
        @ApiModelProperty("订单金额")
        private BigDecimal tradeAmount;
        @ApiModelProperty("商家名字")
        private String shopName;
        @ApiModelProperty("商家退款")
        private BigDecimal shopRefundAmount;
        @ApiModelProperty("退款人")
        private String refundPersonName;
        @ApiModelProperty("退款银行名字")
        private String refundBankName;

        @ApiModelProperty("退款银行账户")
        private String refundAccount;

        @ApiModelProperty("退款银行账户")
        private String refundName;

        @ApiModelProperty("收款银行名字")
        private String collectBankName;

        @ApiModelProperty("收款银行账户")
        private String collectAccount;

        @ApiModelProperty("收款人")
        private String collectName;

    }

    @Data
    @ApiModel("TradeRightsVO.SkuGoodsInfo")
    public static class SkuGoodsInfo implements Serializable {
        private String skuImg;
        private BigDecimal salePrice;
        private BigDecimal pointPrice;
        private Boolean isPointGood;
    }
}
