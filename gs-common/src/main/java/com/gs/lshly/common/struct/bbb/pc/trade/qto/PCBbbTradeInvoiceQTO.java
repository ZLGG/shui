package com.gs.lshly.common.struct.bbb.pc.trade.qto;
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
* @since 2020-12-24
*/
public abstract class PCBbbTradeInvoiceQTO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeInvoiceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
