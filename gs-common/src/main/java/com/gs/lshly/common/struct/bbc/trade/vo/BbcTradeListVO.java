package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.enums.TradeStateEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author oy
 * @since 2020-10-28
 */
public abstract class BbcTradeListVO implements Serializable {


    @Setter
    @ApiModel("BbcTradeListVO.tradeVO")
    @Accessors(chain = true)
    public static class tradeVO implements Serializable {

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


        @ApiModelProperty("退款状态")
        private Integer rightsState;

        @ApiModelProperty("交易状态")
        private Integer tradeState;
        
        @ApiModelProperty("交易状态")
        private String tradeStateText;

        @ApiModelProperty("商品来源类型：1:商城商品，2:积分商品")
        private Integer goodsSourceType;


        @ApiModelProperty("商品总金额（商城用）")
        private BigDecimal goodsAmount;


        @ApiModelProperty("商品总积分（积分商城用）")
        private BigDecimal goodsPointAmount;


        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;


        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;


        @ApiModelProperty("交易总金额（商城用）")
        private BigDecimal tradeAmount;


        @ApiModelProperty("实付积分（积分商城用）")
        private BigDecimal pointPriceActuallyPaid;


        @ApiModelProperty("实付现金（积分商城用）")
        private BigDecimal amountActuallyPaid;


        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty("支付截止时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payDeadline;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

		public String getShopLogo() {
			return shopLogo;
		}

		public String getShopManName() {
			return shopManName;
		}

		public String getShopManPhone() {
			return shopManPhone;
		}

		public BigDecimal getShopLongitude() {
			return shopLongitude;
		}

		public BigDecimal getShopLatitude() {
			return shopLatitude;
		}

		public String getShopFullAddres() {
			return shopFullAddres;
		}

		public String getMerchantId() {
			return merchantId;
		}

		public String getTradeCode() {
			return tradeCode;
		}

		public Integer getRightsState() {
			return rightsState;
		}

		public Integer getTradeState() {
			return tradeState;
		}

		public String getTradeStateText() {
			if(tradeState!=null){
				tradeStateText = TradeStateEnum.getRemarkByCode(tradeState);
			}
			return tradeStateText;
		}

		public Integer getGoodsSourceType() {
			return goodsSourceType;
		}

		public BigDecimal getGoodsAmount() {
			return goodsAmount;
		}

		public BigDecimal getGoodsPointAmount() {
			return goodsPointAmount;
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

		public BigDecimal getPointPriceActuallyPaid() {
			return pointPriceActuallyPaid;
		}

		public BigDecimal getAmountActuallyPaid() {
			return amountActuallyPaid;
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

    }

    @Data
    @ApiModel("BbcTradeListVO.TradeGoodsVO")
    @Accessors(chain = true)
    public static class TradeGoodsVO implements Serializable {

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

    }

    @Data
    @ApiModel("BbcTradeListVO.stateCount")
    public static class stateCountVO implements Serializable {

        @ApiModelProperty("交易状态")
        private Integer tradeState;

        @ApiModelProperty("交易数量")
        private Integer tradeCount;

    }

    @Data
    @ApiModel("BbcTradeListVO.PageData")
    public static class PageData implements Serializable {

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

        @ApiModelProperty("有效时间开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime validStartTime;

        @ApiModelProperty("有效时间结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime validEndTime;

    }

    @Data
    @ApiModel("BbcTradeListVO.UseCard")
    @Accessors(chain = true)
    public static class UseCard implements Serializable {

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
    @ApiModel("BbcTradeListVO.InnerGoodsCompareTo")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class InnerGoodsCompareTo implements Serializable {
        @ApiModelProperty("商品ID")
        private String id;

        @ApiModelProperty("序号")
        private Integer idx;
    }


}
