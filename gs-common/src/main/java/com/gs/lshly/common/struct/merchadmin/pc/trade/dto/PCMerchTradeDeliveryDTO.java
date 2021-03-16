package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeDeliveryDTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeDeliveryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "发货表(ID)",hidden = true)
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("物流公司id")
        private String logisticsId;

        @ApiModelProperty("物流公司名称")
        private String logisticsName;

        @ApiModelProperty("物流公司编号")
        private String logisticsCode;

        @ApiModelProperty("物流编号")
        private String logisticsNumber;

        @ApiModelProperty("操作员id")
        private String operatorId;

        @ApiModelProperty("操作员姓名")
        private String operatorName;

        @ApiModelProperty("发货时间")
        private LocalDateTime deliveryTime;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;
    }

    @Data
    @ApiModel("PCMerchTradeDeliveryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "发货表(ID)")
        private String id;
    }

    @Data
    @ApiModel("PCMerchTradeDeliveryDTO.deliveryDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class deliveryDTO extends BaseDTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("物流公司id")
        private String logisticsId;

        @ApiModelProperty("物流编号")
        private String logisticsNumber;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;

    }

    @Data
    @ApiModel("PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO")
    @AllArgsConstructor
    public static class takeGoodsCodeCheckDTO extends BaseDTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("自提码")
        private String takeGoodsCode;

    }


}
