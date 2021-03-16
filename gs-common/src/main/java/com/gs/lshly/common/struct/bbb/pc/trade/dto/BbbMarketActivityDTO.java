package com.gs.lshly.common.struct.bbb.pc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-04
*/
public abstract class BbbMarketActivityDTO implements Serializable {

    @Data
    @ApiModel("BbbMarketMerchantCardDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


    }

    @Data
    @ApiModel("BbbMarketActivityDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "活动ID")
        private String id;
    }



}
