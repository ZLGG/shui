package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-17
*/
public abstract class PCMerchTradeRightsRefundQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeRightsRefundQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("售后表ID")
        private String rightsId;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("退款结果(10:成功,20:失败)")
        private Integer state;

        @ApiModelProperty("退款结果信息")
        private String resultInfo;
    }
}
