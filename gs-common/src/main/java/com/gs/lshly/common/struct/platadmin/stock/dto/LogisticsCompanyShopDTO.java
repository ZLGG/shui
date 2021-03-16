package com.gs.lshly.common.struct.platadmin.stock.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
public abstract class LogisticsCompanyShopDTO implements Serializable {


    @Data
    @ApiModel("LogisticsCompanyShopDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("ID")
        private String id;
    }

    @Data
    @ApiModel("LogisticsCompanyShopDTO.LogisticsCorpIdDTO")
    @AllArgsConstructor
    public static class LogisticsCorpIdDTO extends BaseDTO {
        @ApiModelProperty("物流公司ID")
        private String logisticsCorpId;
    }

}
