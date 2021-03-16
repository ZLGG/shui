package com.gs.lshly.common.struct.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class CommonLogisticsCompanyVO implements Serializable {


    @Data
    @ApiModel("CommonLogisticsCompanyVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("物流公司ID")
        private String id;

        @ApiModelProperty("物流公司代码")
        private String code;

        @ApiModelProperty("物流公司名称")
        private String name;

        @ApiModelProperty("物流公司网站")
        private String www;

    }


}
