package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class ShopTypeDictQTO implements Serializable {

    @Data
    @ApiModel("ShopTypeDictQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
