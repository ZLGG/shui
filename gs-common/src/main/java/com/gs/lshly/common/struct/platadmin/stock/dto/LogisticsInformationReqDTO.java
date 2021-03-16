package com.gs.lshly.common.struct.platadmin.stock.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author tx
* @since 2020-12-04
*/
public abstract class LogisticsInformationReqDTO implements Serializable {

    @Data
    @ApiModel("LogisticsInformationReqDTO.RequestDTO")
    @Accessors(chain = true)
    public static class RequestDTO implements Serializable{

        @ApiModelProperty("订单编号")
        private String OrderCode;


        @ApiModelProperty("商家编码")
        private String CustomerName;


        @ApiModelProperty("快递公司编码")
        private String ShipperCode;


        @ApiModelProperty("物流单号")
        private String LogisticCode;

    }

}
