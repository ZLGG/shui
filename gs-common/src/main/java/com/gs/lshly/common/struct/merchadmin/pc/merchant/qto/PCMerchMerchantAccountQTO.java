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
        @ApiModelProperty("商户名称")
        private String name;
        @ApiModelProperty("商户类型（10=积分商户 20=普通商户）")
        private Integer type;
        @ApiModelProperty("商户属地")
        private String merAddress;
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
