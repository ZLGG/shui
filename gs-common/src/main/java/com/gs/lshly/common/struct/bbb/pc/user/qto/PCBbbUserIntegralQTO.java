package com.gs.lshly.common.struct.bbb.pc.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2021-01-13
*/
public abstract class PCBbbUserIntegralQTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserIntegralQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {


    }
}
