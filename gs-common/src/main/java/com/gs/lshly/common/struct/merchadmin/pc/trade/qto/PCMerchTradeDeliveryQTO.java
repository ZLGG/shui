package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeDeliveryQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeDeliveryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("物流编号")
        private String logisticsNumber;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("用户手机号")
        private String userPhone;

        @ApiModelProperty("收货地址")
        private String address;

        @ApiModelProperty("收货人姓名")
        private String name;

        @ApiModelProperty("收货人手机号")
        private String phone;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;


    }
    @Data
    @ApiModel("PCMerchTradeDeliveryQTO.IdListQTO")
    public static class IdListQTO implements Serializable{

        @ApiModelProperty("发货单ID列表")
        private List<String> idList;

    }
}
