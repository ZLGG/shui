package com.gs.lshly.common.struct.platadmin.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

public abstract class ShopLogisticsCompanyVO implements Serializable {

    @ApiModel("LogisticsCompanyVO.ListVO")
    @Data
    public static class ListVO implements Serializable {
        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("物流公司名")
        private String name;

        @ApiModelProperty("物流公司代码")
        private String code;

        @ApiModelProperty("物流公司网站")
        private String www;

        private boolean openflag;

    }

}
