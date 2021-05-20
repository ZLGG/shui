package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
* @author oy
* @since 2020-10-28
*/
@SuppressWarnings("serial")
public abstract class BbcTradeDTO implements Serializable {
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易订单号(ID)",hidden = true)
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;

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

        @ApiModelProperty("收货时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime recvTime;

        @ApiModelProperty("支付类型")
        private Integer payType;

        @ApiModelProperty("配送类型")
        private Integer deliveryType;

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

        @ApiModelProperty("是否隐藏订单:1:是")
        private Integer isHide;
    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradeDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易订单号(ID)")
        private String id;
    }
    
	@Data
    @ApiModel("BbcTradeDTO.ListIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListIdDTO implements Serializable {

        @ApiModelProperty(value = "交易订单号(ID)")
        private List<String> ids;
        
        @ApiModelProperty(value ="支付现金金额")
        private BigDecimal tradeAmount;
        
        @ApiModelProperty(value ="支付积分金额")
        private BigDecimal tradePointAmount;
        
    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradeDTO.innerCommpareTo")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class innerCommpareTo extends BaseDTO {

        @ApiModelProperty("商品id")
        private List<String> goodsIds;
        @ApiModelProperty("排序类型[10=评分 20=销售量]")
        private Integer compareToType;
        @ApiModelProperty("排序方式[10=升序 20=降序]")
        private Integer compareToMode;
    }
    @EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradeDTO.UseCard")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UseCard extends BaseDTO {
        @ApiModelProperty("商品信息")
        private List<BbcTradeDTO.UseCardGoods> goodsInfo;

        @ApiModelProperty("店铺ID")
        private String shopId;

    }
    @Data
    @ApiModel("BbcTradeDTO.UseCardGoods")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UseCardGoods implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;
        @ApiModelProperty("商品SKUid")
        private String goodsSkuId;
        @ApiModelProperty("商品总价（单价*数量）值")
        private BigDecimal totalAmount;
    }
    @EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcTradeDTO.OfflinePayDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OfflinePayDTO extends BaseDTO {

        @ApiModelProperty(value = "订单号(ID)")
        private String id;
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
    @ApiModel("BbcStockAddressDTO.ModifyOrderAddressDTO")
    public static class ModifyOrderAddressDTO implements Serializable{
        @ApiModelProperty("订单id")
        @NotBlank
        private String id;

        @ApiModelProperty("收货地址id")
        @NotBlank
        private String recvAddressId;

        @ApiModelProperty("收货人姓名")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("收货人新地址")
        @NotBlank
        private String recvFullAddress;
    }


}
