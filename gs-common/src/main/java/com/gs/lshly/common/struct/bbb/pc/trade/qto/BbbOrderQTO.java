package com.gs.lshly.common.struct.bbb.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zhaoyan
* @since 2020-10-15
*/
public abstract class BbbOrderQTO implements Serializable {

    @Data
    @ApiModel("BBB.OrderQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
    @ApiModel("BbbOrderQTO.TradeList")
    @Accessors(chain = true)
    public static class TradeList extends BaseQTO {


        @ApiModelProperty("交易状态(TradeStateEnum)")
        private Integer tradeState;

        @ApiModelProperty("关键字")
        private String keyword;

    }
}
