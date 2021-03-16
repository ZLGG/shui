package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradeDeliveryDTO implements Serializable {

    @Data
    @ApiModel("TradeDeliveryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "发货表(ID)",hidden = true)
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("物流公司id")
        private String logisticsId;

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
    @ApiModel("TradeDeliveryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "发货表(ID)")
        private String id;
    }


}
