package com.gs.lshly.common.struct.bbc.merchant.qto;
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
* @since 2020-11-05
*/
public abstract class BbcShopNavigationMenuQTO implements Serializable {

    @Data
    @ApiModel("BbcShopNavigationMenuQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private Integer shopId;

    }
}
