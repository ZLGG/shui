package com.gs.lshly.common.struct.bbc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author zdf
* @since 2020-12-17
*/
public abstract class BbcMarketActivityDTO implements Serializable {

    @Data
    @ApiModel(value = "BbcMarketActivityDTO")
    @Accessors(chain = true)
    public static class DTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String id;


    }
    @Data
    @ApiModel("BbcMarketActivityDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "活动ID")
        private String id;
    }




}
