package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
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
* @since 2020-10-18
*/
public abstract class PCMerchShopNavigationMenuQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopNavigationMenuQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuQTO.H5QTO")
    @Accessors(chain = true)
    public static class H5QTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuQTO.H5TextMenuQTO")
    @Accessors(chain = true)
    public static class H5TextMenuQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuQTO.PCQTO")
    @Accessors(chain = true)
    public static class PCQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchShopNavigationMenuQTO.PCShopMenuQTO")
    @Accessors(chain = true)
    public static class PCShopMenuQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

    }
}
