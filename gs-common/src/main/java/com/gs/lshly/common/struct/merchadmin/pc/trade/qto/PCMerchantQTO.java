package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
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
* @since 2020-12-04
*/
public abstract class PCMerchantQTO implements Serializable {

    @Data
    @ApiModel("PCMerchantQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {


    }
}
