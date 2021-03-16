package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopAdvertQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopAdvertQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "广告类型[10=通栏广告 20=单张广告]",hidden = true)
        private Integer advertType;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }
}
