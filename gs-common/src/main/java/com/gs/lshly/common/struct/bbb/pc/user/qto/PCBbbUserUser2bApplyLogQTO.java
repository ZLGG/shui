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
* @author Starry
* @since 2021-01-30
*/
public abstract class PCBbbUserUser2bApplyLogQTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserUser2bApplyLogQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
