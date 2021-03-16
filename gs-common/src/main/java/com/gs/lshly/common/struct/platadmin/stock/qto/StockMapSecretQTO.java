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
* @author zst
* @since 2021-01-15
*/
public abstract class StockMapSecretQTO implements Serializable {

    @Data
    @ApiModel("StockMapSecretQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
