package com.gs.lshly.common.struct.bbb.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.enums.TradeCancelReasonTypeEnum;
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
* @author oy
* @since 2020-10-28
*/
public abstract class BbbTradeListVO implements Serializable {


    @Data
    @ApiModel("BbbTradeListVO.tradeVO")
    @Accessors(chain = true)
    public static class tradeVO implements Serializable{

        @ApiModelProperty("交易订单号(ID)")
        private String id;


        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员名称")
        private String userName;


        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        @ApiModelProperty("经度")
        private BigDecimal shopLongitude;

        @ApiModelProperty("纬度")
        private BigDecimal shopLatitude;

        @ApiModelProperty("店铺地址全路径")
        private String shopFullAddres;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("交易编号")
        private String tradeCode;


        @ApiModelProperty("交易状态")
        private Integer tradeState;


        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;


        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;


        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;


        @ApiModelProperty("交易总金额")
        private BigDecimal tradeAmount;


        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty("支付截止时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payDeadline;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime recvTime;


        @ApiModelProperty("支付类型")
        private Integer payType;


        @ApiModelProperty("配送类型")
        private Integer deliveryType;

        @ApiModelProperty("物流单号")
        private String logisticsNumber;

        @ApiModelProperty("物流公司代码")
        private String logisticsCompanyCode;

        @ApiModelProperty("物流公司名称")
        private String logisticsCompanyName;

        @ApiModelProperty("自提码")
        private String takeGoodsCode;


        @ApiModelProperty("自提码图片")
        private String takeGoodsQrcode;


        @ApiModelProperty("收货地址ID")
        private String recvAddresId;


        @ApiModelProperty("收货人")
        private String recvPersonName;


        @ApiModelProperty("收货人电话")
        private String recvPhone;


        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;


        @ApiModelProperty("是否超时取消")
        private Integer timeoutCancel;

        @ApiModelProperty("需要发票")
        private Boolean isInvoice;

        @ApiModelProperty("买家留言")
        private String buyerRemark;

        @ApiModelProperty("线下支付状态[10=待确认 20=驳回 30=已确认]")
        private Integer offlineState;

        @ApiModelProperty("线下支付驳回拒绝原因")
        private String verifyRemark;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;

        @ApiModelProperty("交易商品集合")
        List<TradeGoodsVO> tradeGoodsVOS;

        @ApiModelProperty("是否投诉[10=是 20=否]")
        private Integer isComplaint;

        @ApiModelProperty("是否可以线下支付[10=可以 20=不可以]")
        private Integer isOfflinePay;

        @ApiModelProperty("订单详情状态[1=订单已生成等待您付款 2=您已付款成功等待商家发货 3=订单已发货请关注物流及时发货 4=确认收货完成订单 5=订单取消]")
        private Integer tradeDetailState;

        @ApiModelProperty("取消原因类型[10=地址信息填写错误 20=不想要了 30=价格有点贵 40=商品错选或多选 50=商品无货 60=其他]")
        private Integer reasonType;

        @ApiModelProperty("取消原因说明")
        private String remark;

        @ApiModelProperty("支付状态[10=待支付,40=已支付]")
        private Integer payState;

        @ApiModelProperty("取消信息")
        private BbbTradeListVO.tradeVO.Cancel cancelInfo;

        @ApiModelProperty("售后信息")
        private BbbTradeListVO.tradeVO.Right rightsInfo;

        @ApiModelProperty("发票ID")
        private String invoiceId;

        @ApiModelProperty("发票信息")
        private BbbTradeListVO.tradeVO.Invoice invoiceInfo;

        @Data
        @ApiModel("BbbTradeListVO.tradeVO.Right")
        @Accessors(chain = true)
        public static class Invoice implements Serializable{
            @ApiModelProperty("发票ID")
            private String invoiceId;
            @ApiModelProperty("发票类型[10增值税普通发票 20增值税专用发票]")
            private Integer invoiceType;
            @ApiModelProperty("公司名称")
            private String firmName;
            @ApiModelProperty("税务识别码")
            private String taxNumber;
        }
        @Data
        @ApiModel("BbbTradeListVO.tradeVO.Right")
        @Accessors(chain = true)
        public static class Right implements Serializable{
            @ApiModelProperty("退款状态[提交申请:10,商家驳回:20,商家确认:30,退款:40,完成:50]")
            private Integer rightsState;

            @ApiModelProperty("退款原因")
            private String remark;

        }

        @Data
        @ApiModel("BbbTradeListVO.tradeVO.Cancel")
        @Accessors(chain = true)
        public static class Cancel implements Serializable{

            @ApiModelProperty("退款金额")
            private BigDecimal tradeAmount;


            @ApiModelProperty("提交申请:10,商家驳回:20,商家确认:30,退款:40,完成:50")
            private Integer cancelState;


            @ApiModelProperty("申请类型:用户取消订单:10,商家取消订单:20")
            private Integer applyType;


            @ApiModelProperty("申请时间")
            @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
            private LocalDateTime applyTime;


            @ApiModelProperty("拒绝理由")
            private String rejectReason;


            @ApiModelProperty("拒绝时间")
            @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
            private LocalDateTime rejectTime;


            @ApiModelProperty("通过时间")
            @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
            private LocalDateTime passTime;


            @ApiModelProperty("退款状态:无需退款:10,等待审核:20,等待退款:30,退款成功:40")
            private Integer refundState;


            @ApiModelProperty("退款时间")
            @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
            private LocalDateTime refundTime;

        }
    }

    @Data
    @ApiModel("BbbTradeListVO.TradeGoodsVO")
    @Accessors(chain = true)
    public static class TradeGoodsVO implements Serializable{

        @ApiModelProperty("交易商品ID")
        private String id;


        @ApiModelProperty("交易ID")
        private String tradeId;


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


        @ApiModelProperty("数量")
        private Integer quantity;


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

        @ApiModelProperty("是否允许评论[10=是 20=否]")
        private Integer commentFlag;

    }
    @Data
    @ApiModel("BbbTradeListVO.stateCount")
    public static class stateCountVO implements Serializable{

        @ApiModelProperty("交易状态")
        private Integer tradeState;

        @ApiModelProperty("交易数量")
        private Integer tradeCount;

    }
    @Data
    @ApiModel("BbbTradeListVO.OfflinePayVO")
    @Accessors(chain = true)
    public static class OfflinePayVO implements Serializable{
        @ApiModelProperty("账户名")
        private String name;

        @ApiModelProperty("银行账号")
        private String number;

        @ApiModelProperty("开户银行")
        private String bank;

        @ApiModelProperty("银联号")
        private String unionpay;

        @ApiModelProperty("订单号")
        private String trade_id;

        @ApiModelProperty("转账金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty(value = "付款用户名")
        private String payName;
        @ApiModelProperty(value = "付款卡号")
        private String payCardNum;
        @ApiModelProperty(value = "付款银行")
        private String payBlank;
        @ApiModelProperty(value = "转账金额")
        private BigDecimal transferAmount;
        @ApiModelProperty(value = "转账备注")
        private String transferRemarks;
        @ApiModelProperty(value = "凭证图片")
        private List<String> transImage;


    }
    @Data
    @ApiModel("BbbTradeListVO.UseCard")
    @Accessors(chain = true)
    public static class UseCard implements Serializable{

        @ApiModelProperty("用户拥有此优惠卷的ID")
        private String id;

        @ApiModelProperty("优惠卷id")
        private String cardId;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

        @ApiModelProperty("优惠卷名称")
        private String cardName;

        @ApiModelProperty("描述")
        private String cardDescribe;

        @ApiModelProperty("优惠卷前缀")
        private String cardPrefix;

        @ApiModelProperty("店铺名字")
        private String shopName;

        @ApiModelProperty("是否隐藏[10=隐藏 20=不隐藏]")
        private Integer isHide;

    }
    @Data
    @ApiModel("BbbTradeListVO.InnerGoodsCompareTo")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class InnerGoodsCompareTo implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;

        @ApiModelProperty("序号")
        private Integer idx;
    }

    @Data
    @ApiModel("BbbTradeListVO.InnerGoodsScore")
    @Accessors(chain = true)
    public static class InnerGoodsScore implements Serializable{
        @ApiModelProperty("商品描述分")
        private BigDecimal describeGrade;

        @ApiModelProperty("物流服务分")
        private BigDecimal deliveryGrade;

        @ApiModelProperty("服务态度分")
        private BigDecimal serviceGrade;

        @ApiModelProperty("商品评分")
        private BigDecimal goodsGrade;

        @ApiModelProperty("评论人数")
        private Integer commentCount;


    }


}
