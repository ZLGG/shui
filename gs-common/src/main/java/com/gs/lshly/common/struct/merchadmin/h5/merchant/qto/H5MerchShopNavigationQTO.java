package com.gs.lshly.common.struct.merchadmin.h5.merchant.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zst
* @since 2021-1-27
*/
public abstract class H5MerchShopNavigationQTO implements Serializable {

    @Data
    @ApiModel("H5MerchShopNavigationQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "2b2c端区分[10=2b 20=2c]",position = 1)
        private Integer terminal;

    }

    @Data
    @ApiModel("H5MerchShopNavigationQTO.MenuQTO")
    @Accessors(chain = true)
    public static class MenuQTO extends BaseQTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]")
        private Integer terminal;

    }
}
