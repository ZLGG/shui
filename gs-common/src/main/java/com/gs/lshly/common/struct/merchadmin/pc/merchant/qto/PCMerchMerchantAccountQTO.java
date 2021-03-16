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
* @author xxfc
* @since 2020-10-23
*/
public abstract class PCMerchMerchantAccountQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantAccountQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }

    @Data
    @ApiModel("PCMerchMerchantAccountQTO.GetPhoneCheckQTO")
    @Accessors(chain = true)
    public static class GetPhoneCheckQTO extends BaseQTO {

    }

    @Data
    @ApiModel("PCMerchMerchantAccountQTO.GetImageCheckQTO")
    @Accessors(chain = true)
    public static class GetImageCheckQTO extends BaseQTO {

    }
}
