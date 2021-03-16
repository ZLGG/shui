package com.gs.lshly.common.struct.bbb.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zhaoyan
* @since 2020-10-15
*/
public abstract class BbbOrderDTO implements Serializable {

    @Data
    @ApiModel("BBB.OrderDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "订单号(ID)",hidden = true)
        private String id;

        @ApiModelProperty("订单状态")
        private Integer orderState;

        @ApiModelProperty("创建时间")
        private LocalDateTime orderCreateTime;

        @ApiModelProperty("支付时间")
        private LocalDateTime payTime;

        @ApiModelProperty("支付类型")
        private String payType;

        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;

        @ApiModelProperty("收货地址ID")
        private Integer recvAddresId;

        @ApiModelProperty("收货人")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("收货时间")
        private LocalDateTime recvTime;

        @ApiModelProperty("取消订单原因")
        private String cancelWhy;

        @ApiModelProperty("发货物流号")
        private String logisticsCode;

        @ApiModelProperty("发货时间")
        private String sendTime;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("BbbOrderDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "订单号(ID)")
        private String id;
    }
    @Data
    @ApiModel("BbbOrderDTO.OfflinePayDTO")
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
    @ApiModel("BbbOrderDTO.StateDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StateDTO extends BaseDTO {

        @ApiModelProperty(value = "20=近期订单 10=待付款 30=待收货 40=待评价")
        private Integer state;
    }
    @Data
    @ApiModel("BbbOrderDTO.UseCard")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UseCard extends BaseDTO {
        @ApiModelProperty("商品信息")
        private List<BbbOrderDTO.UseCardGoods> goodsInfo;

        @ApiModelProperty("店铺ID")
        private String shopId;

    }
    @Data
    @ApiModel("BbbOrderDTO.UseCardGoods")
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
    @Data
    @ApiModel("BbbOrderDTO.innerCommpareTo")
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


}
