package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopTextNavigationLinkQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }
}
