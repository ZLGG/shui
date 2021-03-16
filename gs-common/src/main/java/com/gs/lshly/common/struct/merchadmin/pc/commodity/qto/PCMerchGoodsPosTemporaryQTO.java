package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
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
* @author Starry
* @since 2021-02-23
*/
public abstract class PCMerchGoodsPosTemporaryQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
