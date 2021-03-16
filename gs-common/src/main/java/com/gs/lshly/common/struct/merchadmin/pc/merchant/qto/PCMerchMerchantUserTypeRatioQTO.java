package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-06
*/
public abstract class PCMerchMerchantUserTypeRatioQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantUserTypeRatioQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
