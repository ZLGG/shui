package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-11-04
*/
public abstract class PCMerchShopFloorQTO implements Serializable {


    @Data
    @ApiModel("PCMerchShopFloorQTO.H5QTO")
    @Accessors(chain = true)
    public static class H5QTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchShopFloorQTO.PCQTO")
    @Accessors(chain = true)
    public static class PCQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }
}
