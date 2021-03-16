package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeDeliveryQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeDeliveryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
}
