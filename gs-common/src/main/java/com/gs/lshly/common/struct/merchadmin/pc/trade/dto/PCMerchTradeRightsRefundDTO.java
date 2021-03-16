package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;;
/**
* @author zdf
* @since 2020-12-17
*/
public abstract class PCMerchTradeRightsRefundDTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeRightsRefundDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "售后退款表ID",hidden = true)
        private String id;

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

    @Data
    @ApiModel("PCMerchTradeRightsRefundDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "售后退款表ID")
        private String id;
    }


}
