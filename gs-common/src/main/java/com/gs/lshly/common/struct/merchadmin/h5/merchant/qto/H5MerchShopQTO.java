package com.gs.lshly.common.struct.merchadmin.h5.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2021-01-20
*/
public abstract class H5MerchShopQTO implements Serializable {

    @Data
    @ApiModel("H5MerchShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }

    @Data
    @ApiModel("H5MerchShopQTO.CutShopQTO")
    @Accessors(chain = true)
    public static class CutShopQTO extends BaseQTO {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;
    }
}
