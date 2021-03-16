package com.gs.lshly.common.struct.merchadmin.pc.stock.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-10-24
*/
public abstract class PCMerchStockTemplateQTO implements Serializable {

    @Data
    @ApiModel("PCMerchStockTemplateQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("计费类型[10=卖家承担 20=计重 30=计件 40=地区金额]")
        private Integer templateType;

    }

}
