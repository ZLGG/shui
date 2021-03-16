package com.gs.lshly.common.struct.platadmin.stock.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-10-24
*/
public abstract class StockLogisticsWatchQTO implements Serializable {

    @Data
    @ApiModel("StockLogisticsWatchQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
