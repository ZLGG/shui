package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.gs.lshly.common.enums.GoodsCouponStatusEnum;
import com.gs.lshly.common.struct.common.vo.CommonMarketVO;
import com.gs.lshly.common.utils.StringManageUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author oy
 * @since 2020-10-28
 */
@SuppressWarnings("serial")
public abstract class BbcTradeSettlementVO implements Serializable {

	/**
	 * 购物车店铺列表
	 *
	 * 
	 * @author yingjun
	 * @date 2021年5月17日 下午4:01:25
	 */
	@Setter
	@ApiModel("BbcTradeSettlementVO.ShopListVO")
	@Accessors(chain = true)
	public static class ShopListVO implements Serializable {

		// 商家
		@ApiModelProperty("配送类型")
		private List<String> shopDeliveryType;

		@ApiModelProperty("店铺ID")
		private String shopId;

		@ApiModelProperty("店铺Logo")
		private String shopLogo;

		@ApiModelProperty("店铺名称")
		private String shopName;

		@ApiModelProperty("门店配送范围")
		private BigDecimal deliveryScope;

		@ApiModelProperty("省")
		private String shopProvince;

		@ApiModelProperty("市")
		private String shopCity;

		@ApiModelProperty("县区")
		private String shopCounty;

		@ApiModelProperty("街道")
		private String shopStreet;

		@ApiModelProperty("经度")
		private BigDecimal shopLongitude;

		@ApiModelProperty("纬度")
		private BigDecimal shopLatitude;

		@ApiModelProperty("店铺地址")
		private String shopAddress;

		@ApiModelProperty("店铺地址全文本")
		private String shopFullAddres;

		// 商品总金额、商品数量、运费
		@ApiModelProperty("商品总金额")
		private BigDecimal goodsAmount;

		@ApiModelProperty("商品总积分金额")
		private BigDecimal goodsPointAmount;
		/**
		 * @ApiModelProperty("运费金额") private BigDecimal deliveryAmount;
		 **/
		@ApiModelProperty("交易总金额")
		private BigDecimal tradeAmount;

		@ApiModelProperty("交易总积分金额")
		private BigDecimal tradePointAmount;

		@ApiModelProperty("商品合计数量")
		private Integer goodsCount;

		// 商品（图片、名称、规格、单价）
		@ApiModelProperty(value = "商品集合")
		private List<BbcTradeSettlementVO.ShopListVO.goodsInfoVO> goodsInfoVOS;

		/**
		 * @ApiModelProperty(value = "营销活动") private CommonMarketVO.ActiveVO
		 *                         marketActiveVO;
		 * 
		 * @ApiModelProperty(value = "优惠券") private CommonMarketVO.UserCardVO
		 *                         userCardVO;
		 **/

		@Setter
		@ApiModel("BbcTradeSettlementVO.goodsInfoVO")
		@ToString
		public static class goodsInfoVO implements Serializable {

			@ApiModelProperty("购物车ID")
			private String cartId;

			@ApiModelProperty("商品ID")
			private String goodsId;

			@ApiModelProperty("商品名称")
			private String goodsName;

			@ApiModelProperty("商品标题")
			private String goodsTitle;

			@ApiModelProperty("SKU ID")
			private String skuId;

			@ApiModelProperty("格规值")
			private String skuSpecValue;

			@ApiModelProperty("SKU IMG")
			private String skuImg;

			@ApiModelProperty("数量")
			private Integer quantity;

			@ApiModelProperty("是否是积分商品")
			private Boolean isPointGood;

			@ApiModelProperty("是否是in会员礼品")
			private Boolean isInMemberGift;

			@ApiModelProperty("销售价")
			private BigDecimal salePrice;

			@ApiModelProperty("活动及券后价")
			private BigDecimal activePrice;

			@ApiModelProperty("积分价格")
			private BigDecimal pointPrice;

			@ApiModelProperty("in会员积分价格")
			private BigDecimal inMemberPointPrice;

			@ApiModelProperty("应付金额")
			private BigDecimal tradeAmount;

			@ApiModelProperty("应付积分金额")
			private BigDecimal tradePointAmount;
			
			@ApiModelProperty("默认优惠券列表")
			private List<ListCouponVO> defaultCouponList;
			
			@ApiModelProperty("用户可选优惠券列表")
			private List<ListCouponVO> optionalCouponList;

			public String getCartId() {
				return cartId;
			}

			public String getGoodsId() {
				return goodsId;
			}

			public String getGoodsName() {
				return goodsName;
			}

			public String getGoodsTitle() {
				return goodsTitle;
			}

			public String getSkuId() {
				return skuId;
			}

			public String getSkuSpecValue() {
				return skuSpecValue;
			}

			public String getSkuImg() {
				return skuImg;
			}

			public Integer getQuantity() {
				return quantity;
			}

			public Boolean getIsPointGood() {
				return isPointGood;
			}

			public Boolean getIsInMemberGift() {
				return isInMemberGift;
			}

			public BigDecimal getSalePrice() {
				if(salePrice!=null)
					salePrice = StringManageUtil.formatBigDecimal2(salePrice);
				return salePrice;
			}

			public BigDecimal getActivePrice() {
				if(activePrice!=null)
					activePrice = StringManageUtil.formatBigDecimal2(activePrice);
				return activePrice;
			}

			public BigDecimal getPointPrice() {
				if(pointPrice!=null)
					pointPrice = StringManageUtil.formatBigDecimal2(pointPrice);
				return pointPrice;
			}

			public BigDecimal getInMemberPointPrice() {
				return inMemberPointPrice;
			}

			public BigDecimal getTradeAmount() {
				if(tradeAmount!=null)
					tradeAmount = StringManageUtil.formatBigDecimal2(tradeAmount);
				return tradeAmount;
			}

			public BigDecimal getTradePointAmount() {
				return tradePointAmount;
			}

			public List<ListCouponVO> getDefaultCouponList() {
				return defaultCouponList;
			}

			public List<ListCouponVO> getOptionalCouponList() {
				return optionalCouponList;
			}
		}

		public List<String> getShopDeliveryType() {
			return shopDeliveryType;
		}

		public String getShopId() {
			return shopId;
		}

		public String getShopLogo() {
			return shopLogo;
		}

		public String getShopName() {
			return shopName;
		}

		public BigDecimal getDeliveryScope() {
			return deliveryScope;
		}

		public String getShopProvince() {
			return shopProvince;
		}

		public String getShopCity() {
			return shopCity;
		}

		public String getShopCounty() {
			return shopCounty;
		}

		public String getShopStreet() {
			return shopStreet;
		}

		public BigDecimal getShopLongitude() {
			return shopLongitude;
		}

		public BigDecimal getShopLatitude() {
			return shopLatitude;
		}

		public String getShopAddress() {
			return shopAddress;
		}

		public String getShopFullAddres() {
			return shopFullAddres;
		}

		public BigDecimal getGoodsAmount() {
			if(goodsAmount!=null)
				goodsAmount = StringManageUtil.formatBigDecimal2(goodsAmount);
			return goodsAmount;
		}

		public BigDecimal getGoodsPointAmount() {
			return goodsPointAmount;
		}

		public BigDecimal getTradeAmount() {
			if(tradeAmount!=null)
				tradeAmount = StringManageUtil.formatBigDecimal2(tradeAmount);
			return tradeAmount;
		}

		public BigDecimal getTradePointAmount() {
			return tradePointAmount;
		}

		public Integer getGoodsCount() {
			return goodsCount;
		}

		public List<BbcTradeSettlementVO.ShopListVO.goodsInfoVO> getGoodsInfoVOS() {
			return goodsInfoVOS;
		}

	}

	@Setter
	@ApiModel("BbcTradeSettlementVO.DetailVO")
	@Accessors(chain = true)
	public static class DetailVO implements Serializable {
		// 返回地址
		@ApiModelProperty("虚拟商品还是实物商品")
		private Integer exchangeType;

		@ApiModelProperty("收货地址ID")
		private String recvAddresId;

		@ApiModelProperty("收货人")
		private String recvPersonName;

		@ApiModelProperty("收货人电话")
		private String recvPhone;

		@ApiModelProperty("收货地址全文本")
		private String recvFullAddres;

		@ApiModelProperty("联系人")
		private String contactsName;

		@ApiModelProperty("配送类型")
		private List<String> shopDeliveryType;

		@ApiModelProperty("联系人电话")
		private String contactsPhone;

		@ApiModelProperty("商家列表")
		private List<ShopListVO> shopList;

		// 商品总金额、商品数量、运费
		@ApiModelProperty("商品总金额")
		private BigDecimal goodsAmount;

		@ApiModelProperty("商品总积分金额")
		private BigDecimal goodsPointAmount;

		@ApiModelProperty("交易总金额")
		private BigDecimal tradeAmount;

		@ApiModelProperty("交易总积分金额")
		private BigDecimal tradePointAmount;

		@ApiModelProperty("商品合计数量")
		private Integer goodsCount;

		@ApiModelProperty("用户电信积分")
		private Integer telecomsIntegral;

		@ApiModelProperty("优惠券抵扣金额")
		private BigDecimal discountAmount;

		@ApiModelProperty("优惠券抵扣积分")
		private BigDecimal discountPointAmount;
		

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

		public String getContactsName() {
			return contactsName;
		}

		public List<String> getShopDeliveryType() {
			return shopDeliveryType;
		}

		public String getContactsPhone() {
			return contactsPhone;
		}

		public List<ShopListVO> getShopList() {
			return shopList;
		}

		public BigDecimal getGoodsAmount() {
			if (goodsAmount != null)
				goodsAmount = StringManageUtil.formatBigDecimal2(goodsAmount);
			return goodsAmount;
		}

		public BigDecimal getGoodsPointAmount() {
			return goodsPointAmount;
		}

		public BigDecimal getTradeAmount() {
			if (tradeAmount != null)
				tradeAmount = StringManageUtil.formatBigDecimal2(tradeAmount);
			return tradeAmount;
		}

		public BigDecimal getTradePointAmount() {
			return tradePointAmount;
		}

		public Integer getGoodsCount() {
			return goodsCount;
		}

		public Integer getTelecomsIntegral() {
			return telecomsIntegral;
		}

		public BigDecimal getDiscountAmount() {
			if (discountAmount != null)
				discountAmount = StringManageUtil.formatBigDecimal2(discountAmount);
			return discountAmount;
		}

		public BigDecimal getDiscountPointAmount() {
			return discountPointAmount;
		}
		
	}

	@Setter
	@ApiModel("BbcTradeSettlementVO.ListCouponVO")
	public static class ListCouponVO implements Serializable {
		
		@ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
	    private Integer couponType;
		
		@ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
	    private Integer couponTypeText;
 
		@ApiModelProperty("优惠券名称")
	    private String couponName;

		@ApiModelProperty("可使用时间")
	    private String useTime;

		@ApiModelProperty("减免金额")
	    private BigDecimal deduction;

		@ApiModelProperty("使用门槛")
	    private BigDecimal useThreshold;
		
		@ApiModelProperty("减免类型 1：积分  2：金额")
	    private Integer deductionType;

		@ApiModelProperty("优惠券ID")
		private String id;

		public Integer getCouponType() {
			return couponType;
		}

		public Integer getCouponTypeText() {
			return couponTypeText;
		}

		public String getCouponName() {
			return couponName;
		}

		public String getUseTime() {
			return useTime;
		}

		public BigDecimal getDeduction() {
			return deduction;
		}

		public BigDecimal getUseThreshold() {
			return useThreshold;
		}

		public Integer getDeductionType() {
			return deductionType;
		}

		public String getId() {
			return id;
		}
		
	}
	
	
	@Data
	@ApiModel("BbcTradeSettlementVO.ListVO")
	@Accessors(chain = true)
	public static class ListVO implements Serializable {
		// 返回地址

		@ApiModelProperty("收货地址ID")
		private String recvAddresId;

		@ApiModelProperty("收货人")
		private String recvPersonName;

		@ApiModelProperty("收货人电话")
		private String recvPhone;

		@ApiModelProperty("收货地址全文本")
		private String recvFullAddres;

		@ApiModelProperty("联系人")
		private String contactsName;

		@ApiModelProperty("配送类型")
		private List<String> shopDeliveryType;

		@ApiModelProperty("联系人电话")
		private String contactsPhone;

		// 商家
		@ApiModelProperty("店铺ID")
		private String shopId;

		@ApiModelProperty("店铺Logo")
		private String shopLogo;

		@ApiModelProperty("店铺名称")
		private String shopName;

		@ApiModelProperty("门店配送范围")
		private BigDecimal deliveryScope;

		@ApiModelProperty("省")
		private String shopProvince;

		@ApiModelProperty("市")
		private String shopCity;

		@ApiModelProperty("县区")
		private String shopCounty;

		@ApiModelProperty("街道")
		private String shopStreet;

		@ApiModelProperty("经度")
		private BigDecimal shopLongitude;

		@ApiModelProperty("纬度")
		private BigDecimal shopLatitude;

		@ApiModelProperty("店铺地址")
		private String shopAddress;

		@ApiModelProperty("店铺地址全文本")
		private String shopFullAddres;
		// 商品总金额、商品数量、运费
		@ApiModelProperty("商品总金额")
		private BigDecimal goodsAmount;

		@ApiModelProperty("商品总积分金额")
		private BigDecimal goodsPointAmount;

		@ApiModelProperty("运费金额")
		private BigDecimal deliveryAmount;

		@ApiModelProperty("交易总金额")
		private BigDecimal tradeAmount;

		@ApiModelProperty("交易总积分金额")
		private BigDecimal tradePointAmount;

		@ApiModelProperty("商品合计数量")
		private Integer goodsCount;

		// 商品（图片、名称、规格、单价）
		@ApiModelProperty(value = "商品集合")
		private List<BbcTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS;

		@ApiModelProperty(value = "营销活动")
		private CommonMarketVO.ActiveVO marketActiveVO;

		@ApiModelProperty(value = "优惠券")
		private CommonMarketVO.UserCardVO userCardVO;

		@ApiModelProperty("用户电信积分")
		private Integer telecomsIntegral;

		@Data
		@ApiModel("BbcTradeSettlementVO.goodsInfoVO")
		@ToString
		public static class goodsInfoVO implements Serializable {

			@ApiModelProperty("购物车ID")
			private String cartId;

			@ApiModelProperty("商品ID")
			private String goodsId;

			@ApiModelProperty("商品名称")
			private String goodsName;

			@ApiModelProperty("商品标题")
			private String goodsTitle;

			@ApiModelProperty("SKU ID")
			private String skuId;

			@ApiModelProperty("格规值")
			private String skuSpecValue;

			@ApiModelProperty("SKU IMG")
			private String skuImg;

			@ApiModelProperty("数量")
			private Integer quantity;

			@ApiModelProperty("是否是积分商品")
			private Boolean isPointGood;

			@ApiModelProperty("销售价")
			private BigDecimal salePrice;

			@ApiModelProperty("活动及券后价")
			private BigDecimal activePrice;

			@ApiModelProperty("积分价格")
			private BigDecimal pointPrice;

			@ApiModelProperty("是否是in会员礼品")
			private Boolean isInMemberGift;

			@ApiModelProperty("in会员积分价格")
			private BigDecimal inMemberPointPrice;

		}
	}
}
