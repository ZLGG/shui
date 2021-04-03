package com.gs.lshly.common.struct.bb.trade.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-13
*/
public abstract class RechargeOrderQTO implements Serializable {

    @Data
    @ApiModel("RechargeOrderQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }
}
