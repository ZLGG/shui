package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class PCMerchShopQTO implements Serializable {

    @Data
    @ApiModel("MerchAdmin.ShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
