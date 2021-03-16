package com.gs.lshly.common.struct.platadmin.trade.qto;
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
* @since 2020-12-24
*/
public abstract class TradeComplaintQTO implements Serializable {

    @Data
    @ApiModel("TradeComplaintQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("投诉订单号")
        private String tradeCode;

        @ApiModelProperty("查询类型 -1=全部 10=待处理 20=已完成 30=已关闭 40=买家撤销")
        private Integer queryType;
    }
}
