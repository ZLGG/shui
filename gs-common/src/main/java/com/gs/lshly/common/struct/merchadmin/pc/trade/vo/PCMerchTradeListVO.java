package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* @author oy
* @since 2020-10-28
*/
public abstract class PCMerchTradeListVO implements Serializable {


    @Setter
    @ApiModel("PCMerchTradeListVO.tradeVO")
    @Accessors(chain = true)
    public static class tradeVO implements Serializable{

        @ApiModelProperty(value = "交易订单号(ID)",position = 1)
        private String id;


        @ApiModelProperty(value = "会员ID",position = 2)
        private String userId;

        @ApiModelProperty(value = "会员名称",position = 3)
        private String userName;


        @ApiModelProperty(value = "店铺ID",position = 4)
        private String shopId;

        @ApiModelProperty(value = "店铺名称",position = 5)
        private String shopName;


        @ApiModelProperty(value = "商家ID",position = 6)
        private String merchantId;


        @ApiModelProperty(value = "交易编号",position = 7)
        private String tradeCode;


        @ApiModelProperty(value = "交易状态",position = 8)
        private Integer tradeState;


        @ApiModelProperty("交易状态内容")
        private String tradeStateText;


        @ApiModelProperty(value = "商品总金额",position = 9)
        private BigDecimal goodsAmount;


        @ApiModelProperty(value = "优惠金额",position = 10)
        private BigDecimal discountAmount;


        @ApiModelProperty(value = "运费金额",position = 11)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "付款总额",position = 12)
        private BigDecimal tradeAmount;

        @ApiModelProperty(value = "付款总积分",position = 12)
        private BigDecimal tradePointAmount;

        @ApiModelProperty(value = "下单时间",position = 13)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty(value = "支付时间",position = 14)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty(value = "支付截止时间",position = 15)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payDeadline;


        @ApiModelProperty(value = "收货时间",position = 16)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime recvTime;


        @ApiModelProperty(value = "支付类型",position = 17)
        private Integer payType;


        @ApiModelProperty(value = "配送类型",position = 18)
        private Integer deliveryType;

        @ApiModelProperty(value = "物流单号",position = 19)
        private String logisticsNumber;

        @ApiModelProperty(value = "物流公司代码",position = 20)
        private String logisticsCompanyCode;

        @ApiModelProperty(value = "物流公司名称",position = 21)
        private String logisticsCompanyName;


        @ApiModelProperty(value = "自提码",position = 22)
        private String takeGoodsCode;


        @ApiModelProperty(value = "自提码图片",position = 23)
        private String takeGoodsQrcode;


        @ApiModelProperty(value = "收货地址ID",position = 24)
        private String recvAddresId;


        @ApiModelProperty(value = "收件人姓名",position = 25)
        private String recvPersonName;


        @ApiModelProperty(value = "收件人电话",position = 26)
        private String recvPhone;


        @ApiModelProperty(value = "收货地址全文本",position = 27)
        private String recvFullAddres;


        @ApiModelProperty(value = "是否超时取消",position = 28)
        private Integer timeoutCancel;


        @ApiModelProperty(value = "买家留言",position = 29)
        private String buyerRemark;


        @ApiModelProperty(value = "发货备注",position = 30)
        private String deliveryRemark;

        @ApiModelProperty(value = "交易商品集合",position = 31)
        List<TradeGoodsVO> tradeGoodsVOS;

        @ApiModelProperty("售后信息")
        private PCMerchTradeListVO.tradeVO.Right rightsInfo;

        @ApiModelProperty("支付积分总数")
        private BigDecimal pointPriceActuallyPaid;

        @ApiModelProperty("付款总额")
        private BigDecimal amountActuallyPaid;

        @Data
        @ApiModel("PCMerchTradeListVO.tradeVO.Right")
        @Accessors(chain = true)
        public static class Right implements Serializable{
            @ApiModelProperty("退款状态[提交申请:10,商家驳回:20,商家确认:30,退款:40,完成:50]")
            private Integer rightsState;

            @ApiModelProperty("退款原因")
            private String remark;

        }

		public String getId() {
			return id;
		}

		public String getUserId() {
			return userId;
		}

		public String getUserName() {
			return userName;
		}

		public String getShopId() {
			return shopId;
		}

		public String getShopName() {
			return shopName;
		}

		public String getMerchantId() {
			return merchantId;
		}

		public String getTradeCode() {
			return tradeCode;
		}

		public Integer getTradeState() {
			return tradeState;
		}

		public String getTradeStateText() {
			return tradeStateText;
		}

		public BigDecimal getGoodsAmount() {
			return goodsAmount;
		}

		public BigDecimal getDiscountAmount() {
			return discountAmount;
		}

		public BigDecimal getDeliveryAmount() {
			return deliveryAmount;
		}

		public BigDecimal getTradeAmount() {
			return tradeAmount;
		}

		public BigDecimal getTradePointAmount() {
			return tradePointAmount;
		}

		public LocalDateTime getCreateTime() {
			return createTime;
		}

		public LocalDateTime getPayTime() {
			return payTime;
		}

		public LocalDateTime getPayDeadline() {
			return payDeadline;
		}

		public LocalDateTime getRecvTime() {
			return recvTime;
		}

		public Integer getPayType() {
			return payType;
		}

		public Integer getDeliveryType() {
			return deliveryType;
		}

		public String getLogisticsNumber() {
			return logisticsNumber;
		}

		public String getLogisticsCompanyCode() {
			return logisticsCompanyCode;
		}

		public String getLogisticsCompanyName() {
			return logisticsCompanyName;
		}

		public String getTakeGoodsCode() {
			return takeGoodsCode;
		}

		public String getTakeGoodsQrcode() {
			return takeGoodsQrcode;
		}

		public String getRecvAddresId() {
			return recvAddresId;
		}

		public String getRecvPersonName() {
			return recvPersonName;
		}

		public String getRecvPhone() {
			return recvPhone;
		}

		public String getRecvFullAddres() {
			if(StringUtils.isNotEmpty(recvFullAddres)&&recvFullAddres.equals("nullnullnull")){
				recvFullAddres = "空";
			}
			return recvFullAddres;
		}

		public Integer getTimeoutCancel() {
			return timeoutCancel;
		}

		public String getBuyerRemark() {
			return buyerRemark;
		}

		public String getDeliveryRemark() {
			return deliveryRemark;
		}

		public List<TradeGoodsVO> getTradeGoodsVOS() {
			return tradeGoodsVOS;
		}

		public PCMerchTradeListVO.tradeVO.Right getRightsInfo() {
			return rightsInfo;
		}

		public BigDecimal getPointPriceActuallyPaid() {
			return pointPriceActuallyPaid;
		}

		public BigDecimal getAmountActuallyPaid() {
			return amountActuallyPaid;
		}

        
    }
    @Data
    @ApiModel("PCMerchTradeListVO.tradeVOExport")
    @Accessors(chain = true)
    public static class tradeVOExport implements Serializable{

        @ApiModelProperty(value = "交易订单号(ID)",position = 1)
        private String id;


        @ApiModelProperty(value = "会员名称",position = 2)
        private String userName;


        @ApiModelProperty(value = "店铺名称",position = 3)
        private String shopName;


        @ApiModelProperty(value = "交易编号",position = 4)
        private String tradeCode;


        @ApiModelProperty(value = "交易状态",position = 5)
        private String tradeState;


        @ApiModelProperty(value = "商品总金额",position = 6)
        private BigDecimal goodsAmount;


        @ApiModelProperty(value = "优惠金额",position = 7)
        private BigDecimal discountAmount;


        @ApiModelProperty(value = "运费金额",position = 8)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "交易总金额",position = 9)
        private BigDecimal tradeAmount;


        @ApiModelProperty(value = "创建时间",position = 10)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;



        @ApiModelProperty(value = "支付类型",position = 11)
        private String payType;


        @ApiModelProperty(value = "配送类型",position = 12)
        private String  deliveryType;

        @ApiModelProperty(value = "自提码",position = 16)
        private String takeGoodsCode;


        @ApiModelProperty(value = "自提码图片",position = 17)
        private String takeGoodsQrcode;


        @ApiModelProperty(value = "收货地址ID",position = 18)
        private String recvAddresId;


        @ApiModelProperty(value = "收货人",position = 19)
        private String recvPersonName;


        @ApiModelProperty(value = "收货人电话",position = 20)
        private String recvPhone;


        @ApiModelProperty(value = "收货地址全文本",position = 21)
        private String recvFullAddres;


        @ApiModelProperty(value = "是否超时取消",position = 22)
        private Integer timeoutCancel;


        @ApiModelProperty(value = "买家留言",position = 23)
        private String buyerRemark;


        @ApiModelProperty(value = "发货备注",position = 24)
        private String deliveryRemark;

        @ApiModelProperty(value = "交易商品集合",position = 25)
        List<TradeGoodsVO> tradeGoodsVOS;

    }

    @Data
    @ApiModel("PCMerchTradeListVO.waitSendTradeExport")
    @Accessors(chain = true)
    public static class waitSendTradeExport implements Serializable{

        @ApiModelProperty(value = "订单编号",position = 1)
        private String tradeCode;


        @ApiModelProperty(value = "订单状态",position = 2)
        private String tradeState;


        @ApiModelProperty(value = "实付金额",position = 3)
        private BigDecimal amountActuallyPaid;


        @ApiModelProperty(value = "实付积分",position = 4)
        private String pointPriceActuallyPaid;


        @ApiModelProperty(value = "运费",position = 5)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "下单时间",position = 6)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

        @ApiModelProperty(value = "支付时间",position = 7)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty(value = "收件人姓名",position = 8)
        private String recvPersonName;


        @ApiModelProperty(value = "收件人电话",position = 9)
        private String recvPhone;

        @ApiModelProperty(value = "收件人地址",position = 10)
        private String recvFullAddres;

        @ApiModelProperty(value = "客户备注信息",position = 11)
        private String buyerRemark;
    }

    @Data
    @ApiModel("PCMerchTradeListVO.hasSentTradeExport")
    @Accessors(chain = true)
    public static class hasSentTradeExport implements Serializable{

        @ApiModelProperty(value = "订单编号",position = 1)
        private String tradeCode;


        @ApiModelProperty(value = "订单状态",position = 2)
        private String tradeState;


        @ApiModelProperty(value = "实付金额",position = 3)
        private BigDecimal amountActuallyPaid;


        @ApiModelProperty(value = "实付积分",position = 4)
        private String pointPriceActuallyPaid;


        @ApiModelProperty(value = "运费",position = 5)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "下单时间",position = 6)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

        @ApiModelProperty(value = "支付时间",position = 7)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty(value = "收件人姓名",position = 8)
        private String recvPersonName;


        @ApiModelProperty(value = "收件人电话",position = 9)
        private String recvPhone;

        @ApiModelProperty(value = "收件人地址",position = 10)
        private String recvFullAddres;

        @ApiModelProperty(value = "客户备注信息",position = 11)
        private String buyerRemark;

        @ApiModelProperty(value = "物流信息",position = 12)
        private String logisticsCompanyName;

        @ApiModelProperty(value = "快递单号",position = 13)
        private String logisticsNumber;

        @ApiModelProperty(value = "发货备注",position = 14)
        private String deliveryRemark;
    }

    @Data
    @ApiModel("PCMerchTradeListVO.innerGoodsIdAndName")
    @Accessors(chain = true)
    public static class innerGoodsIdAndName implements Serializable{
        @ApiModelProperty("商品ID")
        private String goodsId;


        @ApiModelProperty("商品名字")
        private String goodsName;
    }
    @Data
    @ApiModel("PCMerchTradeListVO.TradeGoodsVO")
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


        @ApiModelProperty("支付金额")
        private BigDecimal payAmount;

        @ApiModelProperty("支付积分")
        private BigDecimal tradePointAmount;

        @ApiModelProperty("所得积分")
        private BigDecimal giftIntegral;


        @ApiModelProperty("订单总价占比")
        private BigDecimal tradeAmountPercent;

        @ApiModelProperty("是否允许评论")
        private Integer commentFlag;

        @ApiModelProperty("售后状态")
        private Integer rightsState;

        @ApiModelProperty("售后状态内容")
        private String rightsStateText;

        @ApiModelProperty("售后类型")
        private Integer rightsType;

        @ApiModelProperty("售后类型内容")
        private String rightsTypeText;

        @ApiModelProperty("订单状态（主状态）")
        private Integer tradeState;

        @ApiModelProperty("订单状态内容（主状态）")
        private String tradeStateText;
        
        @ApiModelProperty("是否是积分商品")
        private Integer isPointGood;
        
        @ApiModelProperty("兑换类型（20实物，10虚拟）")
        private Integer exchangeType;


    }
    @Data
    @ApiModel("PCMerchTradeListVO.stateCount")
    public static class stateCountVO implements Serializable{

        @ApiModelProperty("交易状态")
        private Integer tradeState;

        @ApiModelProperty("交易数量")
        private Integer tradeCount;

    }


}
