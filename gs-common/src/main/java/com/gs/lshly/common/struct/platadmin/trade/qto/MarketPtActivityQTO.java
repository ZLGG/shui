package com.gs.lshly.common.struct.platadmin.trade.qto;
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
* @author zdf
* @since 2020-11-28
*/
public abstract class MarketPtActivityQTO implements Serializable {

    @Data
    @ApiModel("MarketPtActivityQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty(value = "状态[10=未审核 20=审核通过 30=审核未通过 40=全部]")
        private String check;

    }
}
