package com.gs.lshly.common.struct.bbc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsVO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后ID")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("会员ID")
        private String userId;



        @ApiModelProperty("QQ号")
        private String qqNumber;

        @ApiModelProperty("热线电话")
        private String shopPhone;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("订单编号")
        private String orderCode;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;


        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;

        @ApiModelProperty("退款备注")
        private String refundRemarks;


        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("退货物流公司名字")
        private String returnGoodsLogisticsName;

        @ApiModelProperty("退货物流单号")
        private String returnGoodsLogisticsNum;


        @ApiModelProperty("退货寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime returnGoodsLogisticsDate;

        @ApiModelProperty("换货商机寄回物流公司名字")
        private String sendBackLogisticsName;

        @ApiModelProperty("换货商机寄回物流单号")
        private String sendBackLogisticsNum;


        @ApiModelProperty("换货商机寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime sendBackLogisticsDate;


        @ApiModelProperty("申请售后原因(10:质量问题,20:7天无理由,30:地址信息填写错误,40:不想要了,50:价格有点贵,60:商品错选,70:商品无货,80:其他)")
        private Integer rightsReasonType;


        @ApiModelProperty("申请售后说明")
        private String rightsRemark;


        @ApiModelProperty("退货方式(10:自行寄回,20:上门取件)")
        private Integer returnType;


        @ApiModelProperty("审核拒绝原因")
        private String rejectReason;


        @ApiModelProperty("申请时间")
        private LocalDateTime applyTime;


        @ApiModelProperty("完成时间")
        private LocalDateTime completeTime;


        @ApiModelProperty("审批人名字")
        private String handPersonName;


        @ApiModelProperty("审批人ID")
        private String merchantAccountId;

        @ApiModelProperty("交易商品集合")
        List<BbcTradeRightsVO.TradeRightsGoodsVO> tradeRightsGoodsVOS;

    }

    @Data
    @ApiModel("BbcTradeRightsVO.TradeGoodsVO")
    @Accessors(chain = true)
    public static class TradeRightsGoodsVO implements Serializable{

        @ApiModelProperty("交易商品ID")
        private String id;


        @ApiModelProperty("售后表ID")
        private String rightsId;


        @ApiModelProperty("交易ID")
        private String tradeId;

        @ApiModelProperty("订单商品ID")
        private String tradeGoodsId;

        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("商品ID")
        private String goodsId;


        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品货号")
        private String goodsNo;


        @ApiModelProperty("SKU ID")
        private String skuId;


        @ApiModelProperty("格规值")
        private String skuSpecValue = "";

        @ApiModelProperty("SKU IMG")
        private String skuImg;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;


        @ApiModelProperty("销售价")
        private BigDecimal salePrice;


        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;


        @ApiModelProperty("支付总金额")
        private BigDecimal payAmount;


        @ApiModelProperty("所得积分")
        private BigDecimal giftIntegral;


        @ApiModelProperty("订单总价占比")
        private BigDecimal tradeAmountPercent;

        @ApiModelProperty("售后数量")
        private Integer quantity;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;


    }

    @Data
    @ApiModel("BbcTradeRightsVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("售后图片")
        private List<String> tradeRightImg;
    }
}
