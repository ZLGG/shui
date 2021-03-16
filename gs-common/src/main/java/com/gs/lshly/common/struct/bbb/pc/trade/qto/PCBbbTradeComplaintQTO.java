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
* @author Starry
* @since 2020-12-23
*/
public abstract class PCBbbTradeComplaintQTO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeComplaintQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
