package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonMarketVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
* @author oy
* @since 2020-10-28
*/
@SuppressWarnings("serial")
public abstract class BbcTradeBuildDTO implements Serializable {
	
	@Data
	@EqualsAndHashCode(callSuper=false)
	@ApiModel(value="BbcTradeDTO.SingtonDTO") 
	public static class SingtonDTO  extends BaseDTO implements Serializable {
		@ApiModelProperty(value = "支付类型")
        private Integer payType;

        @ApiModelProperty(value = "配送方式")
        private Integer deliveryType;

        @ApiModelProperty(value = "运费金额", hidden = true)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "业务来源端", hidden = true)
        private ActivityTerminalEnum terminal;

        @ApiModelProperty(value = "订单支付金额", hidden = true)
        BigDecimal shopProductAmount;

        @ApiModelProperty(value = "积分商品，用户分配的现金支付金额")
        private BigDecimal allocatedCashAmount;

        @ApiModelProperty(value = "积分商品，用户分配的积分支付金额")
        private BigDecimal allocatedPointAmount;

        @ApiModelProperty("是否需要发票")
        private Boolean isInvoice;

        @ApiModelProperty(value = "营销活动", hidden = true)
        private CommonMarketVO.ActiveVO marketActiveVO;

        @ApiModelProperty(value = "优惠券", hidden = true)
        private CommonMarketVO.UserCardVO userCardVO;
    	
    	@ApiModelProperty(value = "买家留言")
        private String buyerRemark;
    	
		@ApiModelProperty(value = "店铺ID")
		private String shopId;
		
		@ApiModelProperty(value="商户ID")
		private String merchantId;

		@ApiModelProperty(value = "商品信息")
		private List<ProductData> productData;
		
		@Data
        public static class ProductData implements Serializable {
            @ApiModelProperty(value = "购物车ID")
            private String cartId;

            @ApiModelProperty(value = "商品Id")
            private String goodsId;

            @ApiModelProperty(value = "sku商品Id")
            private String goodsSkuId;

            @ApiModelProperty(value = "购买数量")
            private Integer quantity;
            
            @ApiModelProperty(value = "分配积分金额")
            private Integer pointAmount;

        }
	}
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel(value = "BbcTradeDTO.TradeBuildDTO")
    @Accessors(chain = true)
    public static class DTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "收货地址ID")
        private String addressId;

        @ApiModelProperty(value = "联系人")
        private String contactsName;

        @ApiModelProperty(value = "联系人电话")
        private String contactsPhone;

        @ApiModelProperty(value = "店铺信息")
		private List<ShopData> shopData;
       
        @ApiModelProperty(value = "业务来源端", hidden = true)
        private ActivityTerminalEnum terminal;
        
        @ApiModelProperty(value = "配送方式",hidden=true)
        private Integer deliveryType = 10;
        
        @Data
        public static class ShopData implements Serializable {
        	
        	/**
        	@ApiModelProperty(value = "支付类型",hidden=true)
            private Integer payType;



            @ApiModelProperty(value = "运费金额", hidden = true)
            private BigDecimal deliveryAmount;


            @ApiModelProperty(value = "订单支付金额", hidden = true)
            BigDecimal shopProductAmount;

            @ApiModelProperty(value = "积分商品，用户分配的现金支付金额")
            private BigDecimal allocatedCashAmount;

            @ApiModelProperty(value = "积分商品，用户分配的积分支付金额")
            private BigDecimal allocatedPointAmount;

            @ApiModelProperty("是否需要发票")
            private Boolean isInvoice;

            @ApiModelProperty(value = "营销活动", hidden = true)
            private CommonMarketVO.ActiveVO marketActiveVO;

            @ApiModelProperty(value = "优惠券", hidden = true)
            private CommonMarketVO.UserCardVO userCardVO;
        	**/
        	@ApiModelProperty(value = "买家留言")
            private String buyerRemark;
        	
			@ApiModelProperty(value = "店铺ID")
			private String shopId;
			
			@ApiModelProperty(value = "店铺名称",hidden=true)
			private String shopName;
			
			@ApiModelProperty(value = "商品信息")
			private List<ProductData> productData;
        }

		@Data
        public static class ProductData implements Serializable {
            @ApiModelProperty(value = "购物车ID")
            private String cartId;

            @ApiModelProperty(value = "商品Id")
            private String goodsId;

            @ApiModelProperty(value = "sku商品Id")
            private String goodsSkuId;

            @ApiModelProperty(value = "购买数量")
            private Integer quantity;
            
            @ApiModelProperty(value = "分配积分金额")
            private BigDecimal pointAmount;

        }

    }
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradeDTO.cartIdsDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class cartIdsDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组（购物车ID数组和sku商品Id二选一填写，分别代表购物车和立即购买）")
        private List<String> cartIds;

        @ApiModelProperty(value = "配送类型")
        private Integer deliveryType;

        @ApiModelProperty(value = "sku商品Id（购物车ID数组和sku商品Id二选一填写，分别代表购物车和立即购买）")
        private String goodsSkuId;

        @ApiModelProperty(value = "购买数量")
        private Integer quantity;

        @ApiModelProperty(value = "收货地址ID")
        private String addressId;

        @ApiModelProperty(value = "业务来源端", hidden = true)
        private ActivityTerminalEnum terminal;
    }
}
