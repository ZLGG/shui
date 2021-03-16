package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 14:50 2020/11/9
 */
public abstract class PCMerchGoodsCategoryQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺id")
        private String shopId;

    }
}
