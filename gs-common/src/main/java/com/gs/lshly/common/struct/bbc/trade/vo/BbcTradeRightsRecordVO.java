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
public abstract class BbcTradeRightsRecordVO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsRecordVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后记录表ID")
        private String id;


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

    @Data
    @ApiModel("BbcTradeRightsRecordVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
