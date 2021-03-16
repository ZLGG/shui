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
public abstract class PCMerchShopNavigationQTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopNavigationQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "2b2c端区分[10=2b 20=2c]",position = 1)
        private Integer terminal;

    }

    @Data
    @ApiModel("PCMerchShopNavigationQTO.MenuQTO")
    @Accessors(chain = true)
    public static class MenuQTO extends BaseQTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]")
        private Integer terminal;

    }
}
