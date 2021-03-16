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
* @author xxfc
* @since 2020-12-10
*/
public abstract class PCBbbUserFrequentGoodsQTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserFrequentGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
