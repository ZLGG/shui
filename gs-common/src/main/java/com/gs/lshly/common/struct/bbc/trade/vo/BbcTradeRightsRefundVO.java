package com.gs.lshly.common.struct.bbc.trade.vo;

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
public abstract class BbcTradeRightsRefundVO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsRefundVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后退款表ID")
        private String id;


        @ApiModelProperty("售后表ID")
        private String rightsId;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;


        @ApiModelProperty("退款结果(10:成功,20:失败)")
        private Integer state;


        @ApiModelProperty("退款结果信息")
        private String resultInfo;




    }

    @Data
    @ApiModel("BbcTradeRightsRefundVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
