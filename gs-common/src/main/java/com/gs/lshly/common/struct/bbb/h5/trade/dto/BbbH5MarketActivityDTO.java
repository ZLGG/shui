package com.gs.lshly.common.struct.bbb.h5.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zdf
* @since 2020-12-04
*/
public abstract class BbbH5MarketActivityDTO implements Serializable {

    @Data
    @ApiModel("BbbH5MarketActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


    }

    @Data
    @ApiModel("BbbH5MarketActivityDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "活动ID")
        private String id;
    }



}
