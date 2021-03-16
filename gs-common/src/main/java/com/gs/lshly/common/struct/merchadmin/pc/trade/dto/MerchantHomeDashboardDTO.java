package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zdf
* @since 2021-01-06
*/
public abstract class MerchantHomeDashboardDTO implements Serializable {

    @Data
    @ApiModel("MerchantHomeDashboardDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    }

}
