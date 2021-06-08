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
    public static class ListVO implements Serializable {

        @ApiModelProperty("售后表ID")
        private String id;
/*
        @ApiModelProperty("订单ID")
        private String tradeId;*/

/*        @ApiModelProperty("会员ID")
        private String userId;*/

        /*@ApiModelProperty("QQ号")
        private String qqNumber;

        @ApiModelProperty("热线电话")
        private String shopPhone;*/

/*        @ApiModelProperty("店铺ID")
        private String shopId;*/

        @ApiModelProperty("店铺名称")
        private String shopName;

       /* @ApiModelProperty("店铺Logo")
        private String shopLogo;
*/

/*        @ApiModelProperty("商家ID")
        private String merchantId;*/

/*        @ApiModelProperty("订单编号")
        private String orderCode;*/

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("退款积分")
        private BigDecimal refundPoint;

        @ApiModelProperty("状态(10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回,70:换货完成,80:商家确认收货并退款,90:用户取消)")
        private Integer state;

/*        @ApiModelProperty("退款备注")
        private String refundRemarks;*/

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

/*        @ApiModelProperty("退货物流公司名字")
        private String returnGoodsLogisticsName;

        @ApiModelProperty("退货物流单号")
        private String returnGoodsLogisticsNum;

        @ApiModelProperty("退货寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime returnGoodsLogisticsDate;*/

/*        @ApiModelProperty("换货商家寄回物流公司名字")
        private String sendBackLogisticsName;

        @ApiModelProperty("换货商家寄回物流单号")
        private String sendBackLogisticsNum;

        @ApiModelProperty("换货商家寄出时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime sendBackLogisticsDate;*/

/*        @ApiModelProperty("申请售后原因(10:不想要或多拍了,15:商品信息拍错(规格/尺码/颜色等),20:地址/电话信息填写错误,25:没用或少用优惠券,30:协商退款一致,35:缺货,40:其他,45:与商品详情不符,50:生产日期/保质期与商品不符,55:图片/产地/规格等描述不符,60:商品变质,65:商品破损/缺少配件,70:卖家发错货,75:不喜欢/不想要,80:空包裹,85:快递一直未送达,90:快递无跟踪记录,95:货物破损已拒签)")
        private Integer rightsReasonType;*/

        @ApiModelProperty("申请售后说明")
        private String rightsRemark;
/*
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
        private String merchantAccountId;*/

        /*        @ApiModelProperty("交易商品集合")
                List<BbcTradeRightsVO.TradeRightsGoodsVO> tradeRightsGoodsVOS;*/

        @ApiModelProperty("买家收货人姓名")
        private String recvPersonName;

        @ApiModelProperty("买家电话")
        private String recvPhone;

        @ApiModelProperty("买家收货地址全文本")
        private String recvFullAddres;

        @ApiModelProperty("卖家收货姓名")
        private String merPersonName;

        @ApiModelProperty("卖家收货电话")
        private String merPhone;

        @ApiModelProperty("卖家地址全文本")
        private String merFullAddres;

        @ApiModelProperty("申请时间")
        private LocalDateTime cdate;

        @ApiModelProperty("商品详情")
        List<BbcTradeRightsVO.TradeRightsGoodsVO> tradeRightsGoodsVO;
    }

    @Data
    @ApiModel("BbcTradeRightsVO.TradeRightsGoodsVO")
    @Accessors(chain = true)
    public static class TradeRightsGoodsVO implements Serializable {

/*        @ApiModelProperty("交易商品ID")
        private String id;*/
/*
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
        private String merchantId;*/

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;
/*

        @ApiModelProperty("商品货号")
        private String goodsNo;
*/

        @ApiModelProperty("SKU ID")
        private String skuId;

        @ApiModelProperty("规格")
        private String skuSpecValue = "";

        @ApiModelProperty("商品图片")
        private String goodsImage;

/*        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;*/
/*

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
*/

        @ApiModelProperty("换货数量")
        private Integer quantity;

        @ApiModelProperty("商品计价单位")
        private String goodsPriceUnit;

/*        @ApiModelProperty("退回金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("退回积分")
        private BigDecimal refundPoint;*/

        @ApiModelProperty("原商品或换货商品(10:原商品,20:换货商品)")
        private Integer goodsType;

/*        @ApiModelProperty("商品类型（20实物，10虚拟）")
        private Integer exchangeType;*/

    }

    @Data
    @ApiModel("BbcTradeRightsVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty("售后图片")
        private List<String> tradeRightImg;

    }

    @Data
    @ApiModel("BbcTradeRightsVO.GoodsTotalVO")
    public static class GoodsTotalVO implements Serializable {

        @ApiModelProperty("退款金额")
        private BigDecimal amount;

        @ApiModelProperty("退款积分")
        private BigDecimal point;
    }

    @Data
    @ApiModel("BbcTradeRightsVO.IdVO")
    public static class IdVO implements Serializable{
        @ApiModelProperty("售后Id")
        private String id;
    }

    @Data
    @ApiModel("BbcTradeRightsVO.GoodsInfo")
    public static class GoodsInfo implements Serializable {
        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品计价单位")
        private String goodsPriceUnit;

        @ApiModelProperty("商品图片")
        private String goodsImage;
    }
}
