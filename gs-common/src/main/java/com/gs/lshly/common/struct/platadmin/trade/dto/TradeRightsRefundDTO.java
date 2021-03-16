package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
* @since 2020-12-22
*/
public abstract class TradeRightsRefundDTO implements Serializable {

    @Data
    @ApiModel("TradeRightsRefundDTO.ETO")
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

        @ApiModelProperty("退款方式(10:线下退款,20:原路返回)")
        private Integer refundType;

        @ApiModelProperty("退款结果(10:成功,20:失败)")
        private Integer state;

        @ApiModelProperty("退款结果信息")
        private String resultInfo;

        @ApiModelProperty("退款银行名字")
        private String refundBankName;

        @ApiModelProperty("退款银行账户")
        private String refundAccount;

        @ApiModelProperty("退款人")
        private String refundName;

        @ApiModelProperty("收款银行名字")
        private String collectBankName;

        @ApiModelProperty("收款银行账户")
        private String collectAccount;

        @ApiModelProperty("收款人")
        private String collectName;
    }

    @Data
    @ApiModel("TradeRightsRefundDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "售后退款表ID")
        private String id;
    }


}
