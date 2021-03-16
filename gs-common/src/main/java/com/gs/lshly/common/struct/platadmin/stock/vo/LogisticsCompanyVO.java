package com.gs.lshly.common.struct.platadmin.stock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class LogisticsCompanyVO implements Serializable {

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

        private LocalDateTime cdate;

        private LocalDateTime udate;

    }

}
