package com.gs.lshly.common.struct.bbb.h5.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author oy
* @since 2020-11-04
*/
public abstract class BbbH5TradePayOfflineDTO implements Serializable {

    @Data
    @ApiModel("BbcTradePayOfflineDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "线下支付记录表ID",hidden = true)
        private String id;

        @ApiModelProperty("支付ID")
        private String payId;

        @ApiModelProperty("支付凭证")
        private String payOrder;

        @ApiModelProperty("转帐金额")
        private BigDecimal payAmount;

        @ApiModelProperty("账户名")
        private String accountName;

        @ApiModelProperty("银行账号")
        private String accountNumber;

        @ApiModelProperty("银行名称")
        private String bankName;

        @ApiModelProperty("转帐备注")
        private String payRemark;

        @ApiModelProperty("审核状态:待确认/驳回/确认")
        private Integer verifyState;

        @ApiModelProperty("审核人名称")
        private String verifyName;

        @ApiModelProperty("审核备注")
        private String verifyRemark;

        @ApiModelProperty("审核人ID")
        private String merchantAccountId;
    }

    @Data
    @ApiModel("BbcTradePayOfflineDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "线下支付记录表ID")
        private String id;
    }


}
