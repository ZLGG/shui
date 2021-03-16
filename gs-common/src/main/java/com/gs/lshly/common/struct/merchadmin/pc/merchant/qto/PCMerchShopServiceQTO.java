package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author hyy
* @since 2020-11-09
*/
public abstract class PCMerchShopServiceQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopServiceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
