package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author xxfc
* @since 2020-12-25
*/
public abstract class PCMerchMerchantUserTypeQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantUserTypeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }

}
