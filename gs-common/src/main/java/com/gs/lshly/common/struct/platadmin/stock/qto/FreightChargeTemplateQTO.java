package com.gs.lshly.common.struct.platadmin.stock.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
public abstract class FreightChargeTemplateQTO implements Serializable {

    @ApiModel("查询条件")
    @Data
    public static class QueryParam extends BaseQTO {
        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("计价方式：10-金额方式、20-数量方式、30-重量方式")
        private Integer calculateWay;

        @ApiModelProperty("是否包邮")
        private Boolean freeflag;
    }

}
