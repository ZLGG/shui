package com.gs.lshly.common.struct.platadmin.stock.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


public abstract class LogisticsCompanyDTO implements Serializable {

    @ApiModel("提交快递公司form表单")
    @Data
    public static class Form extends BaseDTO{
        @ApiModelProperty(value = "公司名称", hidden = true)
        private String id;

        @ApiModelProperty(value = "公司名称")
        private String name;

        @ApiModelProperty(value = "公司代码")
        private String code;

        @ApiModelProperty(value = "公司网站")
        private String www;

    }

    @Data
    @ApiModel("LogisticsCompanyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("物流公司ID")
        private String logisticsCorpId;
    }

    @Data
    @ApiModel("LogisticsCompanyDTO.IdDTO")
    @AllArgsConstructor
    public static class ShopIdDTO extends BaseDTO {
        @ApiModelProperty("店铺ID")
        private String shopId;
    }

}
