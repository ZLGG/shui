package com.gs.lshly.common.struct.bbc.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsRecordQTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsRecordQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("售后表ID")
        private String rightsId;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("当前状态")
        private Integer state;

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("说明")
        private String remark;

        @ApiModelProperty("用户ID")
        private String userId;

        @ApiModelProperty("用户名")
        private String userName;
    }
}
