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
* @since 2020-12-15
*/
public abstract class PCMerchGoodsPosLogQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsPosLogQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品编号")
        private String numIid;

        @ApiModelProperty("商品标题")
        private String title;

    }
}
