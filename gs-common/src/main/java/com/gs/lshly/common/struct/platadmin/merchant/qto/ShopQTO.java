package com.gs.lshly.common.struct.platadmin.merchant.qto;
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
* @since 2020-10-13
*/
public abstract class ShopQTO implements Serializable {

    @Data
    @ApiModel("ShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("条件类型")
        private String conditionType;

        @ApiModelProperty("条件类型的值")
        private String conditionValue;

        @ApiModelProperty(value = "终端类型[10=2b,20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("ShopQTO.SelfShopQTO")
    @Accessors(chain = true)
    public static class SelfShopQTO extends BaseQTO {

        @ApiModelProperty(value = "终端类型[10=2b,20=2c]",hidden = true)
        private Integer terminal;

    }


}
