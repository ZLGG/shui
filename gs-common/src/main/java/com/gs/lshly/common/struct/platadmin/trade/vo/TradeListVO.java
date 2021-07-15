package com.gs.lshly.common.struct.platadmin.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* @author oy
* @since 2020-10-28
*/
public abstract class TradeListVO implements Serializable {


    @Setter
    @ApiModel("TradeListVO.tradeVO")
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
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty("支付截止时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payDeadline;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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


        @ApiModelProperty("买家留言")
        private String buyerRemark;


        @ApiModelProperty("发货备注")
        private String deliveryRemark;

        @ApiModelProperty("交易商品集合")
        List<TradeGoodsVO> tradeGoodsVOS;

        @ApiModelProperty("平台取消订单处理说明")
        private String cancelReason;

		@ApiModelProperty("业务号码")
		private String businessPhone;

		@ApiModelProperty("客户编号")
		private String customerID;

		public String getCustomerID() {
			return customerID;
		}

		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}

		public String getBusinessPhone() {
			return businessPhone;
		}

		public void setBusinessPhone(String businessPhone) {
			this.businessPhone = businessPhone;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getShopId() {
			return shopId;
		}

		public void setShopId(String shopId) {
			this.shopId = shopId;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getMerchantId() {
			return merchantId;
		}

		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}

		public String getTradeCode() {
			return tradeCode;
		}

		public void setTradeCode(String tradeCode) {
			this.tradeCode = tradeCode;
		}

		public Integer getTradeState() {
			return tradeState;
		}

		public void setTradeState(Integer tradeState) {
			this.tradeState = tradeState;
		}

		public BigDecimal getGoodsAmount() {
			return goodsAmount;
		}

		public void setGoodsAmount(BigDecimal goodsAmount) {
			this.goodsAmount = goodsAmount;
		}

		public BigDecimal getDiscountAmount() {
			return discountAmount;
		}

		public void setDiscountAmount(BigDecimal discountAmount) {
			this.discountAmount = discountAmount;
		}

		public BigDecimal getDeliveryAmount() {
			return deliveryAmount;
		}

		public void setDeliveryAmount(BigDecimal deliveryAmount) {
			this.deliveryAmount = deliveryAmount;
		}

		public BigDecimal getTradeAmount() {
			return tradeAmount;
		}

		public void setTradeAmount(BigDecimal tradeAmount) {
			this.tradeAmount = tradeAmount;
		}

		public LocalDateTime getCreateTime() {
			return createTime;
		}

		public void setCreateTime(LocalDateTime createTime) {
			this.createTime = createTime;
		}

		public LocalDateTime getPayTime() {
			return payTime;
		}

		public void setPayTime(LocalDateTime payTime) {
			this.payTime = payTime;
		}

		public LocalDateTime getPayDeadline() {
			return payDeadline;
		}

		public void setPayDeadline(LocalDateTime payDeadline) {
			this.payDeadline = payDeadline;
		}

		public LocalDateTime getRecvTime() {
			return recvTime;
		}

		public void setRecvTime(LocalDateTime recvTime) {
			this.recvTime = recvTime;
		}

		public Integer getPayType() {
			return payType;
		}

		public void setPayType(Integer payType) {
			this.payType = payType;
		}

		public Integer getDeliveryType() {
			return deliveryType;
		}

		public void setDeliveryType(Integer deliveryType) {
			this.deliveryType = deliveryType;
		}

		public String getLogisticsNumber() {
			return logisticsNumber;
		}

		public void setLogisticsNumber(String logisticsNumber) {
			this.logisticsNumber = logisticsNumber;
		}

		public String getLogisticsCompanyCode() {
			return logisticsCompanyCode;
		}

		public void setLogisticsCompanyCode(String logisticsCompanyCode) {
			this.logisticsCompanyCode = logisticsCompanyCode;
		}

		public String getLogisticsCompanyName() {
			return logisticsCompanyName;
		}

		public void setLogisticsCompanyName(String logisticsCompanyName) {
			this.logisticsCompanyName = logisticsCompanyName;
		}

		public String getTakeGoodsCode() {
			return takeGoodsCode;
		}

		public void setTakeGoodsCode(String takeGoodsCode) {
			this.takeGoodsCode = takeGoodsCode;
		}

		public String getTakeGoodsQrcode() {
			return takeGoodsQrcode;
		}

		public void setTakeGoodsQrcode(String takeGoodsQrcode) {
			this.takeGoodsQrcode = takeGoodsQrcode;
		}

		public String getRecvAddresId() {
			return recvAddresId;
		}

		public void setRecvAddresId(String recvAddresId) {
			this.recvAddresId = recvAddresId;
		}

		public String getRecvPersonName() {
			return recvPersonName;
		}

		public void setRecvPersonName(String recvPersonName) {
			this.recvPersonName = recvPersonName;
		}

		public String getRecvPhone() {
			return recvPhone;
		}

		public void setRecvPhone(String recvPhone) {
			this.recvPhone = recvPhone;
		}

		public String getRecvFullAddres() {
			if(StringUtils.isNotEmpty(recvFullAddres)&&recvFullAddres.equals("nullnullnull")){
				recvFullAddres = "空";
			}
			return recvFullAddres;
		}

		public void setRecvFullAddres(String recvFullAddres) {
			this.recvFullAddres = recvFullAddres;
		}

		public Integer getTimeoutCancel() {
			return timeoutCancel;
		}

		public void setTimeoutCancel(Integer timeoutCancel) {
			this.timeoutCancel = timeoutCancel;
		}

		public String getBuyerRemark() {
			return buyerRemark;
		}

		public void setBuyerRemark(String buyerRemark) {
			this.buyerRemark = buyerRemark;
		}

		public String getDeliveryRemark() {
			return deliveryRemark;
		}

		public void setDeliveryRemark(String deliveryRemark) {
			this.deliveryRemark = deliveryRemark;
		}

		public List<TradeGoodsVO> getTradeGoodsVOS() {
			return tradeGoodsVOS;
		}

		public void setTradeGoodsVOS(List<TradeGoodsVO> tradeGoodsVOS) {
			this.tradeGoodsVOS = tradeGoodsVOS;
		}

		public String getCancelReason() {
			return cancelReason;
		}

		public void setCancelReason(String cancelReason) {
			this.cancelReason = cancelReason;
		}


    }

    @Data
    @ApiModel("TradeListVO.TradeGoodsVO")
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

        @ApiModelProperty("是否允许评论")
        private Integer commentFlag;

        @ApiModelProperty("交易积分价格")
        private BigDecimal tradePointAmount;

        @ApiModelProperty("交易价格")
        private BigDecimal tradeAmount;

        @ApiModelProperty("优惠积分价格")
        private BigDecimal discountPointAmount;

    }
    @Data
    @ApiModel("TradeListVO.stateCount")
    public static class stateCountVO implements Serializable{

        @ApiModelProperty("交易状态")
        private Integer tradeState;

        @ApiModelProperty("交易数量")
        private Integer tradeCount;

    }
    @Data
    @ApiModel("TradeListVO.TradeRightsGoodsDetailViewVO")
    public static class TradeRightsGoodsDetailViewVO implements Serializable {
        @ApiModelProperty("订单号")
        private String tradeCode;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品数量")
        private Integer quantity;

        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;

        @ApiModelProperty("商品单价")
        private BigDecimal goodsOnePrice;
    }
}
