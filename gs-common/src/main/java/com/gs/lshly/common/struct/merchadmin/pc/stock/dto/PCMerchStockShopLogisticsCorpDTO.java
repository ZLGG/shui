package com.gs.lshly.common.struct.merchadmin.pc.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author R
* @since 2020-10-24
*/
public abstract class PCMerchStockShopLogisticsCorpDTO implements Serializable {

    @Data
    @ApiModel("PCMerchStockShopLogisticsCorpDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    }

        @Data
        @ApiModel("PCMerchStockShopLogisticsCorpDTO.IdDTO")
        @AllArgsConstructor
        public static class IdDTO extends BaseDTO {

            @ApiModelProperty(value = "ID")
            private String id;
        }
    }



