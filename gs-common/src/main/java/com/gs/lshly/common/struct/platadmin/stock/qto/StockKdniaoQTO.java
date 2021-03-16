package com.gs.lshly.common.struct.platadmin.stock.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author zst
* @since 2021-01-29
*/
public abstract class StockKdniaoQTO implements Serializable {

    @Data
    @ApiModel("StockKdniaoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
