package com.gs.lshly.common.struct.bbb.h5.trade.qto;
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
public abstract class H5BbbTradeInvoiceQTO implements Serializable {

    @Data
    @ApiModel("H5BbbTradeInvoiceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }


    @Data
    @ApiModel("H5BbbTradeInvoiceQTO.IdQTO")
    @Accessors(chain = true)
    public static class IdQTO extends BaseQTO {

    }

    @Data
    @ApiModel("H5BbbTradeInvoiceQTO.TradeIdQTO")
    @Accessors(chain = true)
    public static class TradeIdQTO extends BaseQTO {

        @ApiModelProperty("订单编号")
        private String tradeId;

    }
}
